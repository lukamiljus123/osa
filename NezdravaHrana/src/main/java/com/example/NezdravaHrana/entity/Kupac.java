package com.example.NezdravaHrana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Kupac {

    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    @MapsId
    private Korisnik korisnik;

    private String adresa;
}
