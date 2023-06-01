package com.EngBassemOs.foodflow.MealDetail.controller;


import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.MealDetail.view.MealDetailInterface;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;

import java.util.List;

public class MealDetailPresenter implements MealDetailPresenterInterFace {
    MealDetailInterface mealDetailInterface;
    RepositoryInterface repositoryInterface;

    public MealDetailPresenter(MealDetailInterface mealDetailInterface, RepositoryInterface repositoryInterface) {
        this.mealDetailInterface = mealDetailInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void addToFav(DetailMeal detailMeal) {
        repositoryInterface.insertFavDetailMeal(detailMeal);

    }

    @Override
    public LiveData<List<DetailMeal>> getFavMeals() {

       return repositoryInterface.getDetailMeals();


    }

    @Override
    public void removeFromFav(DetailMeal detailMeal) {
        repositoryInterface.deleteFavDetailMeal(detailMeal);

    }

    @Override
    public void addToPlan(PlanMeal planMeal) {
        repositoryInterface.insertPlanMeal(planMeal);
    }

    @Override
    public LiveData<List<PlanMeal>> getPlanmeals() {
        return repositoryInterface.getAllPlanMeals();
    }

    @Override
    public void removeFromPlan(PlanMeal planMeal) {
        repositoryInterface.deletePlanMeal(planMeal);
    }
}
