package com.example.NezdravaHrana.controllers;

import com.example.NezdravaHrana.entity.DTO.KorisnikDTO;
import com.example.NezdravaHrana.entity.DTO.ProdavacDTO;
import com.example.NezdravaHrana.entity.DTO.RegistracijaDTO;
import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.entity.Kupac;
import com.example.NezdravaHrana.entity.Prodavac;
import com.example.NezdravaHrana.entity.Roles;
import com.example.NezdravaHrana.repository.KorisnikRepository;
import com.example.NezdravaHrana.repository.ProdavacRepository;
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
@RequestMapping("/prodavci")
@CrossOrigin(origins = "http://localhost:3000")
public class ProdavacController {

    @Autowired
    ProdavacRepository prodavacRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProdavacDTO> findOne(@PathVariable("id") Integer id) {
        Prodavac prodavac = prodavacRepository.findById(id).orElse(null);
        if (prodavac == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ProdavacDTO(prodavac),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<ProdavacDTO>> findAll() {
        List<Prodavac> prodavci = prodavacRepository.findAll();
        List<ProdavacDTO> prodavciDTO = new ArrayList<>();
        for (Prodavac p: prodavci) {
            prodavciDTO.add(new ProdavacDTO(p));
        }
        return new ResponseEntity<>(prodavciDTO, HttpStatus.OK);
    }
}
