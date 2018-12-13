package com.example.dara.miriamsrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    //Fields
    @SerializedName("id")
    private final int mId;
    @SerializedName("name")
    private final String mName;
    @SerializedName("ingredients")
    private final List<Ingredient> mIngredients;
    @SerializedName("steps")
    private final List<Step> mSteps;
    @SerializedName("servings")
    private final int mServings;
    @SerializedName("image")
    private final String mImage;

    private Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mIngredients = new ArrayList<>();
        in.readList(mIngredients, Ingredient.class.getClassLoader());
        mSteps = new ArrayList<>();
        in.readList(mSteps, Step.class.getClassLoader());
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

    public String getName() {
        return mName;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public List<Step> getSteps() {
        return mSteps;
    }


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
