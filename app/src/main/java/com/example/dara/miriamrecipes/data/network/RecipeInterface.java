package com.example.dara.miriamrecipes.data.network;

import com.example.dara.miriamrecipes.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Interface where endpoints are defined
public interface RecipeInterface {
    @GET("baking.json")
    Call<List<Recipe>> getAllRecipes();
}