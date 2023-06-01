package com.EngBassemOs.foodflow.home.presenter;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;

import java.util.List;

public interface HomePresenterInterface {
    void getSearchByAreaMeals(String area,String type);
    void logout();
    void navigateToMealByID(String id);
    void getRandomMeal();


}
