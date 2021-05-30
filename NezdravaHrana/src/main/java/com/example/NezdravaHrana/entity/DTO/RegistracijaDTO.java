package com.example.NezdravaHrana.entity.DTO;

import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.entity.Kupac;
import com.example.NezdravaHrana.entity.Prodavac;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistracijaDTO {

    private Integer id;

    private String ime;

    private String prezime;

    private String username;

    private String password;

    private String adresa;

    private String email;

    private String naziv;

    public RegistracijaDTO (Prodavac prodavac) {
        this.id = prodavac.getKorisnik().getId();
        this.ime = prodavac.getKorisnik().getIme();
        this.prezime = prodavac.getKorisnik().getPrezime();
        this.username = prodavac.getKorisnik().getUsername();
        this.password = prodavac.getKorisnik().getPassword();
        this.adresa = prodavac.getAdresa();
        this.email = prodavac.getEmail();
        this.naziv = prodavac.getNaziv();
    }

    public RegistracijaDTO (Kupac kupac) {
        this.id = kupac.getKorisnik().getId();
        this.ime = kupac.getKorisnik().getIme();
        this.prezime = kupac.getKorisnik().getPrezime();
        this.username = kupac.getKorisnik().getUsername();
        this.password = kupac.getKorisnik().getPassword();
        this.adresa = kupac.getAdresa();
    }
}
