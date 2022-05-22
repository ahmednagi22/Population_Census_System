package View.Utilities;

public class UserTableColumn {

    private String areaName;
    private int userID;
    private String address;
    private String education;
    private String sex;
    private String occupation;
    private String DOB;
    private int areaID;
    private String name;
    private String phone;
    private String Email;

    public UserTableColumn(String areaName, int userID, String address, String education, String sex, String occupation, String DOB, int areaID, String name, String phone, String Email) {
        this.areaName = areaName;
        this.userID = userID;
        this.address = address;
        this.education = education;
        this.sex = sex;
        this.occupation = occupation;
        this.DOB = DOB;
        this.areaID = areaID;
        this.name = name;
        this.phone = phone;
        this.Email = Email;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
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

}
