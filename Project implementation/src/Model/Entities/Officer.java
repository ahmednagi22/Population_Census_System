package Model.Entities;

import java.util.ArrayList;

public class Officer extends Person {

    private int officerID;
    private int adminID;
    private String username;
    private String password;
    private int areaID;

    public Officer(String phone, String Email, int officerID, String name, int areaID, String Sex, String username, String password, int adminID) {
        super(name, phone, Email, Sex);
        this.officerID = officerID;
        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.areaID = areaID;
    }

    public Officer(String phone, String Email, String name, int areaID, String Sex, String username, String password, int adminID) {
        super(name, phone, Email, Sex);
        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.areaID = areaID;
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
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

    public void notifyUpdate() {
        //User.Update();
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
