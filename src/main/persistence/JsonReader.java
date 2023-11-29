package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Order from JSON data stored in file
// Use partial code from JsonSeriallizationDemo from course CPSC210
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: throws IOException if an error occurs reading data from file
    public void read() throws IOException {
        Order.getOrderHistory().clear();
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        addOrder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: order
    // EFFECTS: parses order from JSON object and adds them to order
    private void addOrder(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Orders");
        for (Object json : jsonArray) {
            JSONObject currOrder = (JSONObject) json;
            Order order = new Order();
            Order.addToOrderHistory(order);
            addDrinks(order, currOrder);
        }
    }

    //MODIFIES: order
    //EFFECTS: parses Drinks from JSON object and adds it to order
    private void addDrinks(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Drinks");
        for (Object json : jsonArray) {
            JSONObject currDrinks = (JSONObject) json;
            if (currDrinks.getString("Type").equals("class model.MilkTea")) {
                int icelevel = currDrinks.getInt("icelevel");
                int sweetness = currDrinks.getInt("sweetness");
                String size = currDrinks.getString("size");
                order.addDrink(new MilkTea(icelevel, sweetness, size));
            } else if (currDrinks.getString("Type").equals("class model.Drinks")) {
                int sweetness = currDrinks.getInt("sweetness");
                String size = currDrinks.getString("size");
                order.addDrink(new Drinks(sweetness, size));
            }
        }
    }
}


