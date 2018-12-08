package com.example.dara.miriamrecipes.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Recipe;

import static com.example.dara.miriamrecipes.ui.list.MainActivity.EXTRA_RECIPE_ID;

public class RecipeStepActivity extends AppCompatActivity {

    public static final String EXTRA_STEP_ID = "step_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        Recipe mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

        this.setTitle(mRecipe.getName());

    }

}
