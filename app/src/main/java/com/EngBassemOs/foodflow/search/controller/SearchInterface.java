package com.EngBassemOs.foodflow.search.controller;

import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public interface SearchInterface {
    public void showIngData(List<Ingredient> ingredients);
}
