package com.EngBassemOs.foodflow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealDBResponse {

    @SerializedName("meals")
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
