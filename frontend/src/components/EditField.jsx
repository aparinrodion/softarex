import React, {useEffect, useState} from 'react';
import {MDBInput, MDBTextArea} from "mdb-react-ui-kit";
import {Button, FormCheck, FormSelect, Modal} from "react-bootstrap";
import axios from "axios";

const EditField = (props) => {
    const [type, setType] = useState(" ");
    const [label, setLabel] = useState(" ");
    const [optionsValue, setOptionsValue] = useState(" ");
    const [isRequired, setRequired] = useState(true)
    const [isActive, setActive] = useState(true);

    const types = [
        "Multiline text", "Single line text", "Radio button", "Checkbox", "Combobox", "Date"
    ]
    const optionsTypes = [
        "Radio button", "Combobox"
    ]

    useEffect(() => {
        console.log("render")
        setType(props.data.type)
        setLabel(props.data.label)
        if (props.data.options !== undefined) {
            setOptionsValue(props.data.options.map(el => el.name).join('\n'))
        }
        setRequired(props.data.isRequired)
        setActive(props.data.isActive)
    }, [props.show]);

    function SelectOptions() {
        return types.map(type => <option>{type}</option>)
    }

    function updateField() {
        let options = optionsValue.split("\n").map(el => {
            let tempObj = {}
            tempObj["name"] = el;
            return tempObj
        });
        let data = JSON.stringify({
            "id": props.data.id,
            "questionnaireId": props.data.questionnaireId,
            label, type, options, isRequired, isActive
        })
        axios.post("http://localhost:8080/fields/update", data, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then()
    }

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Edit field
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <label>Label:</label>
                <MDBInput wrapperClass={"mb-3"} onChange={el => setLabel(el.target.value)}
                          defaultValue={props.data.label}/>
                <label>Type:</label>
                <FormSelect
                    value={type}
                    onChange={el => {
                        setType(el.target.value)
                    }}>
                    <SelectOptions/>
                </FormSelect>
                <div hidden={!optionsTypes.includes(type)}>
                    <label>Options:</label>
                    <MDBTextArea rows={3} onChange={el => setOptionsValue(el.target.value)}
                                 defaultValue={props.data.options === undefined
                                     ? " " : props.data.options.map(el => el.name).join('\n')}/>
                </div>
                <div className={"text-center"}>
                    <div className={"d-inline-flex mt-3"}>
                        <FormCheck checked={isRequired} onChange={el => setRequired(el.target.checked)}
                                   className={"mx-4"}
                                   label={"Required"}/>
                        <FormCheck checked={isActive} onChange={el => setActive(el.target.checked)}
                                   className={"mx-4"}
                                   label={"Active"}/>
                    </div>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button className="btn-light" onClick={props.onHide}>Cancel</Button>
                <Button onClick={() => {
                    updateField()
                    props.onHide()
                }}>Save</Button>
            </Modal.Footer>
        </Modal>
    );
};

export default EditField;