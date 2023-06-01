package com.EngBassemOs.foodflow.profile.presenter;

import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;

public interface ProfilePresenterInterface {
    void getPlanMeal(String day);
    void deleteFromPlanMeals(PlanMeal planMeal);
}
