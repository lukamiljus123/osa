-- noinspection SqlDialectInspectionForFile

-- ADMINISTRATOR
INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Марко', 'Марковић', 'markom', '$2y$12$tGSYeNdNvspuX/8.UY4XQOetDM9d1Kz4gCaa5TIiGnu/leq3bmUYq', false, 'ADMINISTRATOR');

-- PRODAVCI
INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Мирко', 'Мирковић', 'mirkolegenda', '$2y$12$tGSYeNdNvspuX/8.UY4XQOetDM9d1Kz4gCaa5TIiGnu/leq3bmUYq', false, 'PRODAVAC');
INSERT INTO prodavac (korisnik_id, posluje_od, email, adresa, naziv)
VALUES (2, '2021-04-10', 'mirkom@gmail.com', 'Змајева 117', 'Миркомаркет');
INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Петар', 'Петровић', 'petarp', '$2y$12$tGSYeNdNvspuX/8.UY4XQOetDM9d1Kz4gCaa5TIiGnu/leq3bmUYq', false, 'PRODAVAC');
INSERT INTO prodavac (korisnik_id, posluje_od, email, adresa, naziv)
VALUES (3, '2021-05-10', 'petarp@gmail.com', 'Светосавска 117', 'Лоше палачинке Д.О.О.');

-- KUPCI
INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Јована', 'Јовановић', 'jovanaj', '$2y$12$tGSYeNdNvspuX/8.UY4XQOetDM9d1Kz4gCaa5TIiGnu/leq3bmUYq', false, 'KUPAC');
INSERT INTO kupac (korisnik_id, adresa)
VALUES (4, 'Змајева 19');

-- ARTIKLI
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Prazna palacinka', 'Ne znam zasto biste to hteli', 80, 'https://i.pinimg.com/originals/b6/ad/0b/b6ad0b9df214a3c1cab7a397de23bf02.jpg', 2);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Nutela', 'Nije losa, ali ona sa eurokremom je bolja', 130, 'https://letthebakingbegin.com/wp-content/uploads/2015/01/NutellaStuffedCrepes12-500x500.jpg', 2);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Eurokrem', 'Predivna je 🥺', 120, 'https://pbs.twimg.com/media/DZJkggYWkAEOjSg.jpg', 2);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Eurokrem, plazma', 'E ova je najbolja ubedljivo', 130, 'https://pbs.twimg.com/media/DZJkggYWkAEOjSg.jpg', 2);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Dzem', 'Bolja je ona sa eurokremom', 110, 'https://www.oetker.rs/Recipe/Recipes/oetker.rs/rs-sr/baking/image-thumb__53430__RecipeDetailsLightBox/palacinke-sa-dzemom-od-sljiva-i-oraha.jpg', 3);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Dzem, kokos', 'Cudan flex, ali u redu', 140, 'https://pizzeriajimmy.rs/wp-content/uploads/2020/02/slatka-palacinka-kokos.jpg', 3);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Slana palacinka 🤮', 'Molim Vas, nemojte 😭', 10000, 'https://cdn.navidiku.rs/firme/proizvodgalerija3/galerija50028/velike/slana-palacinka-rakovica85987.jpg', 3);

-- AKCIJE
INSERT INTO akcija (procenat, od_kad, do_kad, tekst, prodavac_korisnik_id)
VALUES (10, '2021-05-21', '2021-05-24', 'Викенд попуст', 2);

-- AKCIJA ARTIKLI
INSERT INTO akcija_artikli (akcija_id, artikal_id)
VALUES (1, 1);
INSERT INTO akcija_artikli (akcija_id, artikal_id)
VALUES (1, 2);

/*-- PORUDZBINE
INSERT INTO porudzbina (satnica, dostavljeno, ocena, komentar, anoniman_komentar, arhiviran_komentar, kupac_korisnik_id)
VALUES ('2021-05-16', true, 5, 'Ништа боље нисам појела у животу', false, false, 3);

-- STAVKE
INSERT INTO stavka (kolicina, artikal_id, porudzbina_id)
VALUES (3, 2, 1);*/
