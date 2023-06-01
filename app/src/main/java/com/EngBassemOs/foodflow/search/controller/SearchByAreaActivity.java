package com.EngBassemOs.foodflow.search.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import android.widget.Toast;


import com.EngBassemOs.foodflow.MealDetail.view.MealDetail;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.search.view.OnSearchByAreaClickListner;
import com.EngBassemOs.foodflow.search.view.SearchByAreaAdapter;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.search.view.SearchByAreaInterFace;
import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;



public class SearchByAreaActivity extends AppCompatActivity implements SearchByAreaInterFace, OnSearchByAreaClickListner, SearchByAreaNetworkDelegate {
    RecyclerView searchMealRecycleView;
    SearchByAreaAdapter searchMealAdapter;
    AppClient client;
    SearchByAreaPresenter searchByAreaPresenter;
    List<Meal> meals=new ArrayList<>();
    private LottieAnimationView lottieAnimationView;
    private Disposable searchDisposable;
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
        SearchView searchView = findViewById(R.id.search_view);
        lottieAnimationView=findViewById(R.id.loadingAnimation);
        lottieAnimationView.setVisibility(View.VISIBLE);
        searchDisposable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            // Not used in this example
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            emitter.onNext(newText);
                            return false;
                        }
                    });
                })
                .debounce(300, TimeUnit.MILLISECONDS) // Adjust debounce time as needed
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::performSearch);



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
        searchByAreaPresenter=new SearchByAreaPresenter(this,Repository.getInstance(AppClient.getInstance(),ConcreteLocalSource.getInstance(getApplicationContext())));





    }

    @Override
    public void showData(List<Meal> meals) {
        this.meals=meals;
        searchMealAdapter.setMeals(meals);
        searchMealAdapter.notifyDataSetChanged();
        lottieAnimationView.setVisibility(View.GONE);

    }



    @Override
    public void confirmNavigate(DetailMeal detailMeal) {
        lottieAnimationView.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Intent intent = new Intent(this, MealDetail.class);
        intent.putExtra("detailMeal", detailMeal);
        startActivity(intent);

    }

    @Override
    public void onClick(String id) {

        searchByAreaPresenter.getMealByID(id);
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        this.meals=meals;
        searchMealAdapter.setMeals(meals);
        searchMealAdapter.notifyDataSetChanged();
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void onFailureResult(String error) {
        searchMealRecycleView.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
    private void performSearch(String query) {
        if (query.isEmpty()) {
            // If the search query is empty, show the original list of meals
            searchMealAdapter.setMeals(meals);
        } else {
            List<Meal> filteredMeals = new ArrayList<>();

            // Filter the meal list based on the search query
            for (Meal meal : meals) {
                if (meal.getStrMeal().toLowerCase().contains(query.toLowerCase())) {
                    filteredMeals.add(meal);
                }
            }

            if (filteredMeals.isEmpty()) {
                // Show a message or handle the case when no results are found
                // For example, display a "No results found" message
            }

            // Update the RecyclerView adapter with the filtered list
            searchMealAdapter.setMeals(filteredMeals);
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        if (searchDisposable != null && !searchDisposable.isDisposed()) {
            searchDisposable.dispose();
        }
    }


}