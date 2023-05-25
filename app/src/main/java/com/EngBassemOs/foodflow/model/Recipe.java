package com.EngBassemOs.foodflow.model;

public class Recipe {
    private String ingredientThumb;
    private String measure;
    private String ingredientName;

    public Recipe(String ingredientThumb, String measure, String ingredientName) {
        this.ingredientThumb = ingredientThumb;
        this.measure = measure;
        this.ingredientName = ingredientName;
    }

    public String getIngredientThumb() {
        return ingredientThumb;
    }

    public void setIngredientThumb(String ingredientThumb) {
        this.ingredientThumb = ingredientThumb;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
