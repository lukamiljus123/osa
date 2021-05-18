package com.example.NezdravaHrana.service;

import com.example.NezdravaHrana.entity.Artikal;
import com.example.NezdravaHrana.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtikalService {

    @Autowired
    ArtikalRepository artikalRepository;

    public Artikal findOne(int artikalId) {
        return artikalRepository.findById(artikalId).orElse(null);
    }

    public List<Artikal> findAll() {
        return artikalRepository.findAll();
    }

    public Artikal save(Artikal artikal) {
        return artikalRepository.save(artikal);
    }

    public void delete(Artikal artikal) { artikalRepository.delete(artikal); }
}
