package Controller;

import Model.Entities.User;
import Model.Entities.Area;
import Model.Entities.Officer;
import Model.database.*;
import View.Utilities.OfficerTableColumn;
import View.Utilities.UserTableColumn;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminViewController {

    int adminID;

    public AdminViewController(int adminID) {
        this.adminID = adminID;
    }

    public ArrayList<String> getAreasForComboBox() {
        ArrayList<Area> areas = AdminDB.getAreasFromStateID(AdminDB.getAdminFromAdminID(adminID).getStateID());
        ArrayList<String> areasNames = new ArrayList<String>();
        for (int i = 0; i < areas.size(); i++) {
            areasNames.add(areas.get(i).getAreaName());
        }
        return areasNames;
    }

    public void addOfficer(String phone, String email, String name, String area, String sex, String username, String password) {
        AdminDB.addOfficer(new Officer(phone, email, name, AdminDB.getAreaID(area), sex, username, password, adminID));
    }

    public ObservableList<OfficerTableColumn> getOfficers() {
        ObservableList<OfficerTableColumn> officerList = FXCollections.observableArrayList();
        ArrayList<Officer> officers = AdminDB.getOfficers(adminID);
        for (int i = 0; i < officers.size(); i++) {
            Officer officer = officers.get(i);
            officerList.add(new OfficerTableColumn(AdminDB.getAreaName(officer.getAreaID()), officer.getOfficerID(), officer.getAdminID(), officer.getUsername(), officer.getPassword(), officer.getAreaID(), officer.getName(), officer.getPhone(), officer.getEmail(), officer.getSex()));
        }
        return officerList;
    }

    public void deleteOfficer(int officerID) {
        AdminDB.deleteOfficer(officerID);
    }

    public void updateOfficer(String phone, String Email, int officerID, String name, String areaName, String Sex, String username, String password, int adminID) {
        AdminDB.updateOfficer(new Officer(phone, Email, officerID, name, AdminDB.getAreaID(areaName), Sex, username, password, adminID));
    }

    public String getAreaName(int areaID) {
        return AdminDB.getAreaName(areaID);
    }

    public ObservableList getReport(String SearchOn, String SearchOptions, String SortByOption, String OrderOption) {
        ObservableList reportList = FXCollections.observableArrayList();

        boolean isHasdescendantsOrHasUsers = SearchOptions.equals("Has descendants") || SearchOptions.equals("Has Users") ? true : false;
        boolean isAscendingOrder = OrderOption.equals("Ascending") ? true : false;

        if (SearchOn.equals("User")) {
            ArrayList<User> users = OfficerDB.getUsersByParameters(isHasdescendantsOrHasUsers, SortByOption, isAscendingOrder, adminID);
            reportList = makeUserTableRowsList(users);
        } else if (SearchOn.equals("Officer")) {
            ArrayList<Officer> officers = AdminDB.getOfficersByParameters(isHasdescendantsOrHasUsers, SortByOption, isAscendingOrder, adminID);
            reportList = makeOfficerTableRowsList(officers);
        }

        return reportList;
    }

    private ObservableList makeUserTableRowsList(ArrayList<User> users) {
        ObservableList<UserTableColumn> UserTableRowsList = FXCollections.observableArrayList();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserTableRowsList.add(new UserTableColumn(AdminDB.getAreaName(user.getAreaID()), user.getUserID(), user.getAddress(), user.getEducation(), user.getSex(), user.getOccupation(), user.getDOB(), user.getAreaID(), user.getName(), user.getPhone(), user.getEmail()));
        }
        return UserTableRowsList;
    }

    private ObservableList makeOfficerTableRowsList(ArrayList<Officer> officers) {
        ObservableList<OfficerTableColumn> OfficerTableRowsList = FXCollections.observableArrayList();

        for (int i = 0; i < officers.size(); i++) {
            Officer officer = officers.get(i);
            OfficerTableRowsList.add(new OfficerTableColumn(AdminDB.getAreaName(officer.getAreaID()), officer.getOfficerID(), officer.getAdminID(), officer.getUsername(), officer.getPassword(), officer.getAreaID(), officer.getName(), officer.getPhone(), officer.getEmail(), officer.getSex()));
        }
        return OfficerTableRowsList;
    }

}
