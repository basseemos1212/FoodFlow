package com.EngBassemOs.foodflow.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "favMeals")
public class Meal {
    @PrimaryKey
    @NonNull
    private String idMeal;

    @ColumnInfo(name = "str_meal")
    private String strMeal;

    @ColumnInfo(name = "str_meal_thumb")
    private String strMealThumb;

    public Meal() {
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}

