import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import React from "react";
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import SviArtikli from "./components/Artikal/SviArtikli";
import JedanArtikal from "./components/Artikal/JedanArtikal";
import RegisterPage from "./components/RegisterPage";
import Login from "./components/Login";
import {PrivateRoute} from "./PrivateRoute";
import IzmenaArtikla from "./components/Artikal/IzmenaArtikla";

function App() {
  return (
      <Router>
        <NavigationBar/>

        <Switch>
            <Route path="/prijava">
                <Login/>
            </Route>

            <Route path="/registracija">
                <RegisterPage/>
            </Route>

            <PrivateRoute
                exact
                path="/artikli/:id"
                component={JedanArtikal}
                roles={["ROLE_ADMINISTRATOR", "ROLE_PRODAVAC", "ROLE_KUPAC"]}
            />

            <PrivateRoute
                exact
                path="/izmena-artikla/:id"
                component={IzmenaArtikla}
                roles={["ROLE_PRODAVAC"]}
            />

            <PrivateRoute
                exact
                path="/dodavanje-artikla"
                component={IzmenaArtikla}
                roles={["ROLE_PRODAVAC"]}
            />

            <Route path="/">
                <SviArtikli/>
            </Route>
        </Switch>

        <Footer/>
      </Router>
  );
}


export default App;
