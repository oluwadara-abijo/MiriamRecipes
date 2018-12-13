package com.example.dara.miriamsrecipes.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dara.miriamsrecipes.R;
import com.example.dara.miriamsrecipes.data.model.Step;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dara.miriamsrecipes.ui.detail.RecipeStepActivity.EXTRA_STEP_ID;

public class StepDescriptionFragment extends Fragment {

    public static final String CURRENT_STEP = "current_step";

    //UI element
    @BindView(R.id.tv_recipe_description)
    TextView textView;

    private Step mStep;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(CURRENT_STEP);
        }

        // Inflate the fragment_recipe_step_video layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_description, container, false);

        //Bind the text view
        ButterKnife.bind(this, rootView);

        //Current step
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        if (intent != null) {
            if (intent.hasExtra(EXTRA_STEP_ID)) {
                mStep = ((ViewRecipeStepActivity) this.getActivity()).getStep();}
        }

        String recipeDescription = mStep.getDescription();
        textView.setText(recipeDescription);

        // Return the rootView
        return rootView;
    }

    public void setStep(Step mStep) {
        this.mStep = mStep;
    }

    //Save the state of the fragment

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_STEP, mStep);
    }
}
