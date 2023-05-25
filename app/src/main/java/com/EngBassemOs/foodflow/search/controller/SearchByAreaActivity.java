package com.EngBassemOs.foodflow.search.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.search.view.OnSearchByAreaClickListner;
import com.EngBassemOs.foodflow.search.view.SearchByAreaAdapter;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.search.view.SearchByAreaInterFace;
import java.util.ArrayList;
import java.util.List;



public class SearchByAreaActivity extends AppCompatActivity implements SearchByAreaInterFace, OnSearchByAreaClickListner, SearchByAreaNetworkDelegate {
    RecyclerView searchMealRecycleView;
    SearchByAreaAdapter searchMealAdapter;
    AppClient client;
    SearchByAreaPresenter searchByAreaPresenter;
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_area);
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        ImageButton customButton =findViewById(R.id.toolbar_back_button);
        customButton.setOnClickListener(v -> finish());
        String data = getIntent().getStringExtra("myData");
        String type=getIntent().getStringExtra("type");
        searchMealRecycleView = findViewById(R.id.mByAreaRecycle);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        searchMealRecycleView.setLayoutManager(layoutManager);
        client= AppClient.getInstance();
        client.searchByAreaMeals(this,data,type);
        searchMealAdapter = new SearchByAreaAdapter( new ArrayList<>(),this);
        searchMealRecycleView.setAdapter(searchMealAdapter);
        searchMealRecycleView.setLayoutManager(layoutManager);
        searchByAreaPresenter=new SearchByAreaPresenter(this,Repository.getInstance(AppClient.getInstance(),ConcreteLocalSource.getInstance(getApplicationContext()),this));





    }

    @Override
    public void showData(List<Meal> meals) {
        searchMealAdapter.setMeals(meals);
        searchMealAdapter.notifyDataSetChanged();

    }

    @Override
    public void addFavMeal(Meal meal) {
        searchByAreaPresenter.addToFav(meal);

    }

    @Override
    public void onClick(Meal meal) {
        addFavMeal(meal);

    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        searchMealAdapter.setMeals(meals);
        searchMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureResult(String error) {
        searchMealRecycleView.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }


}