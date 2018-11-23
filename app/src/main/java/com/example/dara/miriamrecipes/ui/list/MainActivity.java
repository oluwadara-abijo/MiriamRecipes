package com.example.dara.miriamrecipes.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Recipe;
import com.example.dara.miriamrecipes.data.network.InjectorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.ItemClickListener {

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
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //Associate the LayoutManager with the RecyclerView */
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

    }
}
