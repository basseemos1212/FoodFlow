package com.EngBassemOs.foodflow.network.Ingredients;

import com.EngBassemOs.foodflow.model.Area;
import com.EngBassemOs.foodflow.model.Ingredient;


import java.util.List;

public interface IngredientsNetworkDelegate {
    public void onSuccessIngResult(List<Ingredient> ingredientList);
    public void onFailureIngResult(String error);
}
