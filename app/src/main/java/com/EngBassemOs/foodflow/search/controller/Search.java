package com.EngBassemOs.foodflow.search.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.Area;
import com.EngBassemOs.foodflow.model.CategoryItem;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.MyResponse;
import com.EngBassemOs.foodflow.network.ApiClient;
import com.EngBassemOs.foodflow.network.ApiClientInterface;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;
import com.EngBassemOs.foodflow.network.Ingredients.view.IngredientAdapter;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.search.view.AreaAdapter;
import com.EngBassemOs.foodflow.search.view.CategoryAdapter;
import com.EngBassemOs.foodflow.search.view.OnAreaClickListner;
import com.EngBassemOs.foodflow.search.view.SearchByAreaAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Search extends Fragment implements AreaNetworkDelegate , OnAreaClickListner ,IngredientsNetworkDelegate,SearchClickListner,SearchInterface{
    List<CategoryItem> categoryItems;
    CategoryAdapter recycleAdapter;
    RecyclerView categoryRecycleView;
    AreaAdapter areaAdapter;
    RecyclerView areaRecycleView;
    RecyclerView ingRecycleView;
    IngredientAdapter ingredientAdapter;
    AppClient appClient;
    SearchPresenter searchPresenter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //old and bad work need to implement
        categoryRecycleView = view.findViewById(R.id.searchRecycleView);
        categoryRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecycleView.setLayoutManager(linearLayoutManager);
        recycleAdapter = new CategoryAdapter(getContext(), categoryItems);
        ApiClientInterface apiService = ApiClient.getClient().create(ApiClientInterface.class);
        Call<MyResponse> call = apiService.getMyResponse();
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                System.out.println(response.body().toString());
                categoryItems = response.body().getCategories();
                Log.d("TAG", "Response = " + categoryItems.get(1).getStrCategory());
                recycleAdapter.setCategoryItems(categoryItems);
                categoryRecycleView.setAdapter(recycleAdapter);
            }
            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error loading data", Toast.LENGTH_SHORT).show();

            }
        });
        //________________________________________________________________________//
        //MVC
        areaRecycleView =view.findViewById(R.id.areaRecycleView);
        areaRecycleView.setHasFixedSize(true);
        appClient= AppClient.getInstance();
        appClient.areaEnqueueCall(this);
        LinearLayoutManager areaLayoutManager=new LinearLayoutManager(getContext().getApplicationContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        areaAdapter = new AreaAdapter(getContext(), new ArrayList<Area>(),this);
        areaRecycleView.setAdapter(areaAdapter);
        areaRecycleView.setLayoutManager(areaLayoutManager);
        //______________________________________________________________________//
        //MVP
        ingRecycleView=view.findViewById(R.id.ingRecycleView);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        appClient.ingredientEnqueueCall(this);
        ingredientAdapter=new IngredientAdapter(new ArrayList<>(),this);
        ingRecycleView.setAdapter(ingredientAdapter);
        ingRecycleView.setLayoutManager(layoutManager);
        searchPresenter=new SearchPresenter(this);





    }

    @Override
    public void onSuccessResult(List<Area> area) {
        areaAdapter.setAreas(area);
        areaAdapter.notifyDataSetChanged();
    }



    @Override
    public void onFailureResult(String error) {
        areaRecycleView.setVisibility(View.GONE);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(Context context, String areaName,String type) {
        Intent intent = new Intent(getContext(), SearchByAreaActivity.class);
        intent.putExtra("myData", areaName);
        intent.putExtra("type", type);



        context.startActivity(intent);
    }

    @Override
    public void onClickOnIngredient(Context context,String ingredient,String type) {
        Intent intent = new Intent(getContext(), SearchByAreaActivity.class);
        intent.putExtra("myData", ingredient);
        intent.putExtra("type", type);



        context.startActivity(intent);
    }

    @Override
    public void onSuccessIngResult(List<Ingredient> ingredientList) {
        ingredientAdapter.setIngredientList(ingredientList);
        ingredientAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailureIngResult(String error) {
        ingRecycleView.setVisibility(View.GONE);
        Toast.makeText(getActivity().getApplicationContext(), error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showIngData(List<Ingredient> ingredients) {
        ingredientAdapter.setIngredientList(ingredients);
        ingredientAdapter.notifyDataSetChanged();

    }
}