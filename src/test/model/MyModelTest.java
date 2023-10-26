package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestOrder {
    Order order;
    Drinks drinks;
    @BeforeEach
    public void setUp () {
        order = new Order();
        drinks = new MilkTea(50, 50,"Medium");
    }

    @Test
    void testAddToOrdered() {
        order.addToOrdered(drinks);
        assertEquals("class model.MilkTea" ,drinks.getClass().toString());
        assertEquals(1, order.getItemsOrdered().size());
        assertEquals(drinks, order.getItemsOrdered().get(0));
    }
}