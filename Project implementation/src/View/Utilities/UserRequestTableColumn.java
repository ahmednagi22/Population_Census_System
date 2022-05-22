package View.Utilities;

public class UserRequestTableColumn {

    private String areaName;
    private int requestID;
    private String name;
    private String requestState;
    private String address;
    private String education;
    private String phone;
    private String email;
    private int memberID;
    private String DOB;
    private int areaID;
    private String sex;
    private String occupation;
    private int userID;

    public UserRequestTableColumn(String name, String areaName, int requestID, String requestState, String address, String education, String phone, String email, int memberID, String DOB, int areaID, String sex, String occupation, int userID) {
        this.name = name;
        this.areaName = areaName;
        this.requestID = requestID;
        this.requestState = requestState;
        this.address = address;
        this.education = education;
        this.phone = phone;
        this.email = email;
        this.memberID = memberID;
        this.DOB = DOB;
        this.areaID = areaID;
        this.sex = sex;
        this.occupation = occupation;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestState() {
        return requestState;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
