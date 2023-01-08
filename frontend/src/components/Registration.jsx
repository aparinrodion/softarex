import React, {useState} from 'react';
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'
import {Button, Form, FormControl, FormGroup} from "react-bootstrap";
import AuthService from "../service/AuthService";
import {useNavigate} from "react-router-dom";

const Registration = () => {
    const navigate = useNavigate();
    let [email, setEmail] = useState('');
    let [password, setPassword] = useState('');
    let [confirmPassword, setConfirmPassword] = useState('');
    let [firstName, setFirstName] = useState('');
    let [lastName, setLastName] = useState('');
    let [phoneNumber, setPhoneNumber] = useState('');
    const [validated, setValidated] = useState(false);

    function register() {
        if (password === confirmPassword) {
            let newUser = JSON.stringify({
                email,
                password,
                firstName,
                lastName,
                phoneNumber
            })
            console.log(newUser)
            AuthService.register(newUser)
            navigate("/portal/login")
        } else {
            alert("Password doesn't match")
        }
    }

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            register()
        }
        setValidated(true);
    };

    return (
        <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
            <Form className="p-3 my-5 d-flex flex-column w-25 border rounded bg-white"
                  noValidate validated={validated}
                  onSubmit={handleSubmit}
            >
                <div className="text-center">
                    <h1>Sign up</h1>
                </div>
                <FormGroup>
                    <FormControl required={true} className='mb-2' onChange={e => setEmail(e.target.value)} type="email"
                                 placeholder="Email"/>
                    <FormControl required={true} className='mb-2' onChange={e => setPassword(e.target.value)}
                                 type="password"
                                 placeholder="Password"/>
                    <FormControl required={true} className='mb-2' onChange={e => setConfirmPassword(e.target.value)}
                                 type="password" placeholder="Confirm password"/>
                    <FormControl className='mb-2' onChange={e => setFirstName(e.target.value)} type="text"
                                 placeholder="First name"/>
                    <FormControl className='mb-2' onChange={e => setLastName(e.target.value)} type="text"
                                 placeholder="Last name"/>
                    <PhoneInput
                        inputStyle={{width: '100%'}}
                        containerStyle={{marginBottom: '2%'}}
                        country={'by'}
                        onChange={setPhoneNumber}
                    />
                </FormGroup>
                <Button type={'submit'} className="mb-4 mu-2">Sign up</Button>

                <div className="text-center">
                    <p>Already have account? <a href={""}
                                                onClick={() => navigate("/portal/login")}>Log in</a>
                    </p>
                </div>
            </Form>
        </div>
    );
};

export default Registration;