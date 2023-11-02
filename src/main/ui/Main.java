package ui;

import model.Order;

public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        DisplayOrder.displayOrder(order);
        ModifyOrders.modifyOrder(order);
        DisplayOrder.displayOrder(order);
    }
}
