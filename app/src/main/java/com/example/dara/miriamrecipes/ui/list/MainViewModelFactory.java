package com.example.dara.miriamrecipes.ui.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.dara.miriamrecipes.data.RecipeRepository;


/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link RecipeRepository}
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RecipeRepository mRepository;

    public MainViewModelFactory(RecipeRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
