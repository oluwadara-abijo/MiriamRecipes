package com.example.dara.miriamsrecipes.ui.detail;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dara.miriamsrecipes.R;
import com.example.dara.miriamsrecipes.data.model.Recipe;
import com.example.dara.miriamsrecipes.data.model.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dara.miriamsrecipes.ui.list.MainActivity.EXTRA_RECIPE_ID;

public class RecipeStepActivity extends AppCompatActivity implements MasterListAdapter.ItemClickListener {

    @Nullable
    @BindView(R.id.recipe_step_detail_linear_layout)
    LinearLayout stepDetail;
    @Nullable
    @BindView(R.id.video_container)
    FrameLayout videoContainer;
    @Nullable
    @BindView(R.id.description_container)
    FrameLayout descriptionContainer;

    public static final String EXTRA_STEP_ID = "step_extra";

    private boolean mTwoPane;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        ButterKnife.bind(this);

        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

        this.setTitle(mRecipe.getName());

        mTwoPane = stepDetail != null;

        if (mTwoPane) {

            if (savedInstanceState == null) {

                FragmentManager fragmentManager = getSupportFragmentManager();

                //Create a new StepVideoFragment
                StepVideoFragment videoFragment = new StepVideoFragment();
                videoFragment.setStep(mRecipe.getSteps().get(0));
                fragmentManager.beginTransaction()
                        .add(R.id.video_container, videoFragment)
                        .commit();

                //Create a new StepDescriptionFragment
                StepDescriptionFragment descriptionFragment = new StepDescriptionFragment();
                descriptionFragment.setStep(mRecipe.getSteps().get(0));
                fragmentManager.beginTransaction()
                        .add(R.id.description_container, descriptionFragment)
                        .commit();

            }
        }

    }

    @Override
    public void onItemClickListener(Step step) {
        if (mTwoPane) {

            //Create and display a new RecipeStepVideoFragment
            StepVideoFragment videoFragment = new StepVideoFragment();

            //If current step has no video, hide the video fragment layout
            if (TextUtils.isEmpty(step.getVideoUrl())) {
                assert videoContainer != null;
                videoContainer.setVisibility(View.GONE);
            } else {
                //Current step has a video and it should be displayed
                videoFragment.setStep(step);
                assert videoContainer != null;
                videoContainer.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.video_container, videoFragment)
                        .commit();
            }

            //Create and display a new RecipeStepDescriptionFragment
            StepDescriptionFragment descriptionFragment = new StepDescriptionFragment();
            descriptionFragment.setStep(step);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.description_container, descriptionFragment)
                    .commit();
        } else {
            //This is a mobile view. Pass the details of the current step into the next activity
            Intent intent = new Intent(RecipeStepActivity.this, ViewRecipeStepActivity.class);
            intent.putExtra(EXTRA_STEP_ID, step);
            intent.putExtra(EXTRA_RECIPE_ID, mRecipe);
            startActivity(intent);
        }

    }
}
