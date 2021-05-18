package com.example.NezdravaHrana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Artikal {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String naziv;
    private String opis;
    private Double cena;
    private String putanjaSlike;

    @ManyToOne
    private Prodavac prodavac;
}
