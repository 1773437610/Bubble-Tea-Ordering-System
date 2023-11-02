package ui;

import model.Drinks;
import model.ingredients.Bubble;
import model.ingredients.Milk;
import model.ingredients.Tea;

import java.util.Scanner;

public class AddToppings {
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
                    drinks.addToppings(new Bubble());
                } else if (text.equals("Milk")) {
                    drinks.addToppings(new Milk());
                } else if (text.equals("Tea")) {
                    drinks.addToppings(new Tea());
                }
            } else {
                break;
            }
        }
    }
}
