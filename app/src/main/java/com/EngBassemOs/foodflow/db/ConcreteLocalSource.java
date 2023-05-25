package com.EngBassemOs.foodflow.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{
    private MealDao dao;
    private static ConcreteLocalSource localSource=null;
    private LiveData<List<Meal>> favMeals;
    private ConcreteLocalSource(Context context){
        MealDatabase db =MealDatabase.getInstance(context.getApplicationContext());
        dao=db.mealDao();
        favMeals=dao.getAllMeals();
    }
    public static ConcreteLocalSource getInstance(Context context){
        if(localSource==null){
            return  new ConcreteLocalSource(context);
        }
        return localSource;
    }

    @Override
    public void insertFavMeal(Meal meal) {

        System.out.println(" i arrived Here");
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.insert(meal);

            }
        }).start();
    }

    @Override
    public void deleteMovie(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dao.delete(meal);
            }
        }).start();

    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return favMeals;
    }
}
