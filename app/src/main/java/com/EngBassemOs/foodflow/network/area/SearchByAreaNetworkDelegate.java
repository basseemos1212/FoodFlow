package com.EngBassemOs.foodflow.network.area;

import com.EngBassemOs.foodflow.model.Area;
import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public interface SearchByAreaNetworkDelegate {
    public void onSuccessResult(List<Meal> meals);
    public void onFailureResult(String error);
}
