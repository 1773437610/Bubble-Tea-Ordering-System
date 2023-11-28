package ui.text;

import model.Drinks;
import model.MilkTea;
import model.Order;
import model.ingredients.Ingredients;

import java.util.ArrayList;

//Display the order details
public class DisplayOrder {

    //EFFECTS: display all the order to the console
    public static void displayAllOrder() {
        ArrayList<Order> orders = Order.getOrdersHistory();
        System.out.println("Order History---------------------------\n\n");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order " + (i + 1) + "\n------------------------");
            displayOrderDetails(orders.get(i));
        }
    }

    //EFFECTS: display the detail of drinks in the order
    public static void displayOrderDetails(Order order) {


        ArrayList<Drinks> currentOrder = order.getItemsOrdered();
        for (int i = 0; i < currentOrder.size(); i++) {
            System.out.println(currentOrder.get(i).getClass().toString().substring(12));
            System.out.println("Sweetness: " + currentOrder.get(i).getSweetness());
            System.out.println("Size: " + currentOrder.get(i).getSize());
            if (currentOrder.get(i) instanceof MilkTea) {
                System.out.println("icelevel: " + ((MilkTea) currentOrder.get(i)).getIceLevel());
            }
            System.out.print("Ingredients:  ");
            toString(currentOrder.get(i).getIngredients());
            System.out.print("Toppings added:  ");
            toString(currentOrder.get(i).getToppings());
            System.out.println("-----------------");
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
