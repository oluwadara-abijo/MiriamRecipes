package com.example.dara.miriamrecipes.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Ingredient;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.model.Step;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements MasterListAdapter.ItemClickListener {

    public static final String EXTRA_RECIPE_ID = "recipe_extra";

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Ingredient ingredient = new Ingredient(5, "A", "B");
//        List<Ingredient> ingredientList = new ArrayList<>();
//        ingredientList.add(ingredient);
//
//        Step step = new Step(5, "C", "D", "E");
//        List<Step> stepList = new ArrayList<>();
//        stepList.add(step);
//
//        mRecipe = new Recipe(1, "Bread", ingredientList, stepList, 2, "Image");
        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

    }

    @Override
    public void onItemClickListener(Step step) {
        Log.d("DETAIL CLICK>>>","Item clicked");

    }
}
