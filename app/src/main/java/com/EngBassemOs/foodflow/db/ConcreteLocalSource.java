package com.EngBassemOs.foodflow.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{
    private MealDao dao;
    private MealDetailDao mealDetailDao;
    private PLanMealDao pLanMealDao;
    private static ConcreteLocalSource localSource=null;
    private LiveData<List<Meal>> favMeals;
    private ConcreteLocalSource(Context context){
        MealDatabase db =MealDatabase.getInstance(context.getApplicationContext());
        dao=db.mealDao();
        mealDetailDao=db.mealDetailDao();
        pLanMealDao=db.pLanMealDao();
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
        new Thread(() -> dao.insert(meal)).start();
    }
    @Override
    public void deleteMovie(Meal meal) {
        new Thread(() -> dao.delete(meal)).start();

    }
    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return favMeals;
    }

    @Override
    public void insertFavDetailMeal(DetailMeal meal) {
        System.out.println("iamm herererereer");
        new Thread(() -> mealDetailDao.insert(meal)).start();
    }

    @Override
    public void delteDetailMeal(DetailMeal meal) {
        new Thread(() -> mealDetailDao.delete(meal)).start();
    }

    @Override
    public LiveData<List<DetailMeal>> getAllDetailMeals() {
        return mealDetailDao.getAllDetailMeals();
    }

    @Override
    public LiveData<List<PlanMeal>> getAllPlanMeals() {
        return pLanMealDao.getAllPlanMeals();
    }

    @Override
    public void insertPlanMeal(PlanMeal meal) {
        new Thread(() -> pLanMealDao.insert(meal)).start();

    }

    @Override
    public void deletePlanMeal(PlanMeal meal) {
        new Thread(() -> pLanMealDao.delete(meal)).start();

    }

    @Override
    public void deleteFavTable() {
        new Thread(() -> mealDetailDao.clearFavourites()).start();
    }

    @Override
    public void fillFavTable(List<DetailMeal> detailMeals) {
        for (DetailMeal detailMeal:detailMeals){
            new Thread(()->localSource.insertFavDetailMeal(detailMeal)).start();
        }
    }

    @Override
    public void deletePlanTable() {
        new Thread(() -> pLanMealDao.clearPlanTable()).start();
    }

    @Override
    public void fillPlanTable(List<PlanMeal> planMeals) {
        for (PlanMeal planMeal:planMeals) {
            new Thread(() -> pLanMealDao.insert(planMeal)).start();

        }


    }

}
