package com.EngBassemOs.foodflow.favoutrite.view;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.DetailMeal;

import java.util.List;

public interface FavInterface {
    void showLifeMeals(LiveData<List<DetailMeal>> detailMealList);
}
