package Model.Entities;


public class FamilyMember extends Person {

    private String address;
    private String education;
    private String occupation;
    private String DOB;
    private int areaID;

    public FamilyMember(String address, String education, String email, String sex, String occupation, String DOB, int areaID, String name, String phone) {
        super(name, phone, email, sex);
        this.address = address;
        this.education = education;
        this.occupation = occupation;
        this.DOB = DOB;
        this.areaID = areaID;
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int AreaID) {
        this.areaID = AreaID;
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
