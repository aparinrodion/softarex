import React from 'react';
import {useNavigate} from "react-router-dom";
import QuestionnairesList from "./QuestionnairesList";
import NavigationBar from "./NavigationBar";

const QuestionnairesListResponses = () => {
    const navigate = useNavigate();

    function navigateToResponses(id) {
        navigate("/portal/questionnaires/" + id + "/responses");
    }

    return (
        <div className={"bg-light vh-100"}>
            <NavigationBar/>
            <div className={"justify-content-center mt-5 d-flex"}>
                <div
                    className={"d-flex w-100 flex-column align-items-center"}>
                    <QuestionnairesList onClick={navigateToResponses}/>
                </div>
            </div>
        </div>
    );
};

export default QuestionnairesListResponses;