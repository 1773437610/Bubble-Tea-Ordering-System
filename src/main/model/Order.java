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
    public void addDrink(Drinks itemsOrdered) {
        this.itemsOrdered.add(itemsOrdered);
        EventLog.getInstance().logEvent(new Event(itemsOrdered.toString() + " added to order."));
    }

    //MODIFIES: this
    //EFFECTS: delete the drink in itemsOrdered
    public void deleteDrink(Drinks itemsOrdered) {
        this.itemsOrdered.remove(itemsOrdered);
        EventLog.getInstance().logEvent(new Event(itemsOrdered.toString() + " deleted from order."));
    }

    //MODIFIES: this
    //EFFECTS: add the order into ordersHistory
    public static void addToOrderHistory(Order order) {
        orderHistory.add(order);
        EventLog.getInstance().logEvent(new Event("order added to order history."));
    }

    //MODIFIES: this
    //EFFECTS: delete the order in ordersHistory
    public static void deleteOrderHistory(int position) {
        orderHistory.remove(position);
        EventLog.getInstance().logEvent(new Event("Drink at " + position + " removed from order."));
    }

    public ArrayList<Drinks> getItemsOrdered() {
        return itemsOrdered;
    }

    public static ArrayList<Order> getOrderHistory() {
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

    //EFFECTS: return String for the details of the selected order
    public String showOrderDetails() {
        EventLog.getInstance().logEvent(new Event("Order details displayed."));
        String text = "Order detail:\n";

        for (int i = 0; i < itemsOrdered.size(); i++) {
            Drinks drink = itemsOrdered.get(i);
            text += (i + 1) + "." + drink.getClass().toString().substring(12);
            text += "\n   Sweetness: " + drink.getSweetness();
            text += "\n   Size: " + drink.getSize();

            if (drink.getClass().equals(MilkTea.class)) {
                text += "\n   IceLevel:" + ((MilkTea) drink).getIceLevel();
            }

            text += "\n";
        }

        return text;
    }
}
