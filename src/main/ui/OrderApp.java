package ui;

import model.Order;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Represent an app that stores orders for drinks as history and are able to modify order
public class OrderApp {
    private static final String JSON_STORE = "./data/order.json";
    private Scanner input;
    private Order order;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        try {
            new OrderApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    public OrderApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        order = new Order();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runOrderApp();
    }

    //EFFECTS: perform operations based on user's input and prompt if the input is not valid
    private void runOrderApp() {
        boolean endProgram = false;
        while (!endProgram) {
            input = new Scanner(System.in);
            System.out.println("Select from:\n\t1->displayOrder\n\t2->modifyOrder\n\t3->save\n\t4->load\n\t5->quit");
            int num = input.nextInt();
            if (num == 1) {
                DisplayOrder.displayAllOrder();
            } else if (num == 2) {
                ModifyOrders.modifyOrder();
            } else if (num == 3) {
                saveOrder();
            } else if (num == 4) {
                loadOrder();
            } else if (num == 5) {
                endProgram = true;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // EFFECTS: saves the Order to file
    private void saveOrder() {
        try {
            jsonWriter.open();
            jsonWriter.write(order);
            jsonWriter.close();
            System.out.println("Saved orders to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Order from file
    private void loadOrder() {
        try {
            jsonReader.read();
            System.out.println("Loaded orders from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

