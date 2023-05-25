package com.EngBassemOs.foodflow.db;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.db.MealDao;
import com.EngBassemOs.foodflow.db.MealDatabase;
import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public class FavMealRepo {
    private Context context;
    private MealDao mealDao;
    private LiveData<List<Meal>> favMeals;

    public FavMealRepo(Context _context) {
        this.context = _context;
        MealDatabase mealDatabase = MealDatabase.getInstance(context.getApplicationContext());
        this.mealDao = mealDatabase.mealDao();
        favMeals = mealDao.getAllMeals();
    }

    public LiveData<List<Meal>> getFavMeals() {
        return favMeals;
    }


        public void insert(Meal meal) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mealDao.insert(meal);
                        showToast("Added to Favourite: " + meal.getStrMeal());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        if (e.toString().equals("android.database.sqlite.SQLiteConstraintException: UNIQUE constraint failed:" +
                                " favMeals.idMeal (code 1555 SQLITE_CONSTRAINT_PRIMARYKEY)")){
                            mealDao.delete(meal);
                            showToast("Deleted from Favourite: " + meal.getStrMeal());
                        }
                    }
                }
            }).start();



}
    private void showToast(final String message) {
        new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
