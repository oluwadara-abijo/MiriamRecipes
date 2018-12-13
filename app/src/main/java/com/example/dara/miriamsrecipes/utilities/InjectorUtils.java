package com.example.dara.miriamsrecipes.utilities;

import android.content.Context;

import com.example.dara.miriamsrecipes.AppExecutors;
import com.example.dara.miriamsrecipes.data.RecipeRepository;
import com.example.dara.miriamsrecipes.data.network.RecipeNetworkDataSource;
import com.example.dara.miriamsrecipes.ui.list.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {

    private static RecipeRepository provideRepository(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        RecipeNetworkDataSource networkDataSource =
                RecipeNetworkDataSource.getInstance(executors);
        return RecipeRepository.getInstance(networkDataSource, executors, context);
    }

    public static RecipeNetworkDataSource provideNetworkDataSource() {
        AppExecutors executors = AppExecutors.getInstance();
        return RecipeNetworkDataSource.getInstance(executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        RecipeRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

}