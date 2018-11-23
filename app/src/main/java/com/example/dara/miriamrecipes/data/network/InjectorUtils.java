package com.example.dara.miriamrecipes.data.network;

import android.content.Context;

import com.example.dara.miriamrecipes.AppExecutors;
import com.example.dara.miriamrecipes.data.RecipeRepository;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    public static RecipeRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        RecipeNetworkDataSource networkDataSource =
                RecipeNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return RecipeRepository.getInstance(networkDataSource, executors, context);
    }

    public static RecipeNetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return RecipeNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

}