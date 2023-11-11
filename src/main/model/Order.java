package model;

import java.util.ArrayList;

//Represent an order by customers
public class Order {
    private static ArrayList<Order> orderHistory;
    private final ArrayList<Drinks> itemsOrdered;

    //MODIFIES: this
    //initializes fields
    public Order() {
        itemsOrdered = new ArrayList<>();
        orderHistory = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add the drink ordered into itemsOrdered
    public void addToOrdered(Drinks itemsOrdered) {
        this.itemsOrdered.add(itemsOrdered);
    }

    //MODIFIES: this
    //EFFECTS: add the order into ordersHistory
    public static void addToOrdersHistory(Order order) {
        orderHistory.add(order);
    }

    public ArrayList<Drinks> getItemsOrdered() {
        return itemsOrdered;
    }

    public static ArrayList<Order> getOrdersHistory() {
        return orderHistory;
    }
}
