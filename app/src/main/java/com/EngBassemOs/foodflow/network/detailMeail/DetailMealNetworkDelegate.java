package com.EngBassemOs.foodflow.network.detailMeail;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Ingredient;

import java.util.List;

public interface DetailMealNetworkDelegate {
    public void onSuccessIngResult(DetailMeal detailMeal);
    public void onFailureIngResult(String error);
}
