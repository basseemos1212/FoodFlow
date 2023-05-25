package com.EngBassemOs.foodflow.favoutrite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.EngBassemOs.foodflow.db.FavMealRepo;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.home.view.MealAdapter;
import com.EngBassemOs.foodflow.model.Meal;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;
import java.util.List;


public class Favourite extends Fragment {
    RecyclerView mealRecycleView;
    MealAdapter favMealAdapter;
    List<Meal> meals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment+
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealRecycleView=view.findViewById(R.id.favMealRecView);
        CarouselLayoutManager carouselLayoutManager =new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        carouselLayoutManager.setMaxVisibleItems(1);
        mealRecycleView.setLayoutManager(carouselLayoutManager);
        favMealAdapter=new MealAdapter(getContext(), new ArrayList<>());
        mealRecycleView.setAdapter(favMealAdapter);
        FavMealRepo repo=new FavMealRepo(getContext());
        repo.getFavMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favMealAdapter.setMeals(meals);
                favMealAdapter.notifyDataSetChanged();

            }
        });
    }
}