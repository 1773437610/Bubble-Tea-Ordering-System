package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    @BeforeEach
    void setUp() {
        Order.getOrderHistory().clear();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterOrderWithOneDrink() {
        try {
            Order order = new Order();
            Order.addToOrderHistory(order);
            Drinks drink = new Drinks(5,"Medium");
            order.addDrink(drink);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrders.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrders.json");
            reader.read();
            ArrayList<Order> list = Order.getOrderHistory();
            assertEquals(1, list.size());
            assertEquals(1, list.get(0).getItemsOrdered().size());
            assertTrue(drink.equals(list.get(0).getItemsOrdered().get(0)));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralOrder() {
        try {
            Order order = new Order();
            Order order1 = new Order();
            Drinks drink = new Drinks(100, "Large");
            Drinks drink1 = new MilkTea(50, 50, "Small");

            order.addDrink(drink);
            order.addDrink(drink1);
            order1.addDrink(drink1);
            order1.addDrink(drink);
            Order.addToOrderHistory(order);
            Order.addToOrderHistory(order1);

            ArrayList<Drinks> drinksList = new ArrayList<>();
            drinksList.add(drink);
            drinksList.add(drink1);

            ArrayList<Drinks> drinksList1 = new ArrayList<>();
            drinksList1.add(drink1);
            drinksList1.add(drink);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralOrders.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOrders.json");
            reader.read();
            ArrayList<Order> list = Order.getOrderHistory();
            assertEquals(2, list.size());
            assertEquals(2, list.get(0).getItemsOrdered().size());
            assertEquals(2, list.get(1).getItemsOrdered().size());
            checkOrder(order, drinksList);
            checkOrder(order1, drinksList1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}