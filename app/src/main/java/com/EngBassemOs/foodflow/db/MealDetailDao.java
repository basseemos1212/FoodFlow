package com.EngBassemOs.foodflow.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

@Dao
public interface MealDetailDao {
    @Insert
    void insert(DetailMeal meal);
    @Delete
    void delete(DetailMeal meal);
    @Query("SELECT * FROM favourites")
    LiveData<List<DetailMeal>> getAllDetailMeals();
    @Query("DELETE FROM favourites")
    void clearFavourites();
}
