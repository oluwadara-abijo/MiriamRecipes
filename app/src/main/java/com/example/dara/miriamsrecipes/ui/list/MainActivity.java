package com.example.dara.miriamsrecipes.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dara.miriamsrecipes.R;
import com.example.dara.miriamsrecipes.data.model.Recipe;
import com.example.dara.miriamsrecipes.ui.detail.RecipeStepActivity;
import com.example.dara.miriamsrecipes.utilities.InjectorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ItemClickListener {

    public static final String EXTRA_RECIPE_ID = "recipe_extra";

    //UI Elements
    @BindView(R.id.rv_recipe_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;


    private RecipeAdapter mAdapter;
    private int mPosition = RecyclerView.NO_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind views
        ButterKnife.bind(this);

        //Get an instance of LinearLayoutManager
        GridLayoutManager mLayoutManager;
        //Set span count based on orientation
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(this, 1);
        } else {
            mLayoutManager = new GridLayoutManager(this, 3);
        }

        //Associate the LayoutManager with the RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        //List of all recipes
        List<Recipe> mList = new ArrayList<>();

        mAdapter = new RecipeAdapter(mList, this);

        //Attach the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        MainActivityViewModel mViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        if (isNetworkAvailable()) {
            mViewModel.getRecipes().observe(this, recipes -> {
                mAdapter.setRecipes(recipes);
                if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
                mRecyclerView.smoothScrollToPosition(mPosition);

                // Show the recipe list or the loading screen based on whether the recipe data exists
                // and is loaded
                if (recipes != null && recipes.size() != 0) showData();
                else showLoading();
            });
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager)
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private void showData() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClickListener(Recipe recipe) {
        Intent intent = new Intent(MainActivity.this, RecipeStepActivity.class);
        intent.putExtra(EXTRA_RECIPE_ID, recipe);
        startActivity(intent);

    }
}
