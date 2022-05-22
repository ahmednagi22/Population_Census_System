package Model.Entities;

public class Area {

    private String areaName;
    private int areaID;

    private int stateID;

    public Area(String areaName, int areaID, int stateID) {
        this.areaName = areaName;
        this.areaID = areaID;
        this.stateID = stateID;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public int getAreaID() {
        return areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
