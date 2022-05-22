package Controller;

import Model.database.AdminDB;
import Model.database.OfficerDB;
import Model.Entities.CorrectionRequest;
import Model.Entities.Member;
import Model.Entities.User;
import Model.Entities.UserRequest;
import Model.database.UserDB;
import View.Utilities.UserRequestTableColumn;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OfficerViewController {

    int officerID;

    public OfficerViewController(int officerID) {
        this.officerID = officerID;
    }

    public void addUser(String name, String address, String education, String phone, String email, String date, String sex, String Occupation, String username, String password, int OfficerID) {
        OfficerDB.addUser(new User(officerID, address, education, email, sex, Occupation, date, OfficerDB.getAreaIDFromOfficerID(officerID), name, phone, username, password));
    }

    public ObservableList<UserRequestTableColumn> getPendingUserRequests() {
        ObservableList<UserRequestTableColumn> UserRequestColumnsList = FXCollections.observableArrayList();
        ArrayList<UserRequest> userRequests = OfficerDB.getPendingUserRequests(officerID);
        for (int i = 0; i < userRequests.size(); i++) {
            UserRequest userRequest = userRequests.get(i);
            UserRequestColumnsList.add(new UserRequestTableColumn(userRequest.getName(), AdminDB.getAreaName(userRequest.getAreaID()), userRequest.getRequestID(), userRequest.getRequestState(), userRequest.getAddress(), userRequest.getEducation(), userRequest.getPhone(), userRequest.getEmail(), userRequest.getMemberID(), userRequest.getDOB(), userRequest.getAreaID(), userRequest.getSex(), userRequest.getOccupation(), userRequest.getUserID()));
        }
        return UserRequestColumnsList;
    }

    public boolean checkSelectedColumnHasCorrectionRequest(int requestID) {
        return OfficerDB.hasCorrectionRequest(requestID);
    }

    public void setTitleAndReasonOfCorrectionRequest(TextField name_field, TextArea Correct, int userRequestID) {
        CorrectionRequest correctionRequest = OfficerDB.getCorrectionRequest(userRequestID);
        name_field.setText(correctionRequest.getRequestTitle());
        Correct.setText(correctionRequest.getRequestContent());
    }

    public void updateCorrectionRequest(int requestID, String title, String content) {
        OfficerDB.updateCorrectionRequest(new CorrectionRequest(OfficerDB.getCorrectionRequest(requestID).getRequestID(), title, content));
    }

    public void makeCorrectionRequest(int userRequestID, int userID, int officerID, String title, String content) {
        OfficerDB.addCorrectionRequest(new CorrectionRequest(userRequestID, title, content, userID, officerID));
    }

    public void updateStateOfUserRequestToRejected(int requestID) {
        OfficerDB.updateStateOfUserRequestToRejected(requestID);
    }

    public void updateStateOfUserRequestToAccepted(int requestID) {
        OfficerDB.updateStateOfUserRequestToAccepted(requestID);
    }

    public void deleteCorrectionRequest(int userRequestID) {
        OfficerDB.deleteCorrectionRequestByUserRequestID(userRequestID);
    }

    public void acceptUserRequest(int requestID) {
        UserRequest userRequest = UserDB.getUserRequestForUser(requestID);
        UserDB.addMember(new Member(userRequest.getMemberID(),userRequest.getUserID(),userRequest.getAddress(),userRequest.getEducation(),userRequest.getEmail(),userRequest.getSex(),userRequest.getOccupation(),userRequest.getDOB(),userRequest.getAreaID(),userRequest.getName(),userRequest.getPhone()));
    }
}
