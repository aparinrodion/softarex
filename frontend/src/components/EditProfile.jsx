import React, {useEffect, useState} from 'react';
import PhoneInput from "react-phone-input-2";
import NavigationBar from "./NavigationBar";
import {Button, Form} from "react-bootstrap";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const EditProfile = () => {
    const navigate = useNavigate();
    let [firstName, setFirstName] = useState('')
    let [lastName, setLastName] = useState('')
    let [email, setEmail] = useState('')
    let [phoneNumber, setPhoneNumber] = useState('')
    const [validated, setValidated] = useState(false);

    function updateUser() {
        let newUser = {
            firstName,
            lastName,
            email,
            phoneNumber
        }
        axios.post("http://localhost:8080/update", newUser, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(() => {
            navigate("/portal/fields")
        })
    }

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            updateUser()
        }
        setValidated(true);
    };

    function loadProfile() {
        axios.get("http://localhost:8080/profile", {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(
            response => {
                setFirstName(response.data.firstName)
                setLastName(response.data.lastName)
                setEmail(response.data.email)
                setPhoneNumber(response.data.phoneNumber)
            }
        )
    }

    useEffect(() => {
        loadProfile()
    }, [])

    return (
        <div className="vh-100 bg-light">
            <NavigationBar/>
            <div className={"d-flex justify-content-center mt-5"}>
                <Form className="bg-white w-25 border" onSubmit={handleSubmit} noValidate
                      validated={validated}>
                    <h3 className={"mx-5 mt-3"}>Edit profile</h3>
                    <hr/>
                    <div className={"px-3"}>
                        <Form.Group>
                            <Form.Label>First name</Form.Label>
                            <Form.Control value={firstName} className='mb-2'
                                          onChange={e => setFirstName(e.target.value)}
                                          type="text"/>
                            <Form.Label>Last Name</Form.Label>
                            <Form.Control value={lastName} className='mb-2'
                                          onChange={e => setLastName(e.target.value)}
                                          type="text"/>
                            <Form.Label>Email</Form.Label>
                            <Form.Control value={email} className='mb-2' onChange={e => setEmail(e.target.value)}
                                          required={true}
                                          type="email"/>
                            <Form.Control.Feedback type={"invalid"}>Email is required</Form.Control.Feedback>
                            <Form.Label>Phone number</Form.Label>
                            <PhoneInput
                                value={phoneNumber}
                                inputStyle={{width: '100%'}}
                                containerStyle={{marginBottom: '2%'}}
                                country={'by'}
                                onChange={setPhoneNumber}
                            />
                        </Form.Group>
                        <Button type={'submit'} onSubmit={handleSubmit} className="mb-4 mt-2 w-25">Save</Button>
                    </div>
                </Form>
            </div>
        </div>
    );
};

export default EditProfile;