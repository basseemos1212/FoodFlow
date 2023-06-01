package com.EngBassemOs.foodflow.home.view;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public interface HomeInterface {
    void showData(List<Meal> meals) ;
    void showError(String error);
    void logout();
    void confirmNavigate(DetailMeal detailMeal);
    void showRandomMeal(DetailMeal detailMeal);


}
