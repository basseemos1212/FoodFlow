package com.EngBassemOs.foodflow.profile.view;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;

public interface ProfileIntercae {
    void showLifeMeals(LiveData<List<PlanMeal>> detailMealList,String day);
}
