package com.EngBassemOs.foodflow.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.db.LocalSource;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface{
    private Context context;
    RemoteSource remoteSource;
    LocalSource localSource;
    private static Repository repository=null;
    public static Repository getInstance(RemoteSource _remoteSource,LocalSource _localSource,Context _context){
        if (repository==null){
            return new Repository(_remoteSource,_localSource,_context);
        }
        return repository;
    }
    private Repository(RemoteSource _remoteSource,LocalSource _localSource,Context _context ){
        this.remoteSource=_remoteSource;
        this.localSource=_localSource;
        this.context=_context;
    }
    @Override
    public void getAllAreas(AreaNetworkDelegate areaNetworkDelegate) {
        remoteSource.areaEnqueueCall(areaNetworkDelegate);
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
}
