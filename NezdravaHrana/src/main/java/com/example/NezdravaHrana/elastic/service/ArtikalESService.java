package com.example.NezdravaHrana.elastic.service;

import com.example.NezdravaHrana.elastic.entity.ArtikalES;
import com.example.NezdravaHrana.elastic.repository.ArtikalEsRepository;
import com.example.NezdravaHrana.util.CyrillicLatinConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtikalESService {

    @Autowired
    private ArtikalEsRepository artikalEsRepository;

    public List<ArtikalES> findByNaziv(String naziv) {
        naziv = CyrillicLatinConverter.cir2lat(naziv);
        return artikalEsRepository.findAllByNaziv(naziv);
    }

    public List<ArtikalES> findByOpis(String opis) {
        opis = CyrillicLatinConverter.cir2lat(opis);
        return artikalEsRepository.findAllByOpis(opis);
    }

    public List<ArtikalES> findAll() {
        List<ArtikalES> artikli = new ArrayList<>();
        for (ArtikalES artikal2: artikalEsRepository.findAll()) {
            artikli.add(artikal2);
        }
        return artikli;
    }

    public List<ArtikalES> findByCenaBetween(Double cenaOd, Double cenaDo) {
        return artikalEsRepository.findByCenaBetween(cenaOd, cenaDo);
    }

    public List<ArtikalES> findByCenaGreaterThan(Double cenaOd) {
        return artikalEsRepository.findByCenaGreaterThan(cenaOd);
    }

    public List<ArtikalES> findByCenaLessThan(Double cenaDo) {
        return artikalEsRepository.findByCenaLessThan(cenaDo);
    }
}
