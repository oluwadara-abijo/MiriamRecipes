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
import com.example.dara.miriamrecipes.data.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// Custom adapter class that displays a list of all steps in a RecyclerView
public class MasterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_INGREDIENT = 0;
    private final int VIEW_TYPE_STEP = 1;

    //List object that holds ingredients
    private List<Ingredient> mIngredients;
    //List object that holds steps
    private List<Step> mSteps;

    //Create an instance of the click handling interface
    private final MasterListAdapter.ItemClickListener mItemClickListener;

    //Class constructor which creates a MasterListAdapter
    MasterListAdapter(List<Ingredient> ingredients, List<Step> steps,
                      MasterListAdapter.ItemClickListener itemClickListener) {
        mIngredients = ingredients;
        mSteps = steps;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item_recipe;
        View itemView = LayoutInflater.from(context).inflate(layout, viewGroup, false);

        if (viewType == VIEW_TYPE_INGREDIENT) {
            return new IngredientViewHolder(itemView);
        }

        if (viewType == VIEW_TYPE_STEP) {
            return new StepViewHolder(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof IngredientViewHolder) {
            ((IngredientViewHolder) viewHolder).bind(mIngredients.get(position));
        }

        if (viewHolder instanceof StepViewHolder) {
            ((StepViewHolder) viewHolder).bind(mSteps.get(position - mIngredients.size()));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position < mIngredients.size()) {
            return VIEW_TYPE_INGREDIENT;
        }

        if (position - mIngredients.size() < mSteps.size()) {
            return VIEW_TYPE_STEP;
        }

        return -1;
    }

    @Override
    public int getItemCount() {
        return mIngredients.size() + mSteps.size();
    }

    //ViewHolder class for Ingredients
    class IngredientViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_name_textView)
        TextView recipeStep;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Ingredient ingredient) {
            recipeStep.setText(ingredient.getIngredient());
        }
    }

    //ViewHolder class for steps
    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipe_name_textView)
        TextView recipeStep;


        StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Step step) {
            recipeStep.setText(step.getShortDescription());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClickListener(mSteps.get(position));
        }
    }

    //Interface for click handling
    public interface ItemClickListener {
        void onItemClickListener(Step step);
    }


}
