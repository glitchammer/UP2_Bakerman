package es.glitch.and.bugs.bakerman.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by dbahls on 07/06/2017.
 */

public class Step {

    private final int id;
    private final String shortDescription;
    private final String description;
    private final String videoUrl;
    private final String thumbnailUrl;

    public Step(JSONObject json) throws JSONException {

        this.id = json.getInt("id");
        this.shortDescription = json.getString("shortDescription");
        this.description = json.getString("description");
        this.videoUrl = json.getString("videoURL");
        this.thumbnailUrl = json.getString("thumbnailURL");

    }
}
