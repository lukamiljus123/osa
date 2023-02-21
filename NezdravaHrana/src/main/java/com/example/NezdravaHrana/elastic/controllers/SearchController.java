package com.example.NezdravaHrana.elastic.controllers;

import com.example.NezdravaHrana.elastic.controllers.dto.PriceRequestDTO;
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
    public List<ArtikalES> searchByNaziv(@RequestBody TextRequestDTO textRequestDTO) {
        return artikalESService.findByNaziv(textRequestDTO.getText());
    }

    @GetMapping("/opis")
    public List<ArtikalES> searchByOpis(@RequestBody TextRequestDTO textRequestDTO) {
        return artikalESService.findByOpis(textRequestDTO.getText());
    }

    @GetMapping("/cena")
    public List<ArtikalES> searchByCena(@RequestBody PriceRequestDTO priceRequestDTO) {
        Double cenaOd = priceRequestDTO.getCenaOd();
        Double cenaDo = priceRequestDTO.getCenaDo();
        if (cenaOd == null) {
            if (cenaDo == null) {
                return null;
            }
            else {
                return artikalESService.findByCenaLessThan(cenaDo);
            }
        }
        else if (cenaDo == null) {
            return artikalESService.findByCenaGreaterThan(cenaOd);
        }
        return artikalESService.findByCenaBetween(cenaOd, cenaDo);
    }
}
