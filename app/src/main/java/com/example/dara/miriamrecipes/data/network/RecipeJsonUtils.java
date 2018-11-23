package com.example.dara.miriamrecipes.data.network;

import android.text.TextUtils;

import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.model.Ingredient;
import com.example.dara.miriamrecipes.data.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

final class RecipeJsonUtils {

    private static final String OWM_MESSAGE_CODE = "cod";

    private static boolean hasHttpError(JSONObject forecastJson) throws JSONException {
        if (forecastJson.has(OWM_MESSAGE_CODE)) {
            int errorCode = forecastJson.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    return false;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    // Location invalid
                default:
                    // Server probably down
                    return true;
            }
        }
        return false;
    }

    /**
     * This method parses JSON from a web response and returns a list of Recipe objects
     * containing ingredients, steps and description of the recipes
     *
     * @param recipeJsonResponse the String response from the server
     * @return the list of Recipe objects
     */
    static List<Recipe> extractRecipesFromJson(String recipeJsonResponse) {

        //Return null if there is no Json response or if there is an error
        try {
            if (TextUtils.isEmpty(recipeJsonResponse) || hasHttpError(new JSONObject(recipeJsonResponse))) {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Create an empty array list that all news will be added to
        List<Recipe> recipeList = new ArrayList<>();

        //Create a JSONObject from the response string and extract fields
        try {
            JSONArray rootArray = new JSONArray(recipeJsonResponse);

            //Iterate through every object in the root array
            for (int i = 0; i < rootArray.length(); i++) {
                //Get the Recipe object at position i
                JSONObject currentRecipe = rootArray.optJSONObject(i);

                //Get recipe id
                int id = currentRecipe.optInt("id");

                //Get recipe name
                String name = currentRecipe.optString("name");

                //Get ingredients array
                JSONArray ingredients = currentRecipe.optJSONArray("ingredients");
                //Deserialize JsonArray into Ingredient objects
                Gson ingredientsGson = new Gson();
                Type ingredientType = new TypeToken<List<Ingredient>>(){}.getType();
                List<Ingredient> ingredientList = ingredientsGson.fromJson(String.valueOf(ingredients), ingredientType);

                //Get steps array
                JSONArray steps = currentRecipe.optJSONArray("steps");
                //Deserialize JsonArray into Step objects
                Gson stepsGson = new Gson();
                Type stepType = new TypeToken<List<Step>>(){}.getType();
                List<Step> stepList = stepsGson.fromJson(String.valueOf(steps), stepType);

                //Get recipe servings
                int servings = currentRecipe.optInt("servings");

                //Get recipe image
                String image = currentRecipe.optString("image");

                //Add the news item to the list of news
                recipeList.add(new Recipe(id, name, ingredientList, stepList, servings, image));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }
}
