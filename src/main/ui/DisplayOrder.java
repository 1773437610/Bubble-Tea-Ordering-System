package ui;

import model.Drinks;
import model.Order;
import model.ingredients.Ingredients;

import java.util.ArrayList;

//Display the order details
public class DisplayOrder {

    //EFFECTS: display the detail of drinks in the order
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

    //EFFECTS: print out the Ingredients in the array
    public static void toString(ArrayList<Ingredients> array) {
        for (Ingredients i : array) {
            System.out.print(i.toString() + " ");
        }
        System.out.println();
    }
}
