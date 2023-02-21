package com.example.NezdravaHrana.elastic.controllers;

import com.example.NezdravaHrana.elastic.controllers.dto.TextRequestDTO;
import com.example.NezdravaHrana.elastic.entity.ArtikalES;
import com.example.NezdravaHrana.elastic.repository.ArtikalEsRepository;
import com.example.NezdravaHrana.elastic.service.ArtikalESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elastic")
public class SearchController {

    @Autowired
    private ArtikalESService artikalESService;

    @Autowired
    ArtikalEsRepository artikalEsRepository;


    @GetMapping("/artikli")
    public List<ArtikalES> findAllArtikli(){
        return artikalESService.findAll();
    }

    @GetMapping("/naziv")
    public List<ArtikalES> searchByNaziv(@RequestBody TextRequestDTO textRequestDTO){
        System.out.println(textRequestDTO.getText());
        System.out.println(artikalESService.findByNaziv(textRequestDTO.getText()).size());

        return artikalESService.findByNaziv(textRequestDTO.getText());
    }

    @GetMapping("/opis")
    public List<ArtikalES> searchByOpis(@RequestBody TextRequestDTO textRequestDTO){
        System.out.println(textRequestDTO.getText());
        System.out.println(artikalESService.findByNaziv(textRequestDTO.getText()).size());

        return artikalESService.findByOpis(textRequestDTO.getText());
    }
}
