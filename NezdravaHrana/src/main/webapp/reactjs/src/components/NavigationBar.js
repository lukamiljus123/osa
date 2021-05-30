import React from 'react';
import {Navbar} from "react-bootstrap";
import logo from "../pictures/logo5.png";
import mirkomarket from "../pictures/mirkomarket.png";

export default class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar className="navigacija" bg="dark" variant="dark">
                <Navbar.Brand><img id="logo" src={logo} width={35} height={35} alt={logo}/> Нездрава храна </Navbar.Brand>
                <a className="btn btn-primary" href="/" role="button">
                    Почетна
                </a>
                <div style={{fontSize:"20px"}} className="ml-auto mr-1">
                    Званични спонзор:
                    <img style={{marginLeft:"15px"}} id="logo" src={mirkomarket} width={231} height={49} alt={mirkomarket}/>
                </div>
            </Navbar>
        );
    }
}