package com.example.dara.miriamrecipes.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dara.miriamrecipes.AppExecutors;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeNetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = RecipeNetworkDataSource.class.getSimpleName();
    private static RecipeNetworkDataSource sInstance;

    private final AppExecutors mExecutors;

    //Constructor
    private RecipeNetworkDataSource(AppExecutors executors) {
        mExecutors = executors;
    }

    /**
     * Get the singleton for this class
     */
    public static RecipeNetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeNetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }

    //Gets the recipes from network
    public LiveData<List<Recipe>> getRecipes() {
        final MutableLiveData<List<Recipe>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            RecipeInterface mRecipeInterface = RecipeClient.getClient();

            mRecipeInterface.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                    Log.d(LOG_TAG, String.valueOf(response));
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                    Log.d(LOG_TAG, "An error occurred");

                }
            });

        });
        return mutableLiveData;
    }
}
