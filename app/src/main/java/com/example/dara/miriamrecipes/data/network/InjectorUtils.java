package com.example.dara.miriamrecipes.data.network;

import android.content.Context;

import com.example.dara.miriamrecipes.AppExecutors;
import com.example.dara.miriamrecipes.data.RecipeRepository;
import com.example.dara.miriamrecipes.ui.list.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    private static RecipeRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        RecipeNetworkDataSource networkDataSource =
                RecipeNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return RecipeRepository.getInstance(networkDataSource, executors, context);
    }

    public static RecipeNetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return RecipeNetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        RecipeRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}