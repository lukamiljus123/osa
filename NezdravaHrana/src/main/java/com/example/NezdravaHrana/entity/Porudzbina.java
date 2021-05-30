package com.example.NezdravaHrana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Porudzbina {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private Date satnica;
    private boolean dostavljeno;
    private Integer ocena;
    private String komentar;
    private boolean anonimanKomentar;
    private boolean arhiviranKomentar;

    @ManyToOne
    private Kupac kupac;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stavka> stavke = new HashSet<>();
}
