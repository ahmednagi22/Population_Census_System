package Model.Entities;

public class State {

    private int stateID;
    private String stateName;

    public State(int stateID, String stateName) {
        this.stateID = stateID;
        this.stateName = stateName;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
