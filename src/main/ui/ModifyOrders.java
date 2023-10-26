package ui;

import model.Drinks;
import model.MilkTea;
import model.Order;

import java.util.Scanner;

public class ModifyOrders {
    public static void modifyOrders(Order order) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Add Order? (Y/N)\n");
            String answer = input.nextLine();
            if (answer.equals("Y")) {
                System.out.println("What types of drink? Sweetness? Ice level? (follow by space)");
                System.out.println("Choices: For MilkTea: MilkTea/(1-100)/(1-100)/(Large, Medium, Small)");
                System.out.println(("\t\tFor Drinks: Drinks/(1-100)/(Large, Medium, Small)"));
                answer = input.next();
                if (answer.equals("MilkTea")) {
                    order.addToOrdered(new MilkTea(input.nextInt(), input.nextInt(), input.next()));
                } else if (answer.equals("Drinks")) {
                    order.addToOrdered(new Drinks(input.nextInt(), input.next()));
                }
            } else if (answer.equals("N")) {
                return;
            }
        }
    }
}
