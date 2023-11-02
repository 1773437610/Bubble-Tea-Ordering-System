package ui;

import model.Drinks;
import model.Order;
import model.ingredients.Ingredient;

import java.util.ArrayList;

//Display the order details
public class DisplayOrder {

    public static void displayOrder(Order order) {
        System.out.println("Orders:\n");
        ArrayList<Drinks> currentOrder = order.getItemsOrdered();
        for (int i = 0; i < currentOrder.size(); i++) {
            System.out.println("Order " + (i + 1) + ": \n" + currentOrder.get(i).getClass().toString().substring(12));
            System.out.println("Sweetness: " + currentOrder.get(i).getSweetness());
            System.out.println("Size: " + currentOrder.get(i).getSize());
            System.out.print("Ingredients:  ");
            toString(currentOrder.get(i).getIngredients());
            System.out.print("Toppings added:  ");
            toString(currentOrder.get(i).getToppings());
            System.out.println("-------------------------");
        }
    }

    public static void toString(ArrayList<Ingredient> array) {
        for (Ingredient i : array) {
            System.out.print(i.getName() + " ");
        }
        System.out.println();
    }
}
