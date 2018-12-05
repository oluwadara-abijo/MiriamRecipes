package com.example.dara.miriamrecipes.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Recipe;

public class RecipeStepActivity extends AppCompatActivity {

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        // Only create new fragments when there is no previously saved state
        if (savedInstanceState == null)

        {   // Create a new head BodyPartFragment
            RecipeStepVideoFragment stepFragment = new RecipeStepVideoFragment();

//            // Set the list of steps for the fragment and set the position to the second image in the list
//            stepFragment.setSteps(mRecipe.getSteps());
//
//            // Get the correct index to access in the array of head images from the intent
//            // Set the default value to 0
//            int stepsIndex = getIntent().getIntExtra("headIndex", 0);
//            stepFragment.setListIndex(stepsIndex);
//
//            // Add the fragment to its container using a FragmentManager and a Transaction
//            FragmentManager fragmentManager = getSupportFragmentManager();
//
//            fragmentManager.beginTransaction()
//                    .add(R.id.details_container, stepFragment)
//                    .commit();

        }
    }
}
