package model.ingredients;

//Milk, a type of ingredients
public class Milk implements Ingredient {
    private static final String name = "Milk";

    @Override
    public String getName() {
        return name;
    }
}
