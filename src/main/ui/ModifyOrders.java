package ui;

import model.Drinks;
import model.MilkTea;
import model.Order;

import java.util.Scanner;

//Represent the modifying of Orders
public class ModifyOrders {
    public static void modifyOrder() {
        Scanner input = new Scanner(System.in);
        Order order = new Order();
        addDrinks(order);
        Order.addToOrdersHistory(order);
    }


    //MODIFIES: order
    //EFFECTS: add drinks to the order with customer's desired customization
    public static void addDrinks(Order order) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Add drinks to an order? (Y/N)");
            String answer = input.next();
            if (answer.equals("Y")) {
                print();
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

    //EFFECTS: print out the questions and prompts before retrieving answer from user
    private static void print() {
        System.out.print("What types of drink would you like to add\nFor the type MilkTea: ");
        System.out.println("{MilkTea} {(1-100)} {(1-100)} {Large, Medium, Small}");
        System.out.print("(type, ice level, sweetness, size)\nFor the type Drinks: ");
        System.out.println("{Drinks} {1-100} {Large, Medium, Small}\n(type, sweetness, size)");
    }
}
