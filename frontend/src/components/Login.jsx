import React, {useState} from 'react';
import authService from "../service/AuthService";
import {Button, Form, FormControl, FormGroup} from "react-bootstrap";
import {useNavigate} from "react-router-dom";

function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [validated, setValidated] = useState(false);

    function login() {
        authService.login(username, password).then((r) => {
                if (r.status === 200) {
                    navigate("/portal/fields")
                } else {
                    setValidated(false)
                    alert("Wrong email or password")
                }
            }
        );
    }

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            event.stopPropagation();
            login();
        }
        setValidated(true)
    };

    return (
        <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
            <Form className="p-3 my-5 d-flex flex-column w-25 border bg-white" noValidate validated={validated}
                  onSubmit={handleSubmit}>
                <div className="text-center">
                    <h1>Login</h1>
                </div>
                <FormGroup>
                    <FormControl onChange={e => setUsername(e.target.value)} required={true}
                                 placeholder={"Email"}
                                 type='email'/>
                    <FormControl className={"mt-4"} onChange={e => setPassword(e.target.value)} required={true}
                                 placeholder={"Password"}
                                 type='password'/>
                </FormGroup>
                <Button type={'submit'} onSubmit={handleSubmit} className="mt-4">LOG IN</Button>
                <div className="text-center mt-4">
                    <p>Don't have account <a href={""} onClick={() => navigate("/portal/registration")}>Sign Up</a></p>
                </div>
            </Form>
        </div>
    );
}

export default Login;