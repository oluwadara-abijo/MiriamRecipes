package com.example.dara.miriamrecipes.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    //List object that holds ingredients
    private List<Ingredient> mIngredients;

    //Class constructor which creates a RecipeAdapter
    IngredientAdapter(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item_ingredient;
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        return new IngredientAdapter.IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder ingredientAdapterViewHolder, int i) {
        Ingredient currentIngredient = mIngredients.get(i);
        ingredientAdapterViewHolder.ingredient.setText(currentIngredient.getIngredient());
        ingredientAdapterViewHolder.measure.setText(currentIngredient.getMeasure());
        ingredientAdapterViewHolder.quantity.setText(String.valueOf(currentIngredient.getQuantity()));

    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    /**
     * When data changes, this method updates the list of recipes
     * and notifies the adapter to use the new values on it
     */
    void setIngredients(List<Ingredient> ingredients) {
        mIngredients = ingredients;
        notifyDataSetChanged();
    }

    //ViewHolder class
    class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        //UI elements
        @BindView(R.id.tv_ingredient)
        TextView ingredient;
        @BindView(R.id.tv_measure)
        TextView measure;
        @BindView(R.id.tv_quantity)
        TextView quantity;

        //ViewHolder class constructor
        IngredientAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
