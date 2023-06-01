package com.EngBassemOs.foodflow.model;



import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.EngBassemOs.foodflow.network.randomMeal.RandomMealNetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface RepositoryInterface {
    void getSearchByAreaMeals(SearchByAreaNetworkDelegate searchByAreaNetworkDelegate,String area,String type);
    void getMealByID(String id, DetailMealNetworkDelegate detailMealNetworkDelegate);
    LiveData<List<Meal>> getFavMeals();
    void insertFavMeal(Meal meal);
    void deleteMeal(Meal meal);
    void insertFavDetailMeal(DetailMeal meal);
    void deleteFavDetailMeal(DetailMeal meal);
    LiveData<List<DetailMeal>> getDetailMeals();
    void insertPlanMeal(PlanMeal meal);
    void deletePlanMeal(PlanMeal meal);
    LiveData<List<PlanMeal>> getAllPlanMeals();
    void clearFavTable();
    void clearPlanTable();
    void fillFavTable(List<DetailMeal> detailMeals);
    void getRandomMeal(RandomMealNetworkDelegate randomMealNetworkDelegate);
    Observable<MyResponse> getCategories();
    Observable<IngredientResponse> steamOnIngredients();
    Observable<AreaResponse> streamOnAreas();
    Observable<DetailMealResponse> streamOnMealByID(String id);


}
