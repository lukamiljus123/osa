package com.example.NezdravaHrana.repository;

import com.example.NezdravaHrana.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac,Integer> {

}
