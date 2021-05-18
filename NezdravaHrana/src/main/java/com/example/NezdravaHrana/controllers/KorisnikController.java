package com.example.NezdravaHrana.controllers;

import com.example.NezdravaHrana.entity.DTO.KorisnikDTO;
import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.repository.KorisnikDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/korisnici")
@CrossOrigin("*")
public class KorisnikController {

    @Autowired
    KorisnikDAO korisnikDAO;

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDTO> findOne(@PathVariable("id") Integer id){
        Korisnik korisnik = korisnikDAO.findById(id).orElse(null);
        if(korisnik == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new KorisnikDTO(korisnik),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<KorisnikDTO>> findAll(){
        List<Korisnik> korisnici = korisnikDAO.findAll();
        List<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for(Korisnik k: korisnici) {
            korisniciDTO.add(new KorisnikDTO(k));
        }
        return new ResponseEntity<>(korisniciDTO, HttpStatus.OK);
    }
}
