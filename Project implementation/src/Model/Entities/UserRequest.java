package Model.Entities;

public class UserRequest {

    private int requestID;
    private String requestState;
    private String name;
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
    //RequestState,Address,City,Education,Phone,Email,memberID,DOB,DocName,AreaID,Sex,Occupation,userID)

    public UserRequest(int requestID, String requestState, String name, String address, String education, String phone, String email, int memberID, String DOB, int areaID, String sex, String occupation, int userID) {
        this.requestID = requestID;
        this.requestState = requestState;
        this.name = name;
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

    public UserRequest(String requestState, String address, String name, String education, String phone, String email, String DOB, int areaID, String sex, String occupation, int userID) {
        this.requestState = requestState;
        this.name = name;
        this.address = address;
        this.education = education;
        this.phone = phone;
        this.email = email;
        this.DOB = DOB;
        this.areaID = areaID;
        this.sex = sex;
        this.occupation = occupation;
        this.userID = userID;
    }

    public UserRequest() {
    }

    public UserRequest(String requestState, String name, String address, String education, String phone, String email, int memberID, String DOB, int areaID, String sex, String occupation, int userID) {
        this.requestState = requestState;
        this.name = name;
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

    public void updateMember(Member member) {

    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Member member) {

    }

    public int getRequestID() {
        return requestID;
    }

    public String getRequestState() {
        return requestState;
    }

    public void setRequestState(String requestState) {
        this.requestState = requestState;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getAreaID() {
        return areaID;
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

}
