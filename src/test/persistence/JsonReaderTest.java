package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    @BeforeEach
    void setUp() {
        Order.getOrdersHistory().clear();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrders.json");
        try {
            reader.read();
            assertEquals(0, Order.getOrdersHistory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralOrder() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOrders.json");
        try {
            reader.read();
            List<Order> orders = Order.getOrdersHistory();
            assertEquals(2, orders.size());

            ArrayList<Drinks> order1 = new ArrayList<>();
            order1.add(new Drinks(100, "large"));
            order1.add(new MilkTea(25, 25, "small"));

            ArrayList<Drinks> order2 = new ArrayList<>();
            order2.add(new MilkTea(100, 100, "small"));
            order2.add(new Drinks(0, "medium"));

            checkOrder(orders.get(0), order1);
            checkOrder(orders.get(1), order2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}