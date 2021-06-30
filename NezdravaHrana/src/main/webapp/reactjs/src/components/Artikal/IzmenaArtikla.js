import React from "react";
import {Card, Form, Col, Button} from "react-bootstrap";
import MyToast from "../Toasts/MyToast";
import NezdravaHranaAxiosClient from "../../services/clients/NezdravaHranaAxiosClient";
import ShallNotToast from "../Toasts/ShallNotToast";

export default class IzmenaArtikla extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.state.shallShow = false;
        this.artikalChange = this.artikalChange.bind(this);
        this.submitArtikal = this.submitArtikal.bind(this);
    }

    componentDidMount() {
        const artikalId = this.props.match.params.id;
        if (artikalId) {
            this.findArtikalById(artikalId);
        }
    }

    initialState = {
        naziv: '',
        opis: '',
        cena: '',
        putanjaSlike: ''
    }

    findArtikalById = (artikalId) => {
        NezdravaHranaAxiosClient.get("http://localhost:8080/artikli/" + artikalId)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    id: data.id,
                    naziv: data.naziv,
                    opis: data.opis,
                    cena: data.cena,
                    putanjaSlike: data.putanjaSlike
                });
            });
    }

    submitArtikal = event => {
        event.preventDefault();

        const artikal = {
            id: this.props.match.params.id,
            naziv: this.state.naziv,
            opis: this.state.opis,
            cena: this.state.cena,
            putanjaSlike: this.state.putanjaSlike
        };

        if (this.state.id) {
            NezdravaHranaAxiosClient.put("http://localhost:8080/artikli", artikal)
                .then(response => {
                    if (response.data != null) {
                        this.setState({"show":true});
                        setTimeout(() => this.setState({"show":false}),3000);
                    } else {
                        this.setState({"show":false});
                    }
                }).catch((error) => {
                    this.setState({"shallShow":true});
                    setTimeout(() => this.setState({"shallShow":false}),3000);
            });
        } else {
            NezdravaHranaAxiosClient.post("http://localhost:8080/artikli", artikal)
                .then(response => {
                    if (response.data != null) {
                        this.setState({"show":true});
                        setTimeout(() => this.setState({"show":false}),3000);
                    } else {
                        this.setState({"show":false});
                    }
                });
        }
    }

    artikalChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {

        const {naziv, opis, cena, putanjaSlike} = this.state;

        return(
            <div style={{padding: '150px'}}>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    {
                        this.state.id
                        ?
                        <MyToast children={{show: this.state.show, message:"Успешно сте изменили артикал!"}}/>
                        :
                        <MyToast children={{show: this.state.show, message:"Успешно сте додали артикал!"}}/>
                    }
                </div>
                <div style={{"display":this.state.shallShow ? "block" : "none"}}>
                    <ShallNotToast children={{shallShow: this.state.shallShow, message:"Добар покушај"}}/>
                </div>
                <Card style={{ width: '69rem', margin: 'auto'}} className={"border border-dark bg-dark text-white"}>
                    <Card.Header style={{"textAlign":"center"}}>
                        {
                            this.state.id
                            ?
                            <h3>Измена артикла</h3>
                            :
                            <h3>Додавање артикла</h3>
                        }
                    </Card.Header>

                    <Form onSubmit={this.submitArtikal} id="userFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridKorisnicko"}>
                                    <Form.Label>Назив</Form.Label>
                                    <Form.Control required type="text" name = "naziv" autoComplete = "off"
                                                  value={naziv}
                                                  onChange={this.artikalChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унесите назив"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridPrezime"}>
                                    <Form.Label>Цена</Form.Label>
                                    <Form.Control required type="number" min-value = "0" name = "cena" autoComplete = "off"
                                                  value={cena}
                                                  onChange={this.artikalChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унесите цену"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridIme"}>
                                    <Form.Label>Опис</Form.Label>
                                    <Form.Control required type="text" name = "opis" autoComplete = "off"
                                                  value={opis}
                                                  onChange={this.artikalChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унесите опис"/>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridPrezime"}>
                                    <Form.Label>Линк до слике</Form.Label>
                                    <Form.Control required type="text" name = "putanjaSlike" autoComplete = "off"
                                                  value={putanjaSlike}
                                                  onChange={this.artikalChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унесите линк до слике"/>
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"center"}}>
                            <Button size={"sm"} variant={"success"} type={"submit"}>
                                {
                                    this.state.id
                                    ?
                                    <h4>Измени</h4>
                                    :
                                    <h4>Додај</h4>
                                }
                            </Button>
                        </Card.Footer>
                        </Form>
                </Card>
            </div>
        );
    }
}