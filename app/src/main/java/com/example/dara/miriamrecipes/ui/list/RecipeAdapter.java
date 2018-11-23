package com.example.dara.miriamrecipes.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    //List object that holds movie data
    private List<Recipe> mRecipes;

    //Create an instance of the click handling interface
    private final ItemClickListener mItemClickListener;

    //Class constructor which creates a RecipeAdapter
    public RecipeAdapter(List<Recipe> recipes, ItemClickListener itemClickListener) {
        mRecipes = recipes;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item_recipe;
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ButterKnife.bind(this, view);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder recipeAdapterViewHolder, int i) {
        Recipe currentRecipe = mRecipes.get(i);
        recipeAdapterViewHolder.recipeName.setText(currentRecipe.getName());

    }

    @Override
    public int getItemCount() {
        if (null == mRecipes) return 0;
        return mRecipes.size();
    }

    //Interface for click handling
    public interface ItemClickListener {
        void onItemClickListener(Recipe recipe);
    }

    //ViewHolder class
    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //UI elements
        @BindView(R.id.recipe_name_textView) TextView recipeName;

        //ViewHolder class constructor
        RecipeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClickListener(mRecipes.get(position));

        }
    }
}
