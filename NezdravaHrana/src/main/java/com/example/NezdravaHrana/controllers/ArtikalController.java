package com.example.NezdravaHrana.controllers;

import com.example.NezdravaHrana.entity.Artikal;
import com.example.NezdravaHrana.entity.DTO.ArtikalDTO;
import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.entity.Roles;
import com.example.NezdravaHrana.service.ArtikalService;
import com.example.NezdravaHrana.service.ProdavacService;
import com.example.NezdravaHrana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/artikli")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtikalController {

    @Autowired
    ArtikalService artikalService;

    @Autowired
    UserService userService;

    @Autowired
    ProdavacService prodavacService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'PRODAVAC', 'KUPAC')")
    @GetMapping("/{id}")
    public ResponseEntity<ArtikalDTO> findOne(@PathVariable("id") Integer id) {
        Artikal artikal = artikalService.findOne(id);
        if (artikal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ArtikalDTO(artikal),HttpStatus.OK);
    }

    @GetMapping("/prodavca/{prodavacId}")
    public ResponseEntity<Collection<ArtikalDTO>> findArtikliProdavca(Authentication authentication, @PathVariable("prodavacId") Integer prodavacId) {

        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            String username = userPrincipal.getUsername();
            Korisnik korisnik = userService.findByUsername(username); // prodavac

            if (korisnik.getRoles().equals(Roles.PRODAVAC)) {
                prodavacId = korisnik.getId();
            }
        }

        List<Artikal> artikli = artikalService.findAll();
        List<ArtikalDTO> artikliDTO = new ArrayList<>();
        for (Artikal a: artikli) {
            if (a.getProdavac().getId().equals(prodavacId)) {
                artikliDTO.add(new ArtikalDTO(a));
            }
        }
        return new ResponseEntity<>(artikliDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<ArtikalDTO>> findAll() {
        List<Artikal> artikli = artikalService.findAll();
        List<ArtikalDTO> artikliDTO = new ArrayList<>();
        for (Artikal a: artikli) {
            artikliDTO.add(new ArtikalDTO(a));
        }
        return new ResponseEntity<>(artikliDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('PRODAVAC')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ArtikalDTO> snimiArtikal (@RequestBody ArtikalDTO artikalDTO, Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        Korisnik korisnik = userService.findByUsername(username); // prodavac
        artikalDTO.setProdavacId(korisnik.getId());

        Artikal noviArtikal = new Artikal();
        noviArtikal.setProdavac(prodavacService.findOne(artikalDTO.getProdavacId()));

        updateArtikalFromArtikalDTO (noviArtikal, artikalDTO);

        artikalService.save(noviArtikal);

        return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(noviArtikal), HttpStatus.CREATED);
    }

    private void updateArtikalFromArtikalDTO (Artikal artikal, ArtikalDTO artikalDTO) {
        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setOpis(artikalDTO.getOpis());
        artikal.setCena(artikalDTO.getCena());
        artikal.setPutanjaSlike(artikalDTO.getPutanjaSlike());
    }

    @PreAuthorize("hasAnyRole('PRODAVAC')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<ArtikalDTO> izmeniArtikal(@RequestBody ArtikalDTO artikalDTO, Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        Korisnik korisnik = userService.findByUsername(username); // prodavac
        if (!artikalService.findOne(artikalDTO.getId()).getProdavac().getId().equals(korisnik.getId())) {
            return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
        }

        Artikal artikal = artikalService.findOne(artikalDTO.getId());
        if (artikal == null) {
            return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
        }

        updateArtikalFromArtikalDTO (artikal, artikalDTO);

        artikalService.save (artikal);

        return new ResponseEntity<ArtikalDTO> (new ArtikalDTO (artikal), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('PRODAVAC')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ArtikalDTO> obrisiArtikal (@PathVariable("id") Integer id) {
        Artikal artikal = artikalService.findOne(id);
        if (artikal == null) {
            return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
        }

        artikalService.delete(artikal);

        return new ResponseEntity<ArtikalDTO> (new ArtikalDTO (artikal), HttpStatus.CREATED);
    }
}
