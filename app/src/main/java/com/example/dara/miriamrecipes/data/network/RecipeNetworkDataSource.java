package com.example.dara.miriamrecipes.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.dara.miriamrecipes.AppExecutors;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RecipeNetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = RecipeNetworkDataSource.class.getSimpleName();
    private static RecipeNetworkDataSource sInstance;

    private final AppExecutors mExecutors;

    //Constructor
    private RecipeNetworkDataSource(Context context, AppExecutors executors) {
        Context mContext = context;
        mExecutors = executors;
        //LiveData storing the recipes
        MutableLiveData<List<Recipe>> mAllRecipes = new MutableLiveData<>();
    }

    /**
     * Get the singleton for this class
     */
    public static RecipeNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeNetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    //Gets the recipes from network

    public LiveData<List<Recipe>> getRecipes() {
        MutableLiveData<List<Recipe>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            try {
                //Get the url to query
                URL recipeRequestUrl = NetworkUtils.buildQueryUrl();

                // Use the URL to retrieve the JSON
                String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(recipeRequestUrl);

                // Parse the JSON into a list of recipe objects
                List<Recipe> recipes = RecipeJsonUtils.extractRecipesFromJson(jsonRecipeResponse);
                Log.d(LOG_TAG, "JSON Parsing finished");

                //Parse json response into a list of movies
                mutableLiveData.postValue(recipes);
                Log.d(LOG_TAG, "JSON Parsing finished");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return mutableLiveData;
    }
}
