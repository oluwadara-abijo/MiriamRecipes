package com.example.dara.miriamsrecipes.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.dara.miriamsrecipes.AppExecutors;
import com.example.dara.miriamsrecipes.data.model.Recipe;
import com.example.dara.miriamsrecipes.utilities.InjectorUtils;
import com.example.dara.miriamsrecipes.data.network.RecipeNetworkDataSource;

import java.util.List;

public class RecipeRepository {
    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static RecipeRepository sInstance;
    private final Context mContext;

    //Constructor
    private RecipeRepository(RecipeNetworkDataSource recipeNetworkDataSource,
                             AppExecutors executors, Context context) {
        RecipeNetworkDataSource mRecipeNetworkDataSource = recipeNetworkDataSource;
        AppExecutors mExecutors = executors;
        mContext = context;
    }

    //Singleton
    public synchronized static RecipeRepository getInstance(
            RecipeNetworkDataSource recipeNetworkDataSource, AppExecutors executors,
            Context context) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeRepository(recipeNetworkDataSource,
                        executors, context);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    //Network related operation
    public LiveData<List<Recipe>> getRecipes() {
        RecipeNetworkDataSource networkDataSource = InjectorUtils
                .provideNetworkDataSource();
        return networkDataSource.getRecipes();
    }
}
