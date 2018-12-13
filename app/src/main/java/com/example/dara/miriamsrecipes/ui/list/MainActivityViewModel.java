package com.example.dara.miriamsrecipes.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.dara.miriamsrecipes.data.RecipeRepository;
import com.example.dara.miriamsrecipes.data.model.Recipe;

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
