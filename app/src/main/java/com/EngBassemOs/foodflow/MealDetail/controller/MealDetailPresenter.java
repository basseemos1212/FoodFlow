package com.EngBassemOs.foodflow.MealDetail.controller;


import com.EngBassemOs.foodflow.MealDetail.view.MealDetailInterface;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;

public class MealDetailPresenter implements DetailMealNetworkDelegate {
    MealDetailInterface mealDetailInterface;

    @Override
    public void onSuccessIngResult(DetailMeal detailMeal) {
        mealDetailInterface.showMeal(detailMeal);

    }

    @Override
    public void onFailureIngResult(String error) {
        mealDetailInterface.showError();

    }
}
