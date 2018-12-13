package com.example.dara.miriamsrecipes.data.network;

import com.example.dara.miriamsrecipes.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Interface where endpoints are defined
interface RecipeInterface {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getAllRecipes();
}
