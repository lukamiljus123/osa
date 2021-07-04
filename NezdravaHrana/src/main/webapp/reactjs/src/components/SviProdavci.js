import React from 'react';
import {Card, Container, Row} from "react-bootstrap";
import NezdravaHranaAxiosClient from "../services/clients/NezdravaHranaAxiosClient";
import MainPart from "./MainPart";
import {AuthenticationService} from "../services/AuthenticationService";

export default class SviProdavci extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            prodavciLista : []
        };
    }

    componentDidMount() {
        if (AuthenticationService.getRole() === "ROLE_PRODAVAC") {
            window.location.href = "http://localhost:3000/artikli-prodavca/1";
        }
        this.findAllProdavci();
    }

    findAllProdavci() {
        let url = "http://localhost:8080/prodavci";
        
        NezdravaHranaAxiosClient.get(url)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    prodavciLista: data
                });
            });
    };

    render() {
        const {prodavciLista} = this.state;

        return (
            <Container className="kontejner">
                <MainPart/>
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Листа продаваца</h3></Card.Header>
                    <Card.Body>
                        <Row>
                            {
                                prodavciLista.length === 0
                                ?
                                <div>Нема доступних</div>
                                :
                                <ul>
                                    {prodavciLista.map((prodavac)=>(
                                            <li>
                                                <a href={"/artikli-prodavca/" + prodavac.id}>
                                                    Артикли продавца {prodavac.ime}, {prodavac.naziv}
                                                </a>
                                            </li>
                                    ))}
                                </ul>
                            }
                        </Row>
                    </Card.Body>
                </Card>
                <p>Хвала што купујете код нас</p>
            </Container>
        );
    }
}