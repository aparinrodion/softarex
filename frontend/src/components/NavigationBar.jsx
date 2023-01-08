import React from 'react';
import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import jwt_decode from "jwt-decode"
import authService from "../service/AuthService";
import {useNavigate} from "react-router-dom";

const NavigationBar = () => {
    const navigate = useNavigate();

    function logout() {
        authService.logout();
        navigate("/portal/login/")
    }

    function DropdownList() {
        if (localStorage.getItem("token") !== null)
            return <NavDropdown className='me-5' title={jwt_decode(localStorage.getItem("token")).name}>
                <NavDropdown.Item onClick={() => navigate("/portal/edit")}>Edit
                    profile</NavDropdown.Item>
                <NavDropdown.Item onClick={() => navigate("/portal/password")}>Change
                    password</NavDropdown.Item>
                <NavDropdown.Item onClick={logout}>Log out</NavDropdown.Item>
            </NavDropdown>
    }

    return (
        <Navbar bg={"white"} expand="lg">
            <Container fluid>
                <Navbar.Brand href="#">Questionnaire Portal</Navbar.Brand>
                <Navbar.Collapse className='justify-content-end'>
                    <Nav
                        className="my-0"
                        style={{
                            maxHeight: '100px',
                            marginRight: '10%'
                        }}
                        navbarScroll>
                        <Nav.Link className='me-5' onClick={() => navigate("/portal/fields")}>Fields</Nav.Link>
                        <Nav.Link className='me-5' onClick={() => navigate("/portal/responses")}>Responses</Nav.Link>
                        <DropdownList/>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>

    );
};

export default NavigationBar;