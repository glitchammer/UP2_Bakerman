package es.glitch.and.bugs.bakerman;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import es.glitch.and.bugs.bakerman.model.Recipe;
import es.glitch.and.bugs.bakerman.utilities.NetworkUtils;
import timber.log.Timber;

/**
 * Created by dbahls on 07/06/2017.
 */

public class RecipeLoader extends AsyncTaskLoader<List<Recipe>> {


    private String errorMessage = null;

    public RecipeLoader(Context context) {
        super(context);
    }

    @Override
    public List<Recipe> loadInBackground() {
        try {
            URL url = new URL(BuildConfig.MIRIAMS_BAKING_RECIPES_URL);

            // check internet connection first
            if (!NetworkUtils.isOnline(getContext())
                    || !NetworkUtils.isServerReachable(getContext(), url)
                    ) {

                Timber.e("No connection to " + url);
                throw new IOException("No connection to "+url);
            }

            // load recipes from url
            String recipesJsonStr = IOUtils.toString(url.openStream());


            // parse json and create recipe list
            List<Recipe> recipes = new ArrayList<Recipe>();
            try {
                JSONArray recipesJson = new JSONArray(recipesJsonStr);

                for (int i=0; i<recipesJson.length(); i++) {
                    JSONObject json = recipesJson.getJSONObject(i);
                    Recipe recipe = new Recipe(json);
                    recipes.add(recipe);
                }

            } catch (JSONException e) {
                Timber.e(e, "Cannot parse baking recipes.");
            }

            return recipes;

        } catch (IOException e) {
            Timber.e(e, "Error loading recipes.");
            errorMessage = "Cannot load Miriam's baking recipes from the Web.";
        }
        return null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
