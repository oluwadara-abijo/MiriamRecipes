package com.example.dara.miriamrecipes.data.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.dara.miriamrecipes.AppExecutors;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.net.URL;
import java.util.List;

public class RecipeNetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = RecipeNetworkDataSource.class.getSimpleName();
    private static RecipeNetworkDataSource sInstance;
    private final Context mContext;

    private final AppExecutors mExecutors;

    //LiveData storing the latest downloaded recipes
    private final MutableLiveData<List<Recipe>> mDownloadedRecipes;

    //Constructor
    private RecipeNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedRecipes = new MutableLiveData<>();
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

    //Getter method for downloaded recipes
    public MutableLiveData<List<Recipe>> getDownloadedRecipes() {
        return mDownloadedRecipes;
    }

    /**
     * Gets the newest weather
     */
    void fetchRecipes() {
        Log.d(LOG_TAG, "Fetch weather started");
        mExecutors.networkIO().execute(() -> {
            try {

                //Get the url to query
                URL recipeRequestUrl = NetworkUtils.buildQueryUrl();

                // Use the URL to retrieve the JSON
                String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(recipeRequestUrl);

                // Parse the JSON into a list of recipe objects
                List<Recipe> recipes = RecipeJsonUtils.extractRecipesFromJson(jsonRecipeResponse);
                Log.d(LOG_TAG, "JSON Parsing finished");

                // As long as there are recipes, update the LiveData storing the most recent
                // recipes. This will trigger observers of that LiveData, such as the
                // RecipeRepository.
                if (jsonRecipeResponse != null) {

                    mDownloadedRecipes.postValue(recipes);
                }
            } catch (Exception e) {
                // Server probably invalid
                e.printStackTrace();
            }
        });

    }
}
