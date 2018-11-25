package com.example.dara.miriamrecipes.data.model;


import com.google.gson.annotations.SerializedName;

public class Ingredient {

    //Fields
    @SerializedName("quantity")
    private int mQuantity;
    @SerializedName("measure")
    private String mMeasure;
    @SerializedName("ingredient")
    private String mIngredient;

    //Class constructor
    public Ingredient (int quantity, String measure, String ingredient) {
        mQuantity = quantity;
        mMeasure = measure;
        mIngredient = ingredient;
    }

    //Getter methods
    public int getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    public String getIngredient() {
        return mIngredient;
    }

}
