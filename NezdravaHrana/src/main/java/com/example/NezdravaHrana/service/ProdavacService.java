package com.example.NezdravaHrana.service;

import com.example.NezdravaHrana.entity.Prodavac;
import com.example.NezdravaHrana.repository.ProdavacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdavacService {

    @Autowired
    ProdavacRepository prodavacRepository;

    public Prodavac findOne(int prodavacId) {
        return prodavacRepository.findById(prodavacId).orElse(null);
    }

    public List<Prodavac> findAll() {
        return prodavacRepository.findAll();
    }

    public Prodavac save(Prodavac prodavac) {
        return prodavacRepository.save(prodavac);
    }
}
