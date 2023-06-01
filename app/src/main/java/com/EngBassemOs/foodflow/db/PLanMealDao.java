package com.EngBassemOs.foodflow.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;
@Dao
public interface PLanMealDao {
    @Insert
    void insert(PlanMeal meal);
    @Delete
    void delete(PlanMeal meal);
    @Query("SELECT * FROM planTable")
    LiveData<List<PlanMeal>> getAllPlanMeals();
    @Query("DELETE FROM planTable")
    void clearPlanTable();
}
