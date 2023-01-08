import React, {useState} from 'react';
import {MDBInput, MDBTextArea} from "mdb-react-ui-kit";
import {Button, FormCheck, FormSelect, Modal} from "react-bootstrap";
import axios from "axios";

const AddField = (props) => {
    const [label, setLabel] = useState('');
    const [type, setType] = useState('Single line text');
    const [optionsValue, setOptionsValue] = useState('');
    const [isRequired, setRequired] = useState(false);
    const [isActive, setActive] = useState(false)

    const types = [
        "Multiline text", "Single line text", "Radio button", "Checkbox", "Combobox", "Date"
    ]
    const optionsTypes = [
        "Radio button", "Combobox"
    ]

    function clearStates() {
        setLabel('');
        setType('');
        setOptionsValue('')
        setRequired(false)
        setActive(false)
    }

    function SelectOptions() {
        return types.map(type => <option>{type}</option>)
    }

    function addField() {
        let options = optionsValue.split("\n").filter(el => el.length > 0).map(el => {
            let tempObj = {}
            tempObj["name"] = el;
            return tempObj
        });
        let data = JSON.stringify({
                label,
                type,
                isActive,
                isRequired,
                "questionnaireId": props.questionnaireId,
                options
            }
        )
        axios.post("http://localhost:8080/fields",
            data, {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }
        ).then(() => {
                clearStates()
            }
        )
    }

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton onClick={clearStates}>
                <Modal.Title id="contained-modal-title-vcenter">
                    Add field
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <label>Label:</label>
                <MDBInput id={"label-input"} wrapperClass={"mb-3"} onChange={el => setLabel(el.target.value)}
                          value={label}/>
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
                    <MDBTextArea rows={3} onChange={el => setOptionsValue(el.target.value)}/>
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
                <Button className="btn-light" onClick={() => {
                    clearStates()
                    props.onHide()
                }}>Cancel</Button>
                <Button onClick={() => {
                    addField()
                    clearStates()
                    props.onHide()
                }}>Add</Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AddField;