package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestOrder {
    Order order;
    Drinks drinks;

    @BeforeEach
    public void setUp () {
        Order.getOrderHistory().clear();
        order = new Order();
        drinks = new MilkTea(50, 50,"Medium");
    }

    @Test
    void testAddDrink() {
        order.addDrink(drinks);
        assertEquals("class model.MilkTea" ,drinks.getClass().toString());
        assertEquals(1, order.getItemsOrdered().size());
        assertEquals(drinks, order.getItemsOrdered().get(0));
    }

    @Test
    void testDeleteDrink() {
        order.addDrink(drinks);
        order.deleteDrink(drinks);
        assertEquals(0, order.getItemsOrdered().size());
    }

    @Test
    void testAddToOrdersHistory() {
        Order.addToOrderHistory(order);
        assertEquals(1, Order.getOrderHistory().size());
        assertEquals(order, Order.getOrderHistory().get(0));
    }

    @Test
    void testDeleteOrdersHistory() {
        Order.addToOrderHistory(order);
        Order.deleteOrderHistory(0);
        assertEquals(0, Order.getOrderHistory().size());
    }

    @Test
    void testShowOrderDetails() {
        order.addDrink(drinks);
        assertEquals("Order detail:\n" +
                "1.MilkTea\n" +
                "   Sweetness: 50\n" +
                "   Size: Medium\n" +
                "   IceLevel:50\n", order.showOrderDetails());
    }
}