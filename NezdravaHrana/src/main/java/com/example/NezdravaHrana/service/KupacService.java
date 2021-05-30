package com.example.NezdravaHrana.service;

import com.example.NezdravaHrana.entity.Kupac;
import com.example.NezdravaHrana.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KupacService {

    @Autowired
    KupacRepository kupacRepository;

    public Kupac findOne(int kupacId) {
        return kupacRepository.findById(kupacId).orElse(null);
    }

    public List<Kupac> findAll() {
        return kupacRepository.findAll();
    }

    public Kupac save(Kupac kupac) {
        return kupacRepository.save(kupac);
    }

}