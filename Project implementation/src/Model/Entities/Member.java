package Model.Entities;


public class Member extends FamilyMember {

    private int memberID;
    private int userID;

    public int getMemberID() {
        return memberID;
    }

    public Member(int memberID, int userID, String address, String education, String email, String sex, String occupation, String DOB, int areaID, String name, String phone) {
        super(address, education, email, sex, occupation, DOB, areaID, name, phone);
        this.memberID = memberID;
        this.userID = userID;
    }

    public Member(int userID, String address, String education, String email, String sex, String occupation, String DOB, int areaID, String name, String phone) {
        super(address, education, email, sex, occupation, DOB, areaID, name, phone);
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
