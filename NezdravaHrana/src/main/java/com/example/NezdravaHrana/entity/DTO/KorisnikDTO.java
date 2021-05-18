package com.example.NezdravaHrana.entity.DTO;

import com.example.NezdravaHrana.entity.Korisnik;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KorisnikDTO {

    private Integer id;

    private String ime;

    private String prezime;

    private String username;

    private String password;

    public KorisnikDTO(Korisnik korisnik){
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
    }
}
