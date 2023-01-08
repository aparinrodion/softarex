import axios from "axios";

const API_URL = "http://localhost:8080/users/";

class AuthService {

    login(username, password) {
        return axios
            .post(API_URL + "login", {
                username,
                password
            })
            .catch(reason => {
                return reason.response
            })
            .then(response => {
                if (response.data.token && response.status === 200) {
                    localStorage.setItem("token", response.data.token);
                }
                return response;
            });
    }

    logout() {
        localStorage.removeItem("token");
    }

    tokenExists() {
        if (localStorage.getItem("token") === null)
            window.location.href = "http://localhost:3000/portal/login"
    }

    register(data) {
        axios.post(API_URL + "register",
            data
            , {
                headers: {
                    'Content-Type': 'application/json',
                }
            }).then(r => console.log(r));
    }
}

export default new AuthService()