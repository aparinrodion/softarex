import Login from "./components/Login";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Registration from "./components/Registration";
import EditProfile from "./components/EditProfile";
import Fields from "./components/Fields";
import Responses from "./components/Responses";
import QuestionnairesListFields from "./components/QuestionnairesListFeilds";
import QuestionnairesListResponses from "./components/QuestionnairesListResponses";
import Questionnaire from "./components/Questionnaire";
import SuccessSubmit from "./components/SuccessSubmit";
import ChangePassword from "./components/ChangePassword";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="portal/login" element={<Login/>}/>
                <Route path="portal/password" element={<ChangePassword/>}/>
                <Route path="portal/registration" element={<Registration/>}/>
                <Route path="portal/questionnaires/:id/fields" element={<Fields/>}/>
                <Route path="portal/questionnaires/:id" element={<Questionnaire/>}/>
                <Route path="portal/questionnaires/:id/responses" element={<Responses/>}/>
                <Route path="" element={<Login/>}/>
                <Route path="portal/success" element={<SuccessSubmit/>}/>
                <Route path="portal/edit" element={<EditProfile/>}/>
                <Route path="portal/fields" element={<QuestionnairesListFields/>}/>
                <Route path="portal/responses" element={<QuestionnairesListResponses/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;