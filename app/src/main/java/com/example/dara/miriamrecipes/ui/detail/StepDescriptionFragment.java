package com.example.dara.miriamrecipes.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDescriptionFragment extends Fragment {

    //UI element
    @BindView(R.id.tv_recipe_description)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_recipe_step_video layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_step_description, container, false);

        //Bind the text view
        ButterKnife.bind(this, rootView);

        //Current step
        Step mStep = ((ViewRecipeStepActivity)this.getActivity()).getStep();

        String recipeDescription = mStep.getDescription();
        textView.setText(recipeDescription);

        // Return the rootView
        return rootView;
    }
}
