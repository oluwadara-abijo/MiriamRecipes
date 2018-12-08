package com.example.dara.miriamrecipes.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dara.miriamrecipes.R;
import com.example.dara.miriamrecipes.data.model.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// Custom adapter class that displays a list of all steps in a RecyclerView
public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MasterListAdapterViewHolder> {

    //List object that holds ingredients
    private List<Step> mSteps;

    //Create an instance of the click handling interface
    private final ItemClickListener mItemClickListener;

    //Class constructor which creates a RecipeAdapter
    MasterListAdapter(List<Step> steps, ItemClickListener itemClickListener) {
        mSteps = steps;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MasterListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item_recipe_steps;
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        return new MasterListAdapter.MasterListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasterListAdapterViewHolder masterListAdapterViewHolder, int i) {
        Step currentStep = mSteps.get(i);
        masterListAdapterViewHolder.stepTextView.setText(currentStep.getShortDescription());

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    //Interface for click handling
    public interface ItemClickListener {
        void onItemClickListener(Step step);
    }

    //ViewHolder class
    class MasterListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //UI elements
        @BindView(R.id.tv_recipe_step)
        TextView stepTextView;

        //ViewHolder class constructor
        MasterListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClickListener(mSteps.get(position));

        }
    }


}
