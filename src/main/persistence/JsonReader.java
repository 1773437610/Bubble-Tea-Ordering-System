package persistence;

import model.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
import java.util.stream.Stream;

import model.ingredients.Ingredients;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Use partial code from JsonSeriallizationDemo from course CPSC210
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public void read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseOrder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private void parseOrder(JSONObject jsonObject) {
        addOrder(jsonObject);
    }

    // MODIFIES: order
    // EFFECTS: parses order from JSON object and adds them to order
    private void addOrder(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Orders");
        for (Object json : jsonArray) {
            JSONObject nextOrder = (JSONObject) json;
            Order order = new Order();
            Order.addToOrdersHistory(order);
            addDrinks(order, nextOrder);
        }
    }

    // MODIFIES: order
    // EFFECTS: parses Drinks from JSON object and adds it to order
    private void addDrinks(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Drinks");
        for (Object json : jsonArray) {
            JSONObject nextDrinks = (JSONObject) json;
            if (jsonObject.getString("Type").equals("MilkTea")) {
                int icelevel = Integer.valueOf(jsonObject.getString("icelevel"));
                int sweetness = Integer.valueOf(jsonObject.getString("sweetness"));
                String size = String.valueOf(jsonObject.getString("sweetness"));
                order.addToOrdered(new MilkTea(icelevel, sweetness, size));
            } else if (jsonObject.getString("Type").equals("Drinks")) {
                int sweetness = Integer.valueOf(jsonObject.getString("sweetness"));
                String size = String.valueOf(jsonObject.getString("sweetness"));
                order.addToOrdered(new Drinks(sweetness, size));
            }
        }
    }
}
*/
