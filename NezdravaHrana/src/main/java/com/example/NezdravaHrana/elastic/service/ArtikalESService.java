package com.example.NezdravaHrana.elastic.service;

import com.example.NezdravaHrana.elastic.entity.ArtikalES;
import com.example.NezdravaHrana.elastic.repository.ArtikalEsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtikalESService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ArtikalEsRepository artikalEsRepository;


    public ArtikalESService(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public List<ArtikalES> findByNaziv(String naziv) {
        return artikalEsRepository.findAllByNaziv(naziv);
    }

    public List<ArtikalES> findByOpis(String opis) {
        return artikalEsRepository.findAllByOpis(opis);
    }

    public List<ArtikalES> findAll() {
        List<ArtikalES> artikli = new ArrayList<>();
        for (ArtikalES artikal2: artikalEsRepository.findAll()) {
            artikli.add(artikal2);
        }
        return artikli;
    }
}
