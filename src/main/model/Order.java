package model;

import java.util.ArrayList;

//Represent an order by customers
public class Order {
    ArrayList<Drinks> itemsOrdered;

    public Order() {
        itemsOrdered = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add the drink ordered in to itemsOrdered
    public void addToOrdered(Drinks itemsOrdered) {
        this.itemsOrdered.add(itemsOrdered);
    }

    public ArrayList<Drinks> getItemsOrdered() {
        return itemsOrdered;
    }
}
