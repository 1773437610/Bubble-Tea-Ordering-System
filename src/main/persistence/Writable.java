package persistence;

import org.json.JSONObject;

//Interface for Objects that needs to have persistence
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}