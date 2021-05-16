INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Марко', 'Марковић', 'markom', '123', false, 'ADMINISTRATOR');

INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Мирко', 'Мирковић', 'mirkom', '123', false, 'PRODAVAC');
INSERT INTO prodavac (korisnik_id, poslujeOd, email, adresa, naziv)
VALUES (2, '2021-04-10', 'mirkom@gmail.com', 'Змајева 117', 'Миркомаркет');

INSERT INTO korisnik (ime, prezime, username, password, blokiran, roles)
VALUES ('Јована', 'Јовановић', 'jovanаj', '123', false, 'KUPAC');
INSERT INTO kupac (korisnik_id, adresa)
VALUES (3, 'Змајева 19');
