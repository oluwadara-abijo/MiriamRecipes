package com.example.dara.miriamrecipes.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.dara.miriamrecipes.data.RecipeRepository;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.util.List;

class MainActivityViewModel extends ViewModel {

    private final LiveData<List<Recipe>> mRecipes;

    MainActivityViewModel(RecipeRepository repository) {
        mRecipes = repository.getRecipes();
    }

    LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }
}
