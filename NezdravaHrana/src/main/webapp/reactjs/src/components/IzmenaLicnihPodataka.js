import React from "react";
import {Card, Form, Col, Button} from "react-bootstrap";
import MyToast from "./Toasts/MyToast";
import NezdravaHranaAxiosClient from "../services/clients/NezdravaHranaAxiosClient";
import Swal from "sweetalert2";

export default class IzmenaLicnihPodataka extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.state.show = false;
        this.userChange = this.userChange.bind(this);
        this.submitUser = this.submitUser.bind(this);
    }

    componentDidMount() {
        this.findKorisnikById();
    }

    initialState = {
        username:'', ime:'', prezime:'', adresa:'',
        email:'', naziv:'',
        novaLozinka: '',
        staraLozinka: ''
    }

    findKorisnikById () {
        NezdravaHranaAxiosClient.get("http://localhost:8080/korisnici/ulogovani")
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    id: data.id,
                    username: data.username,
                    password: data.password,
                    novaLozinka: '',
                    staraLozinka: '',
                    ime: data.ime,
                    prezime: data.prezime,
                    adresa: data.adresa,
                    email: data.email,
                    naziv: data.naziv
                });
            });
    }

    submitUser = event => {
        event.preventDefault();

        /*console.log(this.state.password);
        console.log(this.state.novaLozinka);
        console.log(this.state.staraLozinka);

        if (this.state.novaLozinka !== '')
        {
            if (this.state.password !== this.state.staraLozinka) {
                alert("Нисте добро унели стару лозинку");
                return;
            }
        }*/

        const user = {
            id: this.state.id,
            username: this.state.username,
            password: this.state.novaLozinka,
            ime: this.state.ime,
            prezime: this.state.prezime,
            adresa: this.state.adresa,
            email: this.state.email,
            naziv: this.state.naziv,
        };

        try {
            NezdravaHranaAxiosClient.put("http://localhost:8080/korisnici", user)
            .then(response => {
                if (response.data != null) {
                    this.setState({"show":true});
                    setTimeout(() => this.setState({"show":false}),3000);
                } else {
                    this.setState({"show":false});
                }
            });
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: 'Ох не 😩',
                text: 'Негде сте погрешили 😕',
            })
        }
        this.setState(this.initialState);
    }

    userChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {

        const {username, novaLozinka, staraLozinka, ime, prezime, adresa, email, naziv} = this.state;

        return(
            <div style={{padding: '150px'}}>
                <div style={{"display":this.state.show ? "block" : "none"}}>
                    <MyToast children={{show: this.state.show, message:"Успешно сте изменили личне податке!"}}/>
                </div>
                <Card style={{ width: '69rem', margin: 'auto'}} className={"border border-dark bg-dark text-white"}>
                    <Card.Header style={{"textAlign":"center"}}>
                        <h3>Измена личних података</h3>
                    </Card.Header>

                    <Form onSubmit={this.submitUser} id="userFormId">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridKorisnicko"}>
                                    <Form.Label>Корисничко име</Form.Label>
                                    <Form.Control required disabled type="text" name = "username" autoComplete = "off"
                                                  value={username}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси корисничко име"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridIme"}>
                                    <Form.Label>Име</Form.Label>
                                    <Form.Control required type="text" name = "ime" autoComplete = "off"
                                                  value={ime}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси име"/>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridPrezime"}>
                                    <Form.Label>Презиме</Form.Label>
                                    <Form.Control required type="text" name = "prezime" autoComplete = "off"
                                                  value={prezime}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси презиме"/>
                                </Form.Group>
                                {adresa !== undefined && adresa !== "" && adresa !== null &&
                                    <Form.Group as={Col} controlId={"formGridAdresa"}>
                                        <Form.Label>Адреса</Form.Label>
                                        <Form.Control required type="text" name = "adresa" autoComplete = "off"
                                                      value={adresa}
                                                      onChange={this.userChange}
                                                      className={"bg-dark text-white"}
                                                      placeholder = "Унеси адресу"/>
                                    </Form.Group>
                                }
                            </Form.Row>
                            {email !== undefined && email !== "" && email !== null &&
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
                            <Form.Row>
                                <Form.Group as={Col} controlId={"formGridNovaLozinka"}>
                                    <Form.Label>Нова лозинка</Form.Label>
                                    <Form.Control type="password" name = "novaLozinka" autoComplete = "off"
                                                  value={novaLozinka}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси нову лозинку"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridStaraLozinka"}>
                                    <Form.Label>Стара лозинка</Form.Label>
                                    <Form.Control type="password" name = "staraLozinka" autoComplete = "off"
                                                  value={staraLozinka}
                                                  onChange={this.userChange}
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унеси стару лозинку"/>
                                </Form.Group>
                            </Form.Row>
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