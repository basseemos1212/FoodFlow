package com.EngBassemOs.foodflow.network;

import com.EngBassemOs.foodflow.model.AreaResponse;
import com.EngBassemOs.foodflow.model.DetailMealResponse;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.IngredientResponse;
import com.EngBassemOs.foodflow.model.MyResponse;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.EngBassemOs.foodflow.network.randomMeal.RandomMealNetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface RemoteSource {
    void searchByAreaMeals(SearchByAreaNetworkDelegate searchByAreaNetworkDelegate, String areaName,String type);
    void detailMealEnqeue(DetailMealNetworkDelegate detailMealNetworkDelegate,String mealID);
    void randomMealEnqeue(RandomMealNetworkDelegate randomMealNetworkDelegate);
    Observable<IngredientResponse>ingredientObservable();
    Observable<MyResponse> getCategories();
    Observable<AreaResponse> getAreas();
    Observable<DetailMealResponse> streamOnMealByID(String id);

}
