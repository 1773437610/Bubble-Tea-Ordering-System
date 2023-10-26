package model;

import model.ingredients.Bubble;
import model.ingredients.Milk;
import model.ingredients.Tea;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDrinks {
    Drinks drinks;
    Drinks drinks2;
    @BeforeEach
    public void setUp () {
        drinks = new MilkTea();
        drinks2 = new Drinks(50, "Large");
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

        assertEquals(50, drinks.getSweetness());
        assertEquals("Large", drinks.getSize());
    }

    @Test
    void testIngredientAdded() {
        assertEquals("Bubble", drinks.getIngredients().get(0).getName());
        assertEquals("Milk", drinks.getIngredients().get(1).getName());
        assertEquals("Tea", drinks.getIngredients().get(2).getName());
    }
}