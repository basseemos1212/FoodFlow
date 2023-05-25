package com.EngBassemOs.foodflow.model;

import java.util.List;

public class IngredientResponse {

    private List<Ingredient> meals;

    public IngredientResponse() {
    }

    public List<Ingredient> getMeals() {
        return meals;
    }

    public void setMeals(List<Ingredient> meals) {
        this.meals = meals;
    }
}
