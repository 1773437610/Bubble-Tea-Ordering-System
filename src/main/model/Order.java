package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represent an order by customers
public class Order implements Writable {
    private static ArrayList<Order> orderHistory = new ArrayList<>();
    private final ArrayList<Drinks> itemsOrdered;

    //MODIFIES: this
    //initializes fields
    public Order() {
        itemsOrdered = new ArrayList<>();
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

    //MODIFIES: this
    //EFFECTS: delete the order in ordersHistory
    public static void deleteOrdersHistory(int position) {
        orderHistory.remove(position);
    }

    public ArrayList<Drinks> getItemsOrdered() {
        return itemsOrdered;
    }

    public static ArrayList<Order> getOrdersHistory() {
        return orderHistory;
    }


    //EFFECTS: return a json object labeled as Orders and have order as items in the arr
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Orders", orderToJson());
        return json;
    }

    //EFFECTS: return a json array that stores each order
    private JSONArray orderToJson() {
        JSONArray json = new JSONArray();

        for (Order order : orderHistory) {
            json.put(drinksToJson(order));
        }
        return json;
    }

    //EFFECTS: return a json object that is labeled as Drinks and have drink as items in the arr
    private JSONObject drinksToJson(Order order) {
        JSONObject json = new JSONObject();
        json.put("Drinks", drinkToJson(order));
        return json;
    }

    //EFFECTS: return a json array that stores each drinks
    private JSONArray drinkToJson(Order order) {
        JSONArray json = new JSONArray();

        for (Drinks drink : order.itemsOrdered) {
            json.put(drink.toJson());
        }

        return json;
    }
}
