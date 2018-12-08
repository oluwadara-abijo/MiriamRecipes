package com.example.dara.miriamrecipes.ui.detail;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dara.miriamrecipes.ui.detail.RecipeStepActivity.EXTRA_STEP_ID;

public class ViewRecipeStepActivity extends AppCompatActivity {

    //Current step
    private Step mStep;

    @BindView(R.id.video_container)
    FrameLayout videoContainer;
    @BindView(R.id.description_container)
    FrameLayout descriptionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_step);

        //Bind views
        ButterKnife.bind(this);

        //Allow video take up full screen on landscape orientation on mobile
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            descriptionContainer.setVisibility(View.GONE);
            videoContainer.setLayoutParams(new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        mStep = getIntent().getParcelableExtra(EXTRA_STEP_ID);

        this.setTitle(mStep.getShortDescription());

        //Create and display a new  RecipeStepVideoFragment
        StepVideoFragment videoFragment = new StepVideoFragment();

        //Create and display a new RecipeStepDescriptionFragment
        StepDescriptionFragment descriptionFragment = new StepDescriptionFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        //If current step has no video, hide the video container
        if (TextUtils.isEmpty(mStep.getVideoUrl())) {
            videoContainer.setVisibility(View.GONE);
            fragmentManager.beginTransaction()
                    .add(R.id.description_container, descriptionFragment)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.video_container, videoFragment)
                    .add(R.id.description_container, descriptionFragment)
                    .commit();

        }

    }

    public Step getStep() {
        return mStep;
    }
}
