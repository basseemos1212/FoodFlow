package com.EngBassemOs.foodflow.search.controller;

import android.widget.Toast;

import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;

import java.util.List;

public class SearchPresenter implements IngredientsNetworkDelegate {
    SearchInterface searchInterface;

    public SearchPresenter(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
    }

    @Override
    public void onSuccessIngResult(List<Ingredient> ingredientList) {
        searchInterface.showIngData(ingredientList);


    }

    @Override
    public void onFailureIngResult(String error) {
        System.out.println(error);

    }
}
