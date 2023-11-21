package persistence;

import model.Drinks;
import model.Order;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonTest {
    protected void checkOrder(Order order, ArrayList<Drinks> drinks) {
        if (order.getItemsOrdered().size() == drinks.size()) {
            for (int i = 0; i < drinks.size(); i++) {
                assertTrue(order.getItemsOrdered().get(i).equals(drinks.get(i)));
            }
        } else {
            fail("The number of drinks for this order is not correct!");
        }
    }
}
