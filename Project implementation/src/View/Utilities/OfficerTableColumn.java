package View.Utilities;


public class OfficerTableColumn {

    private String areaName;
    private int officerID;
    private int adminID;
    private String username;
    private String password;
    private int areaID;
    private String name;
    private String phone;

    private String Email;
    private String Sex;

    public OfficerTableColumn(String areaName, int officerID, int adminID, String username, String password, int areaID, String name, String phone, String Email, String Sex) {
        this.areaName = areaName;
        this.officerID = officerID;
        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.areaID = areaID;
        this.name = name;
        this.phone = phone;
        this.Email = Email;
        this.Sex = Sex;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

}
