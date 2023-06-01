package com.EngBassemOs.foodflow.network.detailMeail;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Ingredient;

import java.util.List;

public interface DetailMealNetworkDelegate {
     void onSuccessIngResult(DetailMeal detailMeal);
     void onFailureIngResult(String error);
}
