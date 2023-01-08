import React, {useEffect, useState} from 'react';
import NavigationBar from "./NavigationBar";

import "bootstrap/dist/css/bootstrap.css";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";

import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory from "react-bootstrap-table2-paginator"
import {FiEdit, FiTrash} from "react-icons/fi";
import {useParams} from "react-router-dom";
import axios from "axios";
import EditField from "./EditField";
import {Button} from "react-bootstrap";
import AddField from "./AddField";
import AuthService from "../service/AuthService";

const Fields = () => {
    const [modalShowEdit, setModalShowEdit] = useState(false);
    const [modalShowAdd, setModalShowAdd] = useState(false);
    const [clickedRow, setClickedRow] = useState({});
    const [fields, setFields] = useState([]);
    const params = useParams();

    function handleClick(row) {
        setClickedRow(row)
        setModalShowEdit(true);
    }

    const buttons = (cell, row) => {
        return (<div>
            <FiEdit onClick={() => {
                handleClick(row)
            }}/>
            <FiTrash onClick={() => deleteRow(row)}/>
        </div>)
    }

    function deleteRow(row) {
        axios.delete("http://localhost:8080/fields/delete/" + row.id, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(() => {
            loadFields()
        })
    }

    function loadFields() {
        axios.get("http://localhost:8080/questionnaires/" + params.id, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(response => {
            setFields(response.data)
        })
    }

    useEffect(() => {
        AuthService.tokenExists()
        loadFields()
    }, [modalShowEdit, modalShowAdd])

    const columns = [{
        dataField: 'label',
        text: 'Label',
    }, {
        dataField: 'type',
        text: 'Type'
    }, {
        dataField: 'isRequired',
        text: 'Required'
    }, {
        dataField: 'isActive',
        text: 'Is Active',
    }, {
        dataField: "buttons",
        text: "",
        formatter: buttons
    }
    ];

    return (
        <div className={"bg-light vh-100"}>
            <NavigationBar/>
            <div className={"d-flex justify-content-center mt-5"}>
                <div className="w-50 bg-white border">
                    <div className={"d-flex justify-content-between mx-3 my-2"}>
                        <h4>Fields</h4>
                        <Button onClick={() => setModalShowAdd(true)}>Add field</Button>
                    </div>
                    <BootstrapTable keyField={'id'} data={fields} columns={columns}
                                    bordered={false}
                                    striped={true}
                                    pagination={paginationFactory({
                                        sizePerPage: 3,
                                        hideSizePerPage: true,
                                        withFirstAndLast: false,
                                        alwaysShowAllBtns: true,
                                        showTotal: true,
                                        hidePageListOnlyOnePage: true
                                    })}/>
                </div>
                <EditField
                    title={'Edit'}
                    show={modalShowEdit}
                    onHide={() => setModalShowEdit(false)}
                    data={clickedRow}
                />
                <AddField
                    show={modalShowAdd}
                    onHide={() => setModalShowAdd(false)}
                    questionnaireId={params.id}
                />
            </div>
        </div>
    );
};

export default Fields;