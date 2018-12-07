package com.example.dara.miriamrecipes.ui.detail;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.model.Step;

import static com.example.dara.miriamrecipes.ui.list.MainActivity.EXTRA_RECIPE_ID;


public class ViewRecipeStepActivity extends AppCompatActivity implements MasterListAdapter.ItemClickListener {

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_step);

        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

        //Create and display a new  RecipeStepVideoFragment
        StepVideoFragment videoFragment = new StepVideoFragment();

        //Create and display a new RecipeStepDescriptionFragment
        StepDescriptionFragment descriptionFragment = new StepDescriptionFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.video_container, videoFragment)
                .add(R.id.description_container, descriptionFragment)
                .commit();

    }

    @Override
    public void onItemClickListener(Step step) {
        Log.d("DETAIL CLICK>>>","Item clicked");

    }
}
