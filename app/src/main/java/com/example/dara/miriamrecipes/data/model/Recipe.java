package com.example.dara.miriamrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Recipe implements Parcelable {

    //Fields
    private int mId;
    private String mName;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;
    private int mServings;
    private String mImage;

    //Class constructor
    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps,
                  int servings, String image) {
        mId = id;
        mName = name;
        mIngredients = ingredients;
        mSteps = steps;
        mServings = servings;
        mImage = image;
    }

    //Getter methods
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public int getServings() {
        return mServings;
    }

    public String getImage() {
        return mImage;
    }

    protected Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mIngredients = in.readParcelable(Ingredient.class.getClassLoader());
        mSteps = in.readParcelable(Step.class.getClassLoader());
        mServings = in.readInt();
        mImage = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeList(mIngredients);
        dest.writeList(mSteps);
        dest.writeInt(mServings);
        dest.writeString(mImage);
    }
}
