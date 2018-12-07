package com.example.dara.miriamrecipes.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Ingredient;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dara.miriamrecipes.ui.list.MainActivity.EXTRA_RECIPE_ID;

public class IngredientFragment extends Fragment {

    @BindView(R.id.rv_ingredients)
    RecyclerView recyclerView;

    //Empty constructor
    public IngredientFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        //Bind view
        ButterKnife.bind(this, rootView);

        //Current recipe
        Recipe mRecipe = getActivity().getIntent().getParcelableExtra(EXTRA_RECIPE_ID);

        //Create lists of all steps and ingredients
        List<Ingredient> mIngredients = mRecipe.getIngredients();

        //Layout manager for the recycler view
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter to set on the recycler view
        IngredientAdapter mAdapter = new IngredientAdapter(mIngredients);

        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
