package com.EngBassemOs.foodflow.network.randomMeal;

import com.EngBassemOs.foodflow.model.Area;
import com.EngBassemOs.foodflow.model.DetailMeal;

import java.util.List;

public interface RandomMealNetworkDelegate {
    void onRandomSuccess(DetailMeal detailMeal);
    void onRandomFail(String error);
}
