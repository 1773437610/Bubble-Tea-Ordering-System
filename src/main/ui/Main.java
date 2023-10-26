package ui;

import model.Drinks;
import model.MilkTea;
import model.Order;

public class Main {
    public static void main(String[] args) {
        Order order = new Order();
        Drinks drinks = new MilkTea(5,5,"Milktea");
        DisplayOrder.displayOrder(order);
        ModifyOrders.modifyOrders(order);
        DisplayOrder.displayOrder(order);
    }
}
