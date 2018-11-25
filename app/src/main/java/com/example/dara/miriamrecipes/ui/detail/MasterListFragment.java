package com.example.dara.miriamrecipes.ui.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Ingredient;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// This fragment displays a list of recipe  steps in a recycler view
public class MasterListFragment extends Fragment implements MasterListAdapter.ItemClickListener{

    @BindView(R.id.steps_recycler_view) RecyclerView recyclerView;
    private Recipe mRecipe;

    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    MasterListAdapter.ItemClickListener mCallback;

    @Override
    public void onItemClickListener(Step step) {

    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (MasterListAdapter.ItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Inflates the RecyclerView of all steps
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_steps_master_list, container, false);

        ButterKnife.bind(this, rootView);

        List<Step> steps = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the steps to display
        MasterListAdapter mAdapter = new MasterListAdapter(ingredients, steps, this);

        // Set the adapter on the GridView
        recyclerView.setAdapter(mAdapter);

        // Return the root view
        return rootView;
    }
}
