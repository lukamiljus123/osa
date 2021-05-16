package com.example.NezdravaHrana.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class Korisnik {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String ime;

    private String prezime;

    private String username;

    private String password;

    private boolean blokiran;

    @Enumerated(EnumType.STRING)
    private Roles roles;
}
