package com.example.NezdravaHrana.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.sql.Date;

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

    // meni tu meni sa artiklom
}
