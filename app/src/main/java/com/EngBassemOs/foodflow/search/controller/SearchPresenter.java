package com.EngBassemOs.foodflow.search.controller;

import android.widget.Toast;

import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter implements IngredientsNetworkDelegate ,SearchPresenterInterFace{
    SearchInterface searchInterface;
    RepositoryInterface repositoryInterface;

    public SearchPresenter(RepositoryInterface repositoryInterface,SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
        this.repositoryInterface=repositoryInterface;
    }

    @Override
    public void onSuccessIngResult(List<Ingredient> ingredientList) {
        searchInterface.showIngData(ingredientList);


    }

    @Override
    public void onFailureIngResult(String error) {
        System.out.println(error);

    }

    @Override
    public void getCategories() {
        repositoryInterface.getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(myResponse -> {
            searchInterface.showCategories(myResponse.getCategories());
            System.out.println(myResponse.getCategories().get(3).getStrCategory());

        },throwable -> {
            System.out.println("error");
        });
    }

    @Override
    public void getIngredients() {
        repositoryInterface.steamOnIngredients().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(ingredientResponse -> {
            searchInterface.showIngData(ingredientResponse.getMeals());
        },throwable -> {
            System.out.println("error");
        });
    }

    @Override
    public void getAreas() {
        repositoryInterface.streamOnAreas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(areaResponse -> {
            searchInterface.showAreas(areaResponse.getMeals());
        },throwable -> {
            System.out.println("error");
        });
    }
}
