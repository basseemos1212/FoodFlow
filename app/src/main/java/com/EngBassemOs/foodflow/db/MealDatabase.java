package com.EngBassemOs.foodflow.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.EngBassemOs.foodflow.MealDetail.view.MealDetail;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.PlanMeal;

@Database(entities = {Meal.class, DetailMeal.class, PlanMeal.class}, version = 7, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance;

    public abstract MealDao mealDao();
    public abstract MealDetailDao mealDetailDao();
    public abstract PLanMealDao pLanMealDao();


    public static synchronized MealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, "meal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}