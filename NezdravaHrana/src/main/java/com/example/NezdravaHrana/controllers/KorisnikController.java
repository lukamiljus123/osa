package com.example.NezdravaHrana.controllers;

import com.example.NezdravaHrana.entity.DTO.KorisnikDTO;
import com.example.NezdravaHrana.entity.DTO.RegistracijaDTO;
import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.entity.Kupac;
import com.example.NezdravaHrana.entity.Prodavac;
import com.example.NezdravaHrana.entity.Roles;
import com.example.NezdravaHrana.repository.KorisnikRepository;
import com.example.NezdravaHrana.security.TokenUtils;
import com.example.NezdravaHrana.service.KupacService;
import com.example.NezdravaHrana.service.ProdavacService;
import com.example.NezdravaHrana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/korisnici")
@CrossOrigin(origins = "http://localhost:3000")
public class KorisnikController {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProdavacService prodavacService;

    @Autowired
    KupacService kupacService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDTO> findOne(@PathVariable("id") Integer id) {
        Korisnik korisnik = korisnikRepository.findById(id).orElse(null);
        if (korisnik == null || korisnik.isBlokiran()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new KorisnikDTO(korisnik),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @GetMapping
    public ResponseEntity<Collection<KorisnikDTO>> findAll() {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for (Korisnik k: korisnici) {
            if (!k.isBlokiran()) {
                korisniciDTO.add(new KorisnikDTO(k));
            }
        }
        return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @GetMapping("blokiranje")
    public ResponseEntity<Collection<KorisnikDTO>> findAllNonAdmins() {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for (Korisnik k: korisnici) {
            if (!k.getRoles().equals(Roles.ADMINISTRATOR) && !k.isBlokiran()) {
                korisniciDTO.add(new KorisnikDTO(k));
            }
        }
        return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @PutMapping("blokiraj/{id}")
    public ResponseEntity<Korisnik> blokiraj(@PathVariable Integer id) {
        Korisnik k = korisnikRepository.findById(id).orElse(null);
        if (k == null) {
            return ResponseEntity.notFound().build();
        }
        k.setBlokiran(!k.isBlokiran());
        korisnikRepository.save(k);
        return new ResponseEntity<>(k, HttpStatus.CREATED);
    }

    @PostMapping("/registracija/{isProdavac}")
    public ResponseEntity<RegistracijaDTO> registracija (@RequestBody RegistracijaDTO registracijaDTO,
                                                        @PathVariable boolean isProdavac) {
        Korisnik noviKorisnik = new Korisnik();

        noviKorisnik.setUsername(registracijaDTO.getUsername());
        System.out.println(registracijaDTO.getPassword());
        noviKorisnik.setPassword(passwordEncoder.encode(registracijaDTO.getPassword()));
        noviKorisnik.setIme(registracijaDTO.getIme());
        noviKorisnik.setPrezime(registracijaDTO.getPrezime());
        noviKorisnik.setBlokiran(false);

        if (isProdavac) {
            noviKorisnik.setRoles(Roles.PRODAVAC);
            korisnikRepository.save(noviKorisnik);

            Prodavac prodavac = new Prodavac();

            prodavac.setAdresa(registracijaDTO.getAdresa());
            prodavac.setEmail(registracijaDTO.getEmail());
            prodavac.setNaziv(registracijaDTO.getNaziv());
            prodavac.setPoslujeOd(java.sql.Date.valueOf(java.time.LocalDate.now()));
            prodavac.setKorisnik(noviKorisnik);

            prodavacService.save(prodavac);
            return new ResponseEntity<RegistracijaDTO>(new RegistracijaDTO(prodavac), HttpStatus.CREATED);
        } else {
            noviKorisnik.setRoles(Roles.KUPAC);
            korisnikRepository.save(noviKorisnik);

            Kupac kupac = new Kupac();
            kupac.setAdresa(registracijaDTO.getAdresa());
            kupac.setKorisnik(noviKorisnik);

            kupacService.save(kupac);
            return new ResponseEntity<RegistracijaDTO>(new RegistracijaDTO(kupac), HttpStatus.CREATED);
        }
    }

    @PostMapping("/prijava")
    public ResponseEntity<String> prijava(@RequestBody KorisnikDTO userDto) {
        Korisnik k = userService.findByUsername(userDto.getUsername());
        if (k == null) {
            return ResponseEntity.notFound().build();
        }
        if (k.isBlokiran()) {
            return ResponseEntity.notFound().build();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
