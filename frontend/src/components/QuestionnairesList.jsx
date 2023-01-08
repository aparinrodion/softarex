import React, {useEffect, useState} from 'react';
import axios from "axios";
import {ListGroup, ListGroupItem} from "react-bootstrap";

const QuestionnairesList = (props) => {
    const [quests, setQuestionnaires] = useState([]);
    function loadQuestionnaires() {
        axios.get("http://localhost:8080/questionnaires", {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(response => {
            console.log(response.data)
            setQuestionnaires(response.data)
        })
    }

    useEffect(() => {
        loadQuestionnaires()
    }, [])

    function CustomListGroupItem() {
        return (
            quests.map(t =>
                <ListGroupItem key={t.id} action
                               onClick={() => props.onClick(t.id)}> {t.name} {t.creationDate}</ListGroupItem>
            )
        )
    }

    return (
            <div className='w-50'>
                <ListGroup>
                    <CustomListGroupItem/>
                </ListGroup>
            </div>
    );
};

export default QuestionnairesList;