package com.EngBassemOs.foodflow.model;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;

import java.util.List;

public interface RepositoryInterface {
    void getAllAreas(AreaNetworkDelegate areaNetworkDelegate);
    LiveData<List<Meal>> getFavMeals();
    void insertFavMeal(Meal meal);
    void deleteMeal(Meal meal);
}
