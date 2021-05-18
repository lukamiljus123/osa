package com.example.NezdravaHrana.entity.DTO;

import com.example.NezdravaHrana.entity.Artikal;
import com.example.NezdravaHrana.entity.Korisnik;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArtikalDTO {

    private Integer id;

    private String naziv;

    private String opis;

    private Double cena;

    private String putanjaSlike;

    private Integer prodavacId;

    public ArtikalDTO(Artikal artikal){
        this.id = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
        this.putanjaSlike = artikal.getPutanjaSlike();
        this.prodavacId = artikal.getProdavac().getKorisnik().getId();
    }
}
