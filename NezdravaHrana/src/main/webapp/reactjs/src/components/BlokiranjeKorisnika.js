import React, {Component} from 'react';
import {Card, Container, Table} from 'react-bootstrap';
import NezdravaHranaAxiosClient from "../services/clients/NezdravaHranaAxiosClient";

export default class BlokiranjeKorisnika extends Component{
    constructor(props) {
        super(props);
        this.state={
            korisniciLista : []
        };
    }
    componentDidMount() {
        this.findAllKorisnici();
    }

    findAllKorisnici() {
        NezdravaHranaAxiosClient.get("http://localhost:8080/korisnici/blokiranje")
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    korisniciLista: data
                });
            });
    };

    blokiraj(id) {
        NezdravaHranaAxiosClient.put("http://localhost:8080/korisnici/blokiraj/" + id)
            .then(response => {
                if(response.data !=null){
                    setTimeout(()=> this.setState({"show":false}),3000)
                }
                this.findAllKorisnici();
            });
    }

    render() {
        const {korisniciLista} = this.state;

        return (
            <Container className="kontejner">
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <h3>Листа неблокираних корисника</h3>
                    </Card.Header>

                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                                <tr align="center">
                                    <th>Корисничко име</th>
                                </tr>
                            </thead>
                            <tbody>
                            {korisniciLista.length===0 ?
                                <tr align="center">
                                    <td colSpan="2">Нема доступних</td>
                                </tr> :
                                korisniciLista.map((korisnik)=>(
                                    <tr key={korisnik.id}>
                                        <td align="center">{korisnik.username}</td>
                                        <td align="center">
                                            <button onClick={() => { this.blokiraj(korisnik.id) }} type="button" className="btn btn-danger">Блокирај</button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}