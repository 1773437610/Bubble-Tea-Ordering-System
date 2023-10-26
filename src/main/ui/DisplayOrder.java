package ui;

import model.Drinks;
import model.Order;

import java.util.ArrayList;

//Display the order details
public class DisplayOrder {

    public static void displayOrder(Order order) {
        System.out.println("Orders:\n");
        ArrayList<Drinks> currentOrder = order.getItemsOrdered();
        for (int i = 0; i < currentOrder.size(); i++) {
            System.out.print("Order 1: " + currentOrder.get(i).getClass());
            System.out.print(":  Sweetness: " + currentOrder.get(i).getSweetness());
            System.out.println("   Size: " + currentOrder.get(i).getSize());
            System.out.print("Ingredients: " + currentOrder.get(i).getIngredients());
            System.out.print("Toppings added: " + currentOrder.get(i).getToppings());
        }
    }
}
