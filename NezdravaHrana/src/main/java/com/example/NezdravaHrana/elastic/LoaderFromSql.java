package com.example.NezdravaHrana.elastic;

import com.example.NezdravaHrana.elastic.entity.ArtikalES;
import com.example.NezdravaHrana.elastic.repository.ArtikalEsRepository;
import com.example.NezdravaHrana.entity.Artikal;
import com.example.NezdravaHrana.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoaderFromSql {

    /*prilikom svakog pokretanja podatke iz sql ubacujemo u elastic search bazu*/

    /*@Autowired
    ElasticsearchOperations elasticsearchOperations;*/


    @Autowired
    ArtikalRepository artikalRepository;

    @Autowired
    ArtikalEsRepository artikalEsRepository;

    /*@Autowired
    PorudzbinaEsRepository porudzbinaEsRepository;


    @Autowired
    PorudzbinaRepository porudzbinaRepository;*/


    /*@Autowired
    ArtikalESService artikalESService;*/

   /* @Autowired
    PorudzbinaESService porudzbinaESService;*/

    @PostConstruct
    @Transactional
    public void loadAll(){

        //artikalEsRepository.deleteAll();
        //porudzbinaEsRepository.deleteAll();
        System.out.println("Loading Data");
        List<ArtikalES> artikli = new ArrayList<>();
        for(Artikal artikal: artikalRepository.findAll()){
            artikli.add(new ArtikalES(artikal));
        }
        /*for(ArtikalES artikalES: artikli){
            artikalES.setRating(artikalESService.getRating(artikalES));
            artikalES.setKomentara(artikalESService.getBrojKomentara(artikalES));
        }*/
        artikalEsRepository.saveAll(artikli);

        System.out.println(artikalEsRepository.findAll());


        /*List<PorudzbinaES> porudzbinaES = new ArrayList<>();
        for(Porudzbina porudzbina: porudzbinaRepository.findAll()){
            porudzbinaES.add(new PorudzbinaES(porudzbina));
        }
        for(PorudzbinaES p: porudzbinaES){
            p.setCena(porudzbinaESService.getUkupnaCenaPorudzbine(p));
        }

        porudzbinaEsRepository.saveAll(porudzbinaES);*/

        System.out.println("Loading Completed");
    }
}