package com.example.NezdravaHrana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Stavka {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private Integer kolicina;

    @OneToOne
    private Artikal artikal;
}
