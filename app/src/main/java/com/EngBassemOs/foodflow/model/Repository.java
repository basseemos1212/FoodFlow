package com.EngBassemOs.foodflow.model;

import android.content.Context;


import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.db.LocalSource;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.RemoteSource;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.EngBassemOs.foodflow.network.randomMeal.RandomMealNetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class Repository implements RepositoryInterface{

    RemoteSource remoteSource;
    LocalSource localSource;
    private static Repository repository=null;
    public static Repository getInstance(RemoteSource _remoteSource,LocalSource _localSource){
        if (repository==null){
            return new Repository(_remoteSource,_localSource);
        }
        return repository;
    }
    private Repository(RemoteSource _remoteSource,LocalSource _localSource){
        this.remoteSource=_remoteSource;
        this.localSource=_localSource;

    }

    @Override
    public void getSearchByAreaMeals(SearchByAreaNetworkDelegate searchByAreaNetworkDelegate,String area,String type) {
        remoteSource.searchByAreaMeals(searchByAreaNetworkDelegate,area,type);
    }

    @Override
    public LiveData<List<Meal>> getFavMeals() {
        return localSource.getAllMeals();
    }

    @Override
    public void insertFavMeal(Meal meal) {


    localSource.insertFavMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.deleteMovie(meal);
    }

    @Override
    public void insertFavDetailMeal(DetailMeal meal) {
        localSource.insertFavDetailMeal(meal);
    }

    @Override
    public void deleteFavDetailMeal(DetailMeal meal) {
        localSource.delteDetailMeal(meal);
    }

    @Override
    public LiveData<List<DetailMeal>> getDetailMeals() {
        return localSource.getAllDetailMeals();
    }

    @Override
    public void insertPlanMeal(PlanMeal meal) {
        localSource.insertPlanMeal(meal);
    }

    @Override
    public void deletePlanMeal(PlanMeal meal) {
        localSource.deletePlanMeal(meal);
    }

    @Override
    public LiveData<List<PlanMeal>> getAllPlanMeals() {
        return localSource.getAllPlanMeals();
    }

    @Override
    public void clearFavTable() {
        localSource.deleteFavTable();
    }

    @Override
    public void clearPlanTable() {
        localSource.deletePlanTable();
    }

    @Override
    public void fillFavTable(List<DetailMeal> detailMeals) {
        localSource.fillFavTable(detailMeals);
    }

    @Override
    public void getRandomMeal(RandomMealNetworkDelegate randomMealNetworkDelegate) {
        remoteSource.randomMealEnqeue(randomMealNetworkDelegate);
    }

    @Override
    public Observable<MyResponse> getCategories() {
        return remoteSource.getCategories();
    }

    @Override
    public Observable<IngredientResponse> steamOnIngredients() {
        return remoteSource.ingredientObservable();
    }

    @Override
    public Observable<AreaResponse> streamOnAreas() {
        return remoteSource.getAreas();
    }

    @Override
    public Observable<DetailMealResponse> streamOnMealByID(String id) {
        return remoteSource.streamOnMealByID(id);
    }


    @Override
    public void getMealByID(String id, DetailMealNetworkDelegate detailMealNetworkDelegate) {
        remoteSource.detailMealEnqeue(detailMealNetworkDelegate,id);
    }
}
