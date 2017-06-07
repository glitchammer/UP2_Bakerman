package es.glitch.and.bugs.bakerman.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbahls on 07/06/2017.
 */

public class Recipe {

    private final int id;
    private final String name;
    private final List<Ingredient> ingredients;
    private final List<Step> steps;
    private final int servings;
    private final String image;

    public Recipe(JSONObject jsonRecipe) throws JSONException {

        this.id       = jsonRecipe.getInt("id");
        this.name     = jsonRecipe.getString("name");
        this.servings = jsonRecipe.getInt("servings");
        this.image    = jsonRecipe.getString("image");

        this.ingredients = new ArrayList<Ingredient>();
        JSONArray ingredientsJson = jsonRecipe.getJSONArray("ingredients");
        for (int i=0; i<ingredientsJson.length(); i++) {
            JSONObject json = ingredientsJson.getJSONObject(i);
            ingredients.add(new Ingredient(json));
        }

        this.steps = new ArrayList<Step>();
        JSONArray stepsJson = jsonRecipe.getJSONArray("steps");
        for (int i=0; i<stepsJson.length(); i++) {
            JSONObject json = stepsJson.getJSONObject(i);
            steps.add(new Step(json));
        }

    }
}
