package com.EngBassemOs.foodflow.search.controller;

import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.search.view.SearchByAreaInterFace;

import java.util.List;

public class SearchByAreaPresenter implements SearchByAreaPresenterInterface, SearchByAreaNetworkDelegate {
    private SearchByAreaInterFace areaInterFace;
    private RepositoryInterface repositoryInterface;

    public SearchByAreaPresenter(SearchByAreaInterFace areaInterFace, RepositoryInterface repositoryInterface) {
        this.areaInterFace = areaInterFace;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void getMeals() {
        repositoryInterface.getFavMeals();
    }

    @Override
    public void addToFav(Meal meal) {
        repositoryInterface.insertFavMeal(meal);
    }

    @Override
    public void onSuccessResult(List<Meal> area) {
        areaInterFace.showData(area);
    }

    @Override
    public void onFailureResult(String error) {


    }
}
