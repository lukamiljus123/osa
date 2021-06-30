package com.example.NezdravaHrana.entity.DTO;

import com.example.NezdravaHrana.entity.Korisnik;
import com.example.NezdravaHrana.entity.Prodavac;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdavacDTO {

    private Integer id;

    private String ime;

    private String prezime;

    private String username;

    private String password;

    private String naziv;

    public ProdavacDTO(Prodavac prodavac){
        this.id = prodavac.getId();
        this.ime = prodavac.getKorisnik().getIme();
        this.prezime = prodavac.getKorisnik().getPrezime();
        this.username = prodavac.getKorisnik().getUsername();
        this.password = prodavac.getKorisnik().getPassword();
        this.naziv = prodavac.getNaziv();
    }
}
