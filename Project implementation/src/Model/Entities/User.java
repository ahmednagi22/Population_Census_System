package Model.Entities;


public class User extends FamilyMember {

    private int officerID;
    private int userID;
    private String username;
    private String password;

    public User(int officerID, int userID, String address, String education, String email, String sex, String occupation, String DOB, int areaID, String name, String phone, String username, String password) {
        super(address, education, email, sex, occupation, DOB, areaID, name, phone);
        this.officerID = officerID;
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User(int officerID, String address, String education, String email, String sex, String occupation, String DOB, int areaID, String name, String phone, String username, String password) {
        super(address, education, email, sex, occupation, DOB, areaID, name, phone);
        this.officerID = officerID;
        this.username = username;
        this.password = password;
    }

    public User(String address, String education, String email, String sex, String occupation, String DOB, int areaID, String name, String phone) {
        super(address, education, email, sex, occupation, DOB, areaID, name, phone);
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

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
