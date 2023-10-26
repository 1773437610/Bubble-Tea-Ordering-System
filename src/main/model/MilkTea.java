package model;

public class MilkTea extends Drinks {
    private int iceLevel;

    public MilkTea() {
        super();
        iceLevel = 100;
    }

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
}
