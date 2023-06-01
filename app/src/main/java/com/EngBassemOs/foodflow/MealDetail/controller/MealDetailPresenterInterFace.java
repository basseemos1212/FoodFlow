package com.EngBassemOs.foodflow.MealDetail.controller;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;

public interface MealDetailPresenterInterFace {

    void addToFav(DetailMeal detailMeal);
    LiveData<List<DetailMeal>> getFavMeals();
    void removeFromFav(DetailMeal detailMeal);
    void addToPlan(PlanMeal planMeal);
    LiveData<List<PlanMeal>> getPlanmeals();
    void removeFromPlan(PlanMeal planMeal);

}
