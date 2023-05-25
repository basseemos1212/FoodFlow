package com.EngBassemOs.foodflow.db;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public interface LocalSource {
    void insertFavMeal(Meal meal);
    void deleteMovie(Meal meal);
    LiveData<List<Meal>> getAllMeals();
}
