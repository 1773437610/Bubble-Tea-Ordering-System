package model;

import org.json.JSONObject;

//Represents milk tea
public class MilkTea extends Drinks {
    private static final String name = "MilkTea";
    private int iceLevel;

    //MODIFIES: this
    //EFFECTS: initialize fields
    public MilkTea() {
        super();
        iceLevel = 100;
    }

    //MODIFIES: this
    //EFFECTS: initialize fields
    public MilkTea(int iceLevel, int sweetness, String size) {
        super();
        super.setSweetness(sweetness);
        super.setSize(size);
        this.iceLevel = iceLevel;
    }

    @Override
    public void setSweetness(int sweetness) {
        super.setSweetness(sweetness);
    }

    public int getIceLevel() {
        return iceLevel;
    }

    public void setIceLevel(int num) {
        this.iceLevel = num;
    }

    //EFFECTS: return a json object that stores all the fields
    @Override
    public JSONObject toJson() {
        super.toJson();
        getJson().put("icelevel", iceLevel);
        return getJson();
    }

    //EFFECTS: return the name for this object;
    @Override
    public String toString() {
        return name;
    }
}
