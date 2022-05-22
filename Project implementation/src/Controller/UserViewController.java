package Controller;

import Model.database.AdminDB;
import Model.database.UserDB;
import Model.Entities.CorrectionRequest;
import Model.Entities.Member;
import Model.Entities.UserRequest;
import View.Utilities.CorrectionRequestTableColumn;
import View.Utilities.MemberTableColumn;
import View.Utilities.UserRequestTableColumn;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserViewController {

    int userID;

    public UserViewController(int userID) {
        this.userID = userID;
    }

    public ObservableList<UserRequestTableColumn> getuserRequests() {
        ObservableList<UserRequestTableColumn> UserRequestsList = FXCollections.observableArrayList();
        ArrayList<UserRequest> userRequests = UserDB.getUserRequestsForUser(userID);
        for (int i = 0; i < userRequests.size(); i++) {
            UserRequest userRequest = userRequests.get(i);
            UserRequestsList.add(new UserRequestTableColumn(userRequest.getName(), AdminDB.getAreaName(userRequest.getAreaID()), userRequest.getRequestID(), userRequest.getRequestState(), userRequest.getAddress(), userRequest.getEducation(), userRequest.getPhone(), userRequest.getEmail(), userRequest.getMemberID(), userRequest.getDOB(), userRequest.getAreaID(), userRequest.getSex(), userRequest.getOccupation(), userRequest.getUserID()));
        }
        return UserRequestsList;
    }

    public ObservableList<MemberTableColumn> getMembers() {
        ObservableList<MemberTableColumn> membersList = FXCollections.observableArrayList();
        ArrayList<Member> members = UserDB.getMembers(userID);
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            membersList.add(new MemberTableColumn(AdminDB.getAreaName(member.getAreaID()), member.getMemberID(), member.getUserID(), member.getAddress(), member.getEducation(), member.getSex(), member.getOccupation(), member.getDOB(), member.getAreaID(), member.getName(), member.getPhone(), member.getEmail()));
        }
        return membersList;
    }

    public void addUserRequest(String name, String address, String education, String phone, String email, String date, String sex, String Occupation, int userID) {
        UserDB.addUserRequest(new UserRequest("Pending", address, name, education, phone, email, date, UserDB.getAreaIDFromUserID(userID), sex, Occupation, userID));
    }

    public UserRequestTableColumn getUserRequestTableColumn(int userRequestID) {
        UserRequest userRequest = UserDB.getUserRequestForUser(userRequestID);
        return new UserRequestTableColumn(userRequest.getName(), AdminDB.getAreaName(userRequest.getAreaID()), userRequest.getRequestID(), userRequest.getRequestState(), userRequest.getAddress(), userRequest.getEducation(), userRequest.getPhone(), userRequest.getEmail(), userRequest.getMemberID(), userRequest.getDOB(), userRequest.getAreaID(), userRequest.getSex(), userRequest.getOccupation(), userRequest.getUserID());
    }

    public ObservableList<CorrectionRequestTableColumn> getCorrectionRequests() {
        ObservableList<CorrectionRequestTableColumn> correctionRequestsList = FXCollections.observableArrayList();
        ArrayList<CorrectionRequest> correctionRequests = UserDB.getCorrectionRequests(userID);
        for (int i = 0; i < correctionRequests.size(); i++) {
            CorrectionRequest correctionRequest = correctionRequests.get(i);
            correctionRequestsList.add(new CorrectionRequestTableColumn(correctionRequest.getRequestID(), correctionRequest.getUserRequestID(), correctionRequest.getOfficerID(), correctionRequest.getRequestTitle(), correctionRequest.getRequestContent(), correctionRequest.getUserID()));
        }
        return correctionRequestsList;
    }

    public void updateUserRequest(UserRequestTableColumn UserRequest, String name, String sex, String Occupation, String address, String education, String date, String email, String phone, String Sex) {
        UserDB.updateUserRequest(new UserRequest(UserRequest.getRequestID(), "Pending", name, address, education, phone, email, UserRequest.getMemberID(), date, UserRequest.getAreaID(), Sex, Occupation, UserRequest.getUserID()));
    }
}
