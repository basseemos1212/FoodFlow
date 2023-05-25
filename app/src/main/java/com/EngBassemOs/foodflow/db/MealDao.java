package com.EngBassemOs.foodflow.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

@Dao
public interface MealDao {
    @Insert
    void insert(Meal meal);
    @Delete
    void delete(Meal meal);

    @Query("SELECT * FROM favMeals")
    LiveData<List<Meal>> getAllMeals();

    // Add other database operations you want to perform
}
