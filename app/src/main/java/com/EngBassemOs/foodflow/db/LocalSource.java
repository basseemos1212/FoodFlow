package com.EngBassemOs.foodflow.db;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;

public interface LocalSource {
    void insertFavMeal(Meal meal);
    void deleteMovie(Meal meal);
    LiveData<List<Meal>> getAllMeals();
    void insertFavDetailMeal(DetailMeal meal);
    void delteDetailMeal(DetailMeal meal);
    LiveData<List<DetailMeal>> getAllDetailMeals();
    LiveData<List<PlanMeal>> getAllPlanMeals();
    void insertPlanMeal(PlanMeal meal);
    void deletePlanMeal(PlanMeal meal);
    void deleteFavTable();
    void fillFavTable(List<DetailMeal> detailMeals);
    void deletePlanTable();
    void fillPlanTable(List<PlanMeal> planMeals);



}
