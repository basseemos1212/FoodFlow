package com.EngBassemOs.foodflow.search.controller;

import com.EngBassemOs.foodflow.model.Meal;

public interface SearchByAreaPresenterInterface {
     void getMeals();
     void addToFav(Meal meal);
}
