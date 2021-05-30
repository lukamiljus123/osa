package com.example.NezdravaHrana.repository;

import com.example.NezdravaHrana.entity.Prodavac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdavacRepository extends JpaRepository<Prodavac,Integer> {

}
