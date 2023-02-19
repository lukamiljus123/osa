package com.example.NezdravaHrana.repository;

import com.example.NezdravaHrana.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal,Integer> {

}
