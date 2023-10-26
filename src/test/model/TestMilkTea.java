package model;

import model.ingredients.Bubble;
import model.ingredients.Milk;
import model.ingredients.Tea;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDrinks {
    Drinks drinks;
    @BeforeEach
    public void setUp () {
        drinks = new MilkTea();
        drinks.setSize("Large");
        drinks.setSweetness(50);
        drinks.addIngredient(new Bubble());
        drinks.addIngredient(new Milk());
        drinks.addIngredient(new Tea());
        drinks.addToppings(new Bubble());
        ((MilkTea)drinks).setIceLevel(0);
    }

    @Test
    void testDrinks() {
        assertEquals("Large", drinks.getSize());
        assertEquals(50, drinks.getSweetness());
        assertEquals(3, drinks.getIngredients().size());
        assertEquals(1, drinks.getToppings().size());
        assertEquals(0, ((MilkTea)drinks).getIceLevel());
    }
}