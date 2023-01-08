import React, {useEffect, useState} from 'react';
import NavigationBar from "./NavigationBar";
import {useParams} from "react-router-dom";
import axios from "axios";
import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory from "react-bootstrap-table2-paginator";

const Responses = () => {
        const params = useParams();
        const [responses, setResponses] = useState([]);
        const [fields, setFields] = useState([]);

        function NAFormatter(cellContent) {
            return cellContent == null ? 'N/A' : cellContent
        }

        function addField(el) {
            setFields(o => [...o, el])
        }

        function addResponse(el) {
            setResponses(o => [...o, el])
        }

        function loadResponses() {
            axios.get("http://localhost:8080/responses/" + params.id, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then(response => {
                response.data.forEach(r => {
                    const obj = {
                        "id": r.id
                    };
                    r.fieldResponses.forEach(fr =>
                        obj[fr.fieldId] = fr.answer
                    )
                    addResponse(obj)
                })
            })
        }

        function loadFields() {
            axios.get("http://localhost:8080/questionnaires/" + params.id, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("token")
                }
            }).then(response => {
                response.data.forEach(f => addField({
                    "dataField": f.id.toString(),
                    "text": f.label,
                    "formatter": NAFormatter
                }))
            })
        }

        function connect() {
            const Stomp = require("stompjs");
            let SockJS = require("sockjs-client");
            SockJS = new SockJS("http://localhost:8080/ws-responses");
            let stompClient = Stomp.over(SockJS);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/responses', function (greeting) {
                    let data = JSON.parse(greeting.body)
                    if (data.questionnaireId == params.id) {
                        const obj = {
                            "id": data.id
                        };
                        data.fieldResponses.forEach(fr =>
                            obj[fr.fieldId] = fr.answer
                        )
                        addResponse(obj)
                    }
                });
            });
        }

        useEffect(() => {
            loadFields();
            loadResponses();
            connect();
        }, [])


        function ResponseTable() {
            if (fields.length > 0) {
                return <BootstrapTable keyField={'id'} columns={fields}
                                       bordered={false}
                                       striped={true}
                                       data={responses}
                                       pagination={paginationFactory({
                                           sizePerPage: 10,
                                           hideSizePerPage: true,
                                           withFirstAndLast: false,
                                           alwaysShowAllBtns: true,
                                           showTotal: true,
                                           hidePageListOnlyOnePage: true
                                       })}
                />
            }
        }

        return (
            <div className={"bg-light vh-100"}>
                <NavigationBar/>
                <div className={"d-flex justify-content-center mt-3"}>
                    <div className='w-50 bg-white border'>
                        <div className={"d-flex justify-content-between mx-3 my-2"}>
                            <h4>Responses</h4>
                        </div>
                        <hr/>
                        <ResponseTable/>
                    </div>
                </div>
            </div>
        );
    }
;

export default Responses;