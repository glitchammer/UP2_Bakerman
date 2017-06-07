package es.glitch.and.bugs.bakerman.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dbahls on 07/06/2017.
 */

public class Ingredient {

    private final int quantity;
    private final String measure;
    private final String name;

    public Ingredient(JSONObject json) throws JSONException {

        this.quantity = json.getInt("quantity");
        this.measure  = json.getString("measure");
        this.name     = json.getString("ingredient");

    }

}
