import React from 'react';
import {Button, Card, Col, Container, Image, Row} from "react-bootstrap";
import NezdravaHranaAxiosClient from "../../services/clients/NezdravaHranaAxiosClient";
import MainPart from "../MainPart";
import {TokenService} from "../../services/TokenService";
import GoneToast from "../Toasts/GoneToast";
import {AuthenticationService} from "../../services/AuthenticationService";

export default class ArtikliProdavca extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            artikliLista : [],
            stavkeLista : []
        };
    }

    componentDidMount() {
        this.findAllArtikli();
    }

    findAllArtikli() {
        let url = "http://localhost:8080/artikli/prodavca/";
        if (AuthenticationService.getRole() === "ROLE_PRODAVAC") {
            url += "1";
        } else {
            let lokacija = window.location.href;
            lokacija = lokacija.replace("http://localhost:3000/artikli-prodavca/", "");
            url += lokacija;
        }

        NezdravaHranaAxiosClient.get(url)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    artikliLista: data
                });
            });
    };

    obrisiArtikal (event, id) {
        event.preventDefault();

        NezdravaHranaAxiosClient.delete("http://localhost:8080/artikli/" + id)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}),3000);
                } else {
                    this.setState({"show":false});
                }
                this.findAllArtikli();
            });
    };

    addStavka (event, id, kolicina) {
        event.preventDefault();

        let stavka = {
            artikalId: id,
            kolicina: kolicina
        }

        this.state.stavkeLista.push(stavka);
        console.log("Ваша корпа:", this.state.stavkeLista);
        //this.render();
    };

    render() {
        const {artikliLista, stavkeLista} = this.state;

        //console.log(stavkeLista.length === 0);

        return (
            <Container className="kontejner">
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <GoneToast children={{show: this.state.show, message:"Успешно сте обрисали артикал!"}}/>
                </div>
                <MainPart/>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Листа артикала</h3></Card.Header>
                    <Card.Body>
                        <Row>
                            {
                            artikliLista.length === 0
                                ?
                                <div>Нема доступних</div>
                                :
                                artikliLista.map((artikal)=>(
                                <Col key={artikal.id} xs={6} md={4} style={{marginBottom:"45px"}}>
                                    <h3>{artikal.naziv}</h3>
                                    <h5>Цена: {artikal.cena} РСД</h5>
                                    {
                                        TokenService.getToken()
                                        ?
                                        <a href={"/artikli/" + artikal.id}>
                                            <Image className={"slikaArtikla"} src={artikal.putanjaSlike} thumbnail />
                                        </a>
                                        :
                                        <Image className={"slikaArtikla"} src={artikal.putanjaSlike} thumbnail />
                                    }
                                    {
                                        AuthenticationService.getRole() === "ROLE_PRODAVAC" &&
                                        <div className="text-center">
                                            <Button className="btn btn-primary" href={"/izmena-artikla/" + artikal.id} role="button">Измени</Button>
                                            <Button className="btn btn-primary" onClick={(e) => this.obrisiArtikal(e, artikal.id)}>Обриши</Button>
                                        </div>
                                    }
                                    {
                                        AuthenticationService.getRole() === "ROLE_KUPAC" &&
                                        <div className="text-center">
                                            <Button className="btn btn-primary" onClick={(e) => this.addStavka(e, artikal.id, 1)}>Додај у корпу</Button>
                                        </div>
                                    }
                                    </Col>
                                ))
                            }
                        </Row>
                    </Card.Body>
                </Card>
                {
                    AuthenticationService.getRole() === "ROLE_KUPAC" &&
                    <Card className={"border border-dark bg-dark text-white"}>
                        <Card.Header><h3>Ваша корпа</h3></Card.Header>
                        <Card.Body>
                            <Row>
                                {
                                stavkeLista.length === 0
                                    ?
                                    <div>Нема доступних, али боље проверите у конзоли :D :(</div>
                                    :
                                    stavkeLista.map((stavka)=>(
                                        <Col key={stavka.artikalId} xs={6} md={4} style={{marginBottom:"45px"}}>
                                            <h3>{stavka.artikalId}</h3>
                                            <h3>{stavka.kolicina}</h3>
                                        </Col>
                                    ))
                                }
                            </Row>
                        </Card.Body>
                    </Card>
                }
                <p>Хвала што купујете код нас!</p>
            </Container>
        );
    }
}