import React from "react";
import {Card, Form, Col, Button} from "react-bootstrap";
import MyToast from "../components/MyToast";
import NezdravaHranaAxiosClient from "../services/clients/NezdravaHranaAxiosClient";

class RegisterPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.userChange = this.userChange.bind(this);
        this.submitUser = this.submitUser.bind(this);
    }

    initialState = {
        tipKorisnika: 'KUPAC', username:'', password:'', password2:'', ime:'', prezime:'', adresa:'',
        email:'', naziv:''
    }

    submitUser = event => {
        event.preventDefault();

        if (this.state.password !== this.state.password2) {
            alert("Лозинке се не подударају!");
            return;
        }

        const user = {
            username: this.state.username,
            password: this.state.sifra,
            password2: this.state.sifra2,
            ime: this.state.ime,
            prezime: this.state.prezime,
            adresa: this.state.adresa,
            email: this.state.email,
            naziv: this.state.naziv,
            blokiran: false
        };

        let isProdavac = "true";
        if (this.state.tipKorisnika !== "PRODAVAC") {
            isProdavac = "false";
        }

        NezdravaHranaAxiosClient.post("http://localhost:8080/korisnici/registracija/" + isProdavac, user)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}),3000);
                } else {
                    this.setState({"show":false});
                }
            });
        this.setState(this.initialState);
    }

    userChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {

        const {tipKorisnika, username, password, password2, ime, prezime, adresa, email, naziv} = this.state;

        return(
            <div style={{padding: '150px'}}>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <MyToast children={{show: this.state.show, message:"Успешно сте се регистровали!"}}/>
                </div>
                <Card style={{ width: '69rem', margin: 'auto'}} className={"border border-dark bg-dark text-white"}>
                    <Card.Header style={{"textAlign":"center"}}>
                        <h3>Регистрација</h3>
                    </Card.Header>

                    <Form onSubmit={this.submitUser} id="userFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <Form.Label>Тип корисника</Form.Label>
                                    <Form.Control as="select" name = "tipKorisnika" autoComplete = "off"
                                                  value={tipKorisnika}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"} >
                                        <option defaultValue="KUPAC">Купац</option>
                                        <option value="PRODAVAC">Продавац</option>
                                    </Form.Control>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridKorisnicko"}>
                                    <Form.Label>Корисничко име</Form.Label>
                                    <Form.Control required type="text" name = "username" autoComplete = "off"
                                                  value={username}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси корисничко име"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridSifra"}>
                                    <Form.Label>Лозинка</Form.Label>
                                    <Form.Control required type="password" minLength="6" name = "password" autoComplete = "off"
                                                  value={password}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси лозинку"/>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Потврда лозинке</Form.Label>
                                    <Form.Control required type="password" minLength="6" name = "password2" autoComplete = "off"
                                                  value={password2}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Понови лозинку"/>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridIme"}>
                                    <Form.Label>Име</Form.Label>
                                    <Form.Control required type="text" name = "ime" autoComplete = "off"
                                                  value={ime}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси име"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridPrezime"}>
                                    <Form.Label>Презиме</Form.Label>
                                    <Form.Control required type="text" name = "prezime" autoComplete = "off"
                                                  value={prezime}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси презиме"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridAdresa"}>
                                    <Form.Label>Адреса</Form.Label>
                                    <Form.Control required type="text" name = "adresa" autoComplete = "off"
                                                  value={adresa}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси адресу"/>
                                </Form.Group>
                            </Form.Row>
                            {tipKorisnika === "PRODAVAC" &&
                                <Form.Row>
                                <Form.Group as={Col} controlId={"formGridEmail"}>
                                    <Form.Label>Емаил</Form.Label>
                                    <Form.Control required type="email" name = "email" autoComplete = "off"
                                                  value={email}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси емаил"/>
                                </Form.Group>
                                <Form.Group as={Col}>
                                    <Form.Label>Назив фирме</Form.Label>
                                    <Form.Control required type="text" name = "naziv" autoComplete = "off"
                                                  value={naziv}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder="Унеси назив фирме"/>
                                </Form.Group>
                                </Form.Row>
                            }
                        </Card.Body>
                        <Card.Footer style={{"textAlign":"center"}}>
                            <Button size={"sm"} variant={"success"} type={"submit"}>
                                Потврди
                            </Button>
                        </Card.Footer>
                        </Form>
                </Card>
            </div>
        );
    }
}

export default RegisterPage;