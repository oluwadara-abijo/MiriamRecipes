package com.example.dara.miriamrecipes.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.dara.miriamrecipes.AppExecutors;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.utilities.InjectorUtils;
import com.example.dara.miriamrecipes.data.network.RecipeNetworkDataSource;

import java.util.List;

public class RecipeRepository {
    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static RecipeRepository sInstance;
    private final RecipeNetworkDataSource mRecipeNetworkDataSource;
    private final AppExecutors mExecutors;
    private Context mContext;

    //Constructor
    private RecipeRepository(RecipeNetworkDataSource recipeNetworkDataSource,
                             AppExecutors executors, Context context) {
        mRecipeNetworkDataSource = recipeNetworkDataSource;
        mExecutors = executors;
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
                .provideNetworkDataSource(mContext);
        return networkDataSource.getRecipes();
    }
}
