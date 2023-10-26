package model;

import model.ingredients.Ingredient;

import java.util.ArrayList;

//Represents drinks that can be ordered by customers
public class Drinks {
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<Ingredient> toppings;
    private int sweetness;
    private String size;

    //MODIFIES: this
    //EFFECTS: initialize fields
    public Drinks() {
        ingredients = new ArrayList<>();
        toppings = new ArrayList<>();
        sweetness = 100;
        size = "medium";
    }

    ////MODIFIES: this
    //EFFECTS: initialize fields
    public Drinks(int sweetness, String size) {
        ingredients = new ArrayList<>();
        toppings = new ArrayList<>();
        this.sweetness = sweetness;
        this.size = size;
    }

    public void setSweetness(int sweetness) {
        this.sweetness = sweetness;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addToppings(Ingredient i) {
        toppings.add(i);
    }

    public ArrayList<Ingredient> getToppings() {
        return toppings;
    }

    public int getSweetness() {
        return sweetness;
    }

    public String getSize() {
        return size;
    }
}
