package com.EngBassemOs.foodflow.home.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.EngBassemOs.foodflow.home.view.MealAdapter;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.MealDBResponse;
import com.EngBassemOs.foodflow.model.MyResponse;
import com.EngBassemOs.foodflow.network.ApiClient;
import com.EngBassemOs.foodflow.network.ApiClientInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mig35.carousellayoutmanager.CenterScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {
    RecyclerView mealRecycleView;
    MealAdapter mealAdapter;
    List<Meal> meals;
    ProgressBar progressBar;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView=requireActivity().findViewById(R.id.bottomNavBar);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println(FirebaseAuth.getInstance().getCurrentUser());
        floatingActionButton=view.findViewById(R.id.floatingActionButton);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            floatingActionButton.setVisibility(View.GONE);
        } else {
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryColor)));
        progressBar.setVisibility(view.VISIBLE);
        mealRecycleView=view.findViewById(R.id.mealRecycleView);
        CarouselLayoutManager carouselLayoutManager =new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        carouselLayoutManager.setMaxVisibleItems(1);
        mealRecycleView.setLayoutManager(carouselLayoutManager);
        mealAdapter=new MealAdapter(getContext(), meals);
        ApiClientInterface apiService = ApiClient.getClient().create(ApiClientInterface.class);
        Call<MyResponse> call = apiService.getMyResponse();
        Call<MealDBResponse> mealCall=apiService.getMealsByArea("British");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();

                SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.apply();
                navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_home_to_intro);
                bottomNavigationView.setVisibility(View.GONE);

            }
        });
        mealCall.enqueue(new Callback<MealDBResponse>() {
            @Override
            public void onResponse(Call<MealDBResponse> call, Response<MealDBResponse> response) {
                meals=response.body().getMeals();
                System.out.println(response.body().getMeals().get(1).getStrMeal());
                mealAdapter.setMeals(meals);
                mealRecycleView.setAdapter(mealAdapter);
                mealRecycleView.addOnScrollListener(new CenterScrollListener());
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MealDBResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error loading data", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });




    }
}