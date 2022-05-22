package Model.Entities;

public class CorrectionRequest {

    private int requestID;
    private int userRequestID;
    private int officerID;
    private String requestTitle;
    private String requestContent;
    private int userID;

    public CorrectionRequest() {
    }

    public int getUserRequestID() {
        return userRequestID;
    }

    public void setUserRequestID(int userRequestID) {
        this.userRequestID = userRequestID;
    }

    public CorrectionRequest(int requestID, int userRequestID, String requestTitle, String requestContent, int userID, int officerID) {
        this.requestID = requestID;
        this.userRequestID = userRequestID;
        this.requestTitle = requestTitle;
        this.requestContent = requestContent;
        this.userID = userID;
        this.officerID = officerID;
    }

    public CorrectionRequest(int userRequestID, String requestTitle, String requestContent, int userID, int officerID) {
        this.userRequestID = userRequestID;
        this.requestTitle = requestTitle;
        this.requestContent = requestContent;
        this.userID = userID;
        this.officerID = officerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public CorrectionRequest(int requestID, String requestTitle, String requestContent) {
        this.requestID = requestID;
        this.requestTitle = requestTitle;
        this.requestContent = requestContent;
    }

   

}
