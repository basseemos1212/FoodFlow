package com.EngBassemOs.foodflow.search.controller;

import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;

public interface SearchPresenterInterFace {
    void getCategories();
    void getIngredients();
    void getAreas();
}
