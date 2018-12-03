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
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeNetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = RecipeNetworkDataSource.class.getSimpleName();
    private static RecipeNetworkDataSource sInstance;

    private final AppExecutors mExecutors;
    private static final String base_url = "https://d17h27t6h515a5.cloudfront.net/";

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
        final MutableLiveData<List<Recipe>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            RecipeInterface mRecipeInterface = RecipeClient.getClient();

            mRecipeInterface.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    Log.d(LOG_TAG, String.valueOf(response));
                    mutableLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    Log.d(LOG_TAG, "An error occurred");

                }
            });

        });
        return mutableLiveData;
    }
}
