import React, { useState } from "react";
import {Button, Card, Col, Container, Form, Row} from "react-bootstrap";
import { AuthenticationService } from "../services/AuthenticationService";

const Login =() => {

    const [credentials, setCredentials] = useState({
        username: "",
        password: "",
    });

    // Funkcija koja prima naziv polja koje će se ažurirati, a potom i događaj koji je doveo do tog ažuriranja
    // Iz događaja je moguće izvući novu vrednost polja
    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;

        // ... - Destructuring assignment - omogućuje raspakivanje vrednosti objekata ili nizova
        // setCredentails će zameniti stanje novim objektom koji uzima vrednosti iz prethodnog stanja kredencijala
        // ali sa ažuriranom vrednošću [name] polja
        setCredentials({ ...credentials, [name]: val });
    };

    const login = async () => {
        await AuthenticationService.login(credentials);
    };

    return (
        <Container className={"kontejner"}>
            <Card style={{ width: '70rem', margin: 'auto'}} className={"border border-dark bg-dark text-white"}>
            <Row>
                <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                    <h1>Пријава</h1>
                    <hr/>
                    <Form>
                        <Form.Group>
                            <Form.Label>Корисничко име</Form.Label>
                            <Form.Control
                                type="text"
                                name="username"
                                value={credentials.username}
                                onChange={handleFormInputChange("username")}
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Лозинка</Form.Label>
                            <Form.Control
                                type="password"
                                name="password"
                                value={credentials.password}
                                onChange={handleFormInputChange("password")}
                            />
                        </Form.Group>
                        <Button variant="success" onClick={login}>
                            Пријави се
                        </Button>
                        <a className="btn btn-primary" href="/registracija" role="button">Региструј се</a>
                    </Form>
                </Col>
            </Row>
            </Card>
        </Container>
    );
};
export default Login;

