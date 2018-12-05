package com.example.dara.miriamrecipes.ui.detail;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.model.Step;


public class ViewRecipeStepActivity extends AppCompatActivity implements MasterListAdapter.ItemClickListener {

    public static final String EXTRA_RECIPE_ID = "recipe_extra";

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_step);

        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

        // COMPLETED (5) Create a new BodyPartFragment instance and display it using the FragmentManager
        RecipeStepVideoFragment videoFragment = new RecipeStepVideoFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.video_container, videoFragment)
                .commit();

    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    @Override
    public void onItemClickListener(Step step) {
        Log.d("DETAIL CLICK>>>","Item clicked");

    }
}
