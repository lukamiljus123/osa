package com.example.NezdravaHrana.controllers;

import com.example.NezdravaHrana.entity.Artikal;
import com.example.NezdravaHrana.entity.DTO.ArtikalDTO;
import com.example.NezdravaHrana.service.ArtikalService;
import com.example.NezdravaHrana.service.ProdavacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/artikli")
@CrossOrigin("*")
public class ArtikalController {

    @Autowired
    ArtikalService artikalService;

    @Autowired
    ProdavacService prodavacService;

    @GetMapping("/{id}")
    public ResponseEntity<ArtikalDTO> findOne(@PathVariable("id") Integer id){
        Artikal artikal = artikalService.findOne(id);
        if(artikal == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ArtikalDTO(artikal),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<ArtikalDTO>> findAll(){
        List<Artikal> artikli = artikalService.findAll();
        List<ArtikalDTO> artikliDTO = new ArrayList<>();
        for(Artikal a: artikli) {
            artikliDTO.add(new ArtikalDTO(a));
        }
        return new ResponseEntity<>(artikliDTO, HttpStatus.OK);
    }

    @PostMapping("/dodavanje")
    public ResponseEntity<ArtikalDTO> snimiArtikal(@RequestBody ArtikalDTO artikalDTO) {

        Artikal noviArtikal = new Artikal();

        updateArtikalFromArtikalDTO (noviArtikal, artikalDTO);

        artikalService.save(noviArtikal);

        return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(noviArtikal), HttpStatus.CREATED);
    }

    private void updateArtikalFromArtikalDTO (Artikal artikal, ArtikalDTO artikalDTO) {
        artikal.setNaziv(artikalDTO.getNaziv());
        artikal.setOpis(artikalDTO.getOpis());
        artikal.setCena(artikalDTO.getCena());
        artikal.setPutanjaSlike(artikalDTO.getPutanjaSlike());
        artikal.setProdavac(prodavacService.findOne(artikalDTO.getProdavacId()));
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ArtikalDTO> izmeniArtikal(@RequestBody ArtikalDTO artikalDTO){
        Artikal artikal = artikalService.findOne(artikalDTO.getId());
        if(artikal == null){
            return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
        }

        updateArtikalFromArtikalDTO (artikal, artikalDTO);

        artikalService.save(artikal);

        return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArtikalDTO> obrisiArtikal(@PathVariable("id") Integer id){
        Artikal artikal = artikalService.findOne(id);
        if(artikal == null){
            return new ResponseEntity<ArtikalDTO>(HttpStatus.BAD_REQUEST);
        }

        artikalService.delete(artikal);

        return new ResponseEntity<ArtikalDTO>(new ArtikalDTO(artikal), HttpStatus.CREATED);
    }
}
