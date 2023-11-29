package model;

import model.ingredients.Ingredients;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents drinks that can be ordered by customers
public class Drinks implements Writable {
    private final ArrayList<Ingredients> ingredients;
    private final ArrayList<Ingredients> toppings;
    private int sweetness;
    private String size;
    private JSONObject json;
    private static final String name = "Drinks";

    //MODIFIES: this
    //EFFECTS: initialize fields
    public Drinks() {
        ingredients = new ArrayList<>();
        toppings = new ArrayList<>();
        sweetness = 100;
        size = "medium";
    }

    //MODIFIES: this
    //EFFECTS: initialize fields
    public Drinks(int sweetness, String size) {
        ingredients = new ArrayList<>();
        toppings = new ArrayList<>();
        this.sweetness = sweetness;
        this.size = size;
    }

    //EFFECTS: return boolean value that if this contains bubbles or not
    public boolean containsBubbleAlready() {
        for (Ingredients i : ingredients) {
            if (i.toString().equals("BUBBLE")) {
                return true;
            }
        }
        return false;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void addIngredient(Ingredients ingredient) {
        ingredients.add(ingredient);
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void addToppings(Ingredients i) {
        toppings.add(i);
    }

    public ArrayList<Ingredients> getToppings() {
        return toppings;
    }

    public int getSweetness() {
        return sweetness;
    }

    public String getSize() {
        return size;
    }

    //EFFECTS: return a json object that stores all the fields
    @Override
    public JSONObject toJson() {
        json = new JSONObject();
        json.put("Type", this.getClass());
        json.put("sweetness", sweetness);
        json.put("size", size);
        return json;
    }

    public JSONObject getJson() {
        return json;
    }

    //EFFECTS: return true if both drinks has identical fields (storing the same information)
    public boolean equals(Drinks drinks) {
        return this.getClass().equals(drinks.getClass()) && this.getSweetness() == drinks.getSweetness()
                && this.getSize().equals(drinks.getSize()) && compareArray(toppings, drinks.getToppings())
                && compareArray(ingredients, drinks.getIngredients());
    }

    //EFFECTS: return true if two arraylist printing out the same result
    public boolean compareArray(ArrayList<Ingredients> array1, ArrayList<Ingredients> array2) {
        return (array1.toString().equals(array2.toString()));
    }

    public String toString() {
        return name;
    }
}
