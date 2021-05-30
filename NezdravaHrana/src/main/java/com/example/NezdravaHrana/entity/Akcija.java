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
public class Akcija {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private Integer procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;

    @ManyToOne
    private Prodavac prodavac;

    @ManyToMany
    @JoinTable(
            name = "akcija_artikli",
            joinColumns = @JoinColumn(name = "akcija_id"),
            inverseJoinColumns = @JoinColumn(name = "artikal_id"))
    private Set<Artikal> artikli = new HashSet<>();
}
