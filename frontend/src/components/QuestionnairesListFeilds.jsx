import React, {useState} from 'react';
import QuestionnairesList from "./QuestionnairesList";
import {useNavigate} from "react-router-dom";
import NavigationBar from "./NavigationBar";
import {Button, Form, FormControl, FormGroup} from "react-bootstrap";
import axios from "axios";

const QuestionnairesListFields = () => {
    const navigate = useNavigate();
    const [name, setName] = useState(' ')
    const [validated, setValidated] = useState(false);


    function navigateToResponses(id) {
        navigate("/portal/questionnaires/" + id + "/fields");
    }

    function createQuestionnaire() {
        axios.post("http://localhost:8080/questionnaires", JSON.stringify({"name": name}), {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token"),
                'Content-Type': 'application/json'
            }
        })
    }

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            createQuestionnaire();
        }
        setValidated(true)
    };

    return (
        <div className={"bg-light vh-100"}>
            <NavigationBar/>
            <div className={"justify-content-center mt-5 d-flex"}>
                <div
                    className={"d-flex w-100 flex-column align-items-center"}>
                    <Form className={"d-flex mx-3 my-2 w-50"} noValidate validated={validated}
                          onSubmit={handleSubmit}>
                        <FormGroup>
                            <FormControl type={"text"} placeholder={"Create new questionnaire"}
                                         required={true}
                                         onChange={el => setName(el.target.value)}/>
                        </FormGroup>
                        <Button className={"mx-1"} type={'submit'}>Create</Button>
                    </Form>
                    <QuestionnairesList onClick={navigateToResponses}/>
                </div>
            </div>
        </div>
    );
};

export default QuestionnairesListFields;