package com.example.dara.miriamrecipes.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.dara.miriamrecipes.data.RecipeRepository;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final RecipeRepository mRepository;
    private final LiveData<List<Recipe>> mRecipes;

    public MainActivityViewModel(RecipeRepository repository) {
        mRepository = repository;
        mRecipes = mRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes () {
        return mRecipes;
    }
}
