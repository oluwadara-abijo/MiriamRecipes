package com.example.dara.miriamrecipes.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Ingredient;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dara.miriamrecipes.ui.detail.RecipeStepActivity.EXTRA_STEP_ID;
import static com.example.dara.miriamrecipes.ui.list.MainActivity.EXTRA_RECIPE_ID;

// This fragment displays a list of recipe  steps in a recycler view
public class MasterListFragment extends Fragment implements MasterListAdapter.ItemClickListener {

    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;
    @BindView(R.id.tv_ingredients)
    TextView ingredientsTextView;

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Inflates the RecyclerView of all steps
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate the layout
        View rootView = inflater.inflate(R.layout.fragment_steps_master_list, container, false);

        //Bind view
        ButterKnife.bind(this, rootView);

        //Current recipe
        Recipe mRecipe = getActivity().getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

        //Create lists of all steps and ingredients
        List<Ingredient> mIngredients = mRecipe.getIngredients();
        List<Step> mSteps = mRecipe.getSteps();

        //Layout manager for the recycler view
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        stepsRecyclerView.setLayoutManager(layoutManager);

        //Create an adapter to set on the recycler view
        MasterListAdapter mAdapter = new MasterListAdapter(mSteps, this);

        stepsRecyclerView.setAdapter(mAdapter);

        for (int i = 0; i < mIngredients.size(); i++) {
            String ingredientDetail = mIngredients.get(i).getIngredient() + " " +
                    String.valueOf(mIngredients.get(i).getQuantity()) + " " +
                    mIngredients.get(i).getMeasure();

            String allIngredients = ingredientDetail + "\n";

            Log.d("ALL>>>", allIngredients);

            ingredientsTextView.append(allIngredients);

        }

        return rootView;
    }

    @Override
    public void onItemClickListener(Step step) {
        Intent intent = new Intent(getActivity(), ViewRecipeStepActivity.class);
        intent.putExtra(EXTRA_STEP_ID, step);
        startActivity(intent);
    }
}
