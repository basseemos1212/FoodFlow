package com.EngBassemOs.foodflow.search.view;

import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public interface SearchByAreaInterFace {
    public void showData(List<Meal> meals);
    public void addFavMeal(Meal meal);

}
