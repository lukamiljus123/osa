import {TokenService} from "./TokenService";
import NezdravaHranaAxiosClient from "./clients/NezdravaHranaAxiosClient";
import Swal from "sweetalert2";

export const AuthenticationService = {
    login,
    logout,
    getRole
};

async function login(userCredentials) {
    try {
        const response = await NezdravaHranaAxiosClient.post(
            "http://localhost:8080/korisnici/prijava",
            userCredentials
        );
        const decoded_token = TokenService.decodeToken(response.data);
        if (decoded_token) {
            TokenService.setToken(response.data);

            window.location.assign("/");
        } else {
            console.error("Invalid token");
        }
    } catch (error) {
        await Swal.fire({
            icon: 'error',
            title: 'Ох не 😩',
            text: 'Негде сте погрешили 😕',
        })
    }
}

function logout() {
    TokenService.removeToken();
    window.location.assign("/");
}

function getRole() {
    const token = TokenService.getToken();
    const decoded_token = token ? TokenService.decodeToken(token) : null;
    if (decoded_token) {
        return decoded_token.role.authority;
    } else {
        return null;
    }
}