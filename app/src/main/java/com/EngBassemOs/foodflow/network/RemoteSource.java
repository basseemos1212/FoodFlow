package com.EngBassemOs.foodflow.network;

import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;

public interface RemoteSource {
    void areaEnqueueCall(AreaNetworkDelegate areaNetworkDelegate);
    void searchByAreaMeals(SearchByAreaNetworkDelegate searchByAreaNetworkDelegate, String areaName,String type);
    void detailMealEnqeue(DetailMealNetworkDelegate detailMealNetworkDelegate,String mealID);

    void ingredientEnqueueCall(IngredientsNetworkDelegate ingredientsNetworkDelegate);
}
