import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import NavigationBar from "./NavigationBar";
import {Button, Col, Form, FormCheck, FormControl, FormSelect} from "react-bootstrap";
import {MDBTextArea} from "mdb-react-ui-kit"

const Questionnaire = () => {
    const navigate = useNavigate();
    const params = useParams();
    const [fields, setFields] = useState([])
    const [responses, setResponses] = useState(new Map());
    const [validated, setValidated] = useState(false);

    const updateResponses = (k, v) => {
        setResponses(responses.set(k, v))
    }

    function createInput(el) {
        switch (el.type) {
            case 'Single line text': {
                return createSingleLineInput(el)
            }
            case 'Multiline text': {
                return createMultiLineInput(el)
            }
            case 'Radio button': {
                return createRadioButtonInput(el)
            }
            case 'Checkbox': {
                return createCheckboxInput(el)
            }
            case 'Date': {
                return createDateInput(el)
            }
            case 'Combobox': {
                return createCombobox(el)
            }
        }
    }

    function createCombobox(el) {
        return (<div>
            <Form.Label>{el.label}</Form.Label>
            <FormSelect required={el.isRequired}
                        onChange={f => updateResponses(el.id, f.target.value)}
                        defaultValue={el.options[0].name}>
                {el.options.map(o => <option>{o.name}</option>)}
            </FormSelect>
            <Form.Control.Feedback type={"invalid"}>{el.label} is required field</Form.Control.Feedback>
        </div>)
    }

    function createDateInput(el) {
        return (<div>
            <Form.Label>{el.label}</Form.Label>
            <FormControl onChange={f => updateResponses(el.id, f.target.value)} required={el.isRequired}
                         type={"date"}/>
            <Form.Control.Feedback type={"invalid"}>{el.label} is required field</Form.Control.Feedback>
        </div>)
    }

    function createCheckboxInput(el) {
        return (<div>
            <FormCheck onChange={f => updateResponses(el.id, f.target.checked)} defaultChecked={false} type={"checkbox"}
                       required={el.isRequired}
                       label={el.label}/>
            <Form.Control.Feedback type={"invalid"}>{el.label} is required field</Form.Control.Feedback>
        </div>)
    }

    function createRadioButtonInput(el) {
        return (<div>
            <Form.Label>{el.label}</Form.Label>
            {el.options.map((option) => <FormCheck onChange={() => updateResponses(el.id, option.name)}
                                                   required={el.isRequired} name={el.label} type={"radio"}
                                                   label={option.name}/>)}
            <Form.Control.Feedback type={"invalid"}>{el.label} is required field</Form.Control.Feedback>
        </div>)
    }


    function createMultiLineInput(el) {
        return (<div>
                <Form.Label>{el.label}</Form.Label>
                <MDBTextArea onChange={f => updateResponses(el.id, f.target.value)} required={el.isRequired}
                             placeholder={el.name}/>
                <Form.Control.Feedback type={"invalid"}>{el.label} is required field</Form.Control.Feedback>
            </div>
        )
    }

    function createSingleLineInput(el) {
        return (<div><Form.Label>{el.label}</Form.Label>
                <Form.Control
                    onChange={f => updateResponses(el.id, f.target.value)}
                    required={el.isRequired}
                    type="text"
                    placeholder={el.name}
                />
                <Form.Control.Feedback type={"invalid"}>{el.label} is required field</Form.Control.Feedback>
            </div>
        )
    }

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            event.preventDefault();
            let responsesJson = [];
            for (let [key, value] of responses) {
                responsesJson.push({"fieldId": key, "answer": value})
            }
            let data = JSON.stringify({
                "questionnaireId": params.id,
                "fieldResponses": responsesJson
            })
            axios.post("http://localhost:8080/responses",
                data, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
            ).then(() => navigate("/portal/success"))
        }
        setValidated(true);
    };

    useEffect(() => {
        axios.get("http://localhost:8080/questionnaires/" + params.id, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(response => {
            setFields(response.data)
        })
    }, [params.id])
    return (
        <div className={"bg-light vh-100"}>
            <NavigationBar/>
            <div className={"d-flex justify-content-center p-5"}>
                <Form style={{width: '30%'}} className={"p-5 bg-white"} noValidate validated={validated}
                      onSubmit={handleSubmit}>
                    <Form.Group className={"w-100"} as={Col} md="4" controlId="validationCustom01">
                        {fields.filter(el => el.isActive === true)
                            .map((el) => createInput(el))}
                    </Form.Group>
                    <Button className={"mt-3"} onSubmit={handleSubmit} type="submit">Submit form</Button>
                </Form>
            </div>
        </div>
    );
};

export default Questionnaire;