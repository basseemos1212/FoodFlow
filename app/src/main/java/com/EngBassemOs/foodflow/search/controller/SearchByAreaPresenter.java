package com.EngBassemOs.foodflow.search.controller;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.EngBassemOs.foodflow.search.view.SearchByAreaInterFace;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByAreaPresenter implements SearchByAreaPresenterInterface, SearchByAreaNetworkDelegate  {
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
    public void getMealByID(String id) {
        repositoryInterface.streamOnMealByID(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(detailMealResponse -> {
//                    System.out.println(detailMealResponse.getMeals().size());
                   areaInterFace.confirmNavigate(detailMealResponse.getMeals().get(0));
                });

    }

    @Override
    public void onSuccessResult(List<Meal> area) {
        areaInterFace.showData(area);
    }

    @Override
    public void onFailureResult(String error) {


    }


}
