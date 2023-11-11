package model;

import model.ingredients.Ingredients;

import java.util.ArrayList;

//Represents drinks that can be ordered by customers
public class Drinks {
    private final ArrayList<Ingredients> ingredients;
    private final ArrayList<Ingredients> toppings;
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
}
