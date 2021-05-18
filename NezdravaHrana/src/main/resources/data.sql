INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Марко', 'Марковић', 'markom', '123', false, 'ADMINISTRATOR');

-- PRODAVCI
INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Мирко', 'Мирковић', 'mirkolegenda', '123', false, 'PRODAVAC');
INSERT INTO prodavac (korisnik_id, posluje_od, email, adresa, naziv)
VALUES (2, '2021-04-10', 'mirkom@gmail.com', 'Змајева 117', 'Миркомаркет');

-- KUPCI
INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Јована', 'Јовановић', 'jovanаj', '123', false, 'KUPAC');
INSERT INTO kupac (korisnik_id, adresa)
VALUES (3, 'Змајева 19');

-- ARTIKLI
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Празна палачинка', 'Не знам зашто бисте то хтели', 80, 'palacinke.jpg', 2);
INSERT INTO artikal (naziv, opis, cena, putanja_slike, prodavac_korisnik_id)
VALUES ('Палачинка са нутелом', 'Није лоша', 120, 'nutella.jpg', 2);

-- AKCIJE
INSERT INTO akcija (procenat, od_kad, do_kad, tekst, prodavac_korisnik_id)
VALUES (10, '2021-05-21', '2021-05-24', 'Викенд попуст', 2);

-- PORUDZBINE
INSERT INTO porudzbina (satnica, dostavljeno, ocena, komentar, anoniman_komentar, arhiviran_komentar, kupac_korisnik_id)
VALUES ('2021-05-16', true, 5, 'Ништа боље нисам појела у животу', false, false, 3);

-- STAVKE
INSERT INTO stavka (kolicina, artikal_id)
VALUES (3, 2);
