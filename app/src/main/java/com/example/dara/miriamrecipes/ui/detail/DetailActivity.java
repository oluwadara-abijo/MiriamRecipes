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

        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

    }

    @Override
    public void onItemClickListener(Step step) {
        Log.d("DETAIL CLICK>>>","Item clicked");

    }
}
