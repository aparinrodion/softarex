import React, {useState} from 'react';
import NavigationBar from "./NavigationBar";
import {Button, Form} from "react-bootstrap";
import axios from "axios";
import AuthService from "../service/AuthService";
import {useNavigate} from "react-router-dom";

const ChangePassword = () => {
    const navigate = useNavigate()
    const [validated, setValidated] = useState(false);
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            changePassword()
        }
        setValidated(true);
    };

    function changePassword() {
        if (newPassword !== confirmNewPassword) {
            alert("Password doesn't match")
        } else {
            let data =
                JSON.stringify({oldPassword, newPassword})
            axios.post("http://localhost:8080/users/change-password/", data, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token"),
                    'Content-Type': 'application/json',
                }
            })
                .then(() => {
                    navigate("/portal/login")
                    AuthService.logout();
                })
                .catch(() => alert("Wrong password"))
        }
    }

    return (
        <div className="vh-100 bg-light">
            <NavigationBar/>
            <div className={"d-flex justify-content-center mt-5"}>
                <Form className="bg-white w-25 border" onSubmit={handleSubmit} noValidate
                      validated={validated}>
                    <h3 className={"mx-5 mt-3"}>Change password</h3>
                    <hr/>
                    <div className={"px-3"}>
                        <Form.Group>
                            <Form.Label>Current password</Form.Label>
                            <Form.Control className='mb-2'
                                          required={true}
                                          onChange={e => setOldPassword(e.target.value)}
                                          type="password"/>
                            <Form.Label>New password</Form.Label>
                            <Form.Control className='mb-2'
                                          required={true}
                                          onChange={e => setNewPassword(e.target.value)}
                                          type="password"/>
                            <Form.Label>Confirm new password</Form.Label>
                            <Form.Control className='mb-2' onChange={e => setConfirmNewPassword(e.target.value)}
                                          required={true}
                                          type="password"/>
                        </Form.Group>
                        <Button type={'submit'} onSubmit={handleSubmit} className="mb-4 mt-2 w-25">Save</Button>
                    </div>
                </Form>
            </div>
        </div>
    );
};

export default ChangePassword;