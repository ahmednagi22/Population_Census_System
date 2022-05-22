package Model.Entities;

import java.util.ArrayList;

public class Admin extends Person{

    private int adminID;
    private int stateID;
    private String username;
    private String password;

    public Admin(int adminID, int stateID, String username, String password, String name, String phone, String Email, String Sex) {
        super(name, phone, Email, Sex);
        this.adminID = adminID;
        this.stateID = stateID;
        this.username = username;
        this.password = password;
    }

    public Admin(String name, String phone, String Email, String Sex) {
        super(name, phone, Email, Sex);
    }

    

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addOfficer(Officer officer) {

    }

    public void deleteOfficer(int Oid) {

    }

    public void updateOfficer(int Oid, String name,
            String state, int phone, String username,
            String password, String area) {

    }

    public void viewOfficers(ArrayList<Officer> officers) {

    }

    public void viewFamilyMember(ArrayList<FamilyMember> FamilyMembers) {

    }

}
