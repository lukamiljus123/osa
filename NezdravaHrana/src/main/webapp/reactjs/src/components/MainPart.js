import React from 'react';
import {Button, Jumbotron} from "react-bootstrap";
import logo from "../pictures/logo5.png";
import {TokenService} from "../services/TokenService";
import {AuthenticationService} from "../services/AuthenticationService";

export default class SviArtikli extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-dark text-white text-center">
                <h1>Добро дошли у продавницу нездраве хране</h1>
                <br/>
                <img src={logo} alt="logo" />
                {
                    TokenService.getToken()
                        ?
                        <p>
                            {
                                AuthenticationService.getRole() === "ROLE_PRODAVAC" &&
                                <a className="btn btn-primary" href="/dodavanje-artikla" role="button">
                                    Додавање артикла
                                </a>
                            }
                            <Button style={{marginRight:"35px"}} className="btn btn-primary" onClick={() => AuthenticationService.logout()}>Одјави се</Button>
                        </p>
                        :
                        <p>
                            <a className="btn btn-primary" href="/prijava" role="button">Пријави се</a>
                            <a className="btn btn-primary" href="/registracija" role="button">Региструј се</a>
                        </p>
                }
            </Jumbotron>
        );
    }
}