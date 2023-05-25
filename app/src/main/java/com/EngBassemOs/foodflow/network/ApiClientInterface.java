package com.EngBassemOs.foodflow.network;

import com.EngBassemOs.foodflow.model.AreaResponse;
import com.EngBassemOs.foodflow.model.DetailMealResponse;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.IngredientResponse;
import com.EngBassemOs.foodflow.model.MealDBResponse;
import com.EngBassemOs.foodflow.model.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClientInterface {

    @GET("categories.php")
    Call<MyResponse> getMyResponse();
    @GET("filter.php")
    Call<MealDBResponse> getMealsByArea(@Query("a") String area);
    @GET("filter.php")
    Call<MealDBResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<MealDBResponse> getMealsByIngredient(@Query("i") String ingredient);
    @GET("list.php?a=list")
    Call<AreaResponse> getArea();
    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredient();
    @GET("lookup.php")
    Call<DetailMealResponse> getDetailMeal(@Query("i") String id);

}
