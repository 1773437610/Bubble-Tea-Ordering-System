package ui;

import model.Drinks;
import model.MilkTea;
import model.Order;

import java.util.Scanner;

public class ModifyOrders {
    public static void modifyOrder(Order order) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Add Order? (Y/N)");
            String answer = input.next();
            if (answer.equals("Y")) {
                System.out.print("What types of drink would you like to add\nFor the type MilkTea: ");
                System.out.print("{MilkTea} {(1-100)} {(1-100)} {Large, Medium, Small}");
                System.out.print("(type, ice level, sweetness, size)\nFor the type Drinks: ");
                System.out.print("{Drinks} {1-100} {Large, Medium, Small}\n(type, sweetness, size)");
                answer = input.next();
                if (answer.equals("MilkTea")) {
                    Drinks drinks = new MilkTea(input.nextInt(), input.nextInt(), input.next());
                    order.addToOrdered(drinks);
                    AddToppings.addToppings(drinks);
                } else if (answer.equals("Drinks")) {
                    Drinks drinks = new Drinks(input.nextInt(), input.next());
                    order.addToOrdered(drinks);
                    AddToppings.addToppings(drinks);
                }
            } else if (answer.equals("N")) {
                break;
            }
        }
    }
}
