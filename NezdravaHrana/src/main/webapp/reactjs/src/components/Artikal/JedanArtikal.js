import React from 'react';
import {Card, Col, Container, Image, Row} from "react-bootstrap";
import MainPart from "../MainPart";
import NezdravaHranaAxiosClient from "../../services/clients/NezdravaHranaAxiosClient";

export default class JedanArtikal extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            artikal : {}
        };
    }

    componentDidMount() {
        this.findArtikal();
    }

    findArtikal() {
        NezdravaHranaAxiosClient.get("http://localhost:8080/artikli/" + this.props.match.params.id)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    artikal: data
                });
            });
    };

    render() {
        const {artikal} = this.state;

        return (
            <Container className="kontejner">
                <MainPart/>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>{artikal.naziv}</h3></Card.Header>
                    <Card.Body>
                        <Row>
                            <Col xs={6} md={4}>
                                <h5>Цена: {artikal.cena} РСД</h5>
                                <h5>Опис: {artikal.opis}</h5>
                                <h5>Продавац: {artikal.prodavacNaziv}</h5>
                                <Image className={"slikaArtikla"} src={artikal.putanjaSlike} thumbnail />
                            </Col>
                            <Col xs={6} md={4}>

                            </Col>
                        </Row>
                    </Card.Body>
                </Card>
                <p>Хвала што купујете код нас</p>
            </Container>
        );
    }
}