package model;

import model.ingredients.Ingredients;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestDrinks {
    Drinks drinks;
    Drinks drinks2;
    @BeforeEach
    public void setUp () {
        drinks = new MilkTea();
        drinks2 = new Drinks(50, "Large");
        drinks.setSize("Large");
        drinks.setSweetness(50);
        drinks.addIngredient(Ingredients.BUBBLE);
        drinks.addIngredient(Ingredients.MILK);
        drinks.addIngredient(Ingredients.TEA);
        drinks.addToppings(Ingredients.BUBBLE);
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
        assertEquals("BUBBLE", drinks.getIngredients().get(0).toString());
        assertEquals("MILK", drinks.getIngredients().get(1).toString());
        assertEquals("TEA", drinks.getIngredients().get(2).toString());
    }

    @Test
    void testContainsBubble() {
        assertFalse(drinks2.containsBubbleAlready());
        drinks2.addIngredient(Ingredients.MILK);
        assertFalse(drinks2.containsBubbleAlready());
        drinks2.addIngredient(Ingredients.BUBBLE);
        assertTrue(drinks2.containsBubbleAlready());
    }
}