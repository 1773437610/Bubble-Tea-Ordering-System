package ui.text;

import model.Drinks;
import model.Ingredients;

import java.util.Scanner;

//Represent all actions related to add new toppings
public class AddToppings {

    //MODIFIES: add toppings to the drink
    //EFFECTS: prompt the user if they want to add toppings or not
    public static void addToppings(Drinks drinks) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Would you like to add any toppings?(Y/N)");
            if (input.next().equals("Y")) {
                System.out.println("What would you like to add?");
                System.out.println("{Bubble, Milk, Tea}");
                input.nextLine();
                String text = input.nextLine();
                if (text.equals("Bubble")) {
                    drinks.addToppings(Ingredients.BUBBLE);
                } else if (text.equals("Milk")) {
                    drinks.addToppings(Ingredients.MILK);
                } else if (text.equals("Tea")) {
                    drinks.addToppings(Ingredients.MILK);
                }
            } else {
                break;
            }
        }
    }
}
