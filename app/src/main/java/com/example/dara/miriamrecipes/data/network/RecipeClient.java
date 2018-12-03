package com.example.dara.miriamrecipes.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RecipeClient {
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    private static RecipeInterface retrofit = null;

    static RecipeInterface getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RecipeInterface.class);
        }
        return retrofit;
    }
}
