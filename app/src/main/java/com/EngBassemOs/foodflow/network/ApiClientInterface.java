package com.EngBassemOs.foodflow.network;

import com.EngBassemOs.foodflow.model.AreaResponse;
import com.EngBassemOs.foodflow.model.DetailMealResponse;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.IngredientResponse;
import com.EngBassemOs.foodflow.model.MealDBResponse;
import com.EngBassemOs.foodflow.model.MyResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClientInterface {

    @GET("filter.php")
    Call<MealDBResponse> getMealsByArea(@Query("a") String area);
    @GET("filter.php")
    Call<MealDBResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<MealDBResponse> getMealsByIngredient(@Query("i") String ingredient);
    @GET("lookup.php")
    Call<DetailMealResponse> getDetailMeal(@Query("i") String id);
    @GET("random.php")
    Call<DetailMealResponse> getRandomMeal();
    @GET("categories.php")
    Observable<MyResponse> getCategories();
    @GET("list.php?i=list")
    Observable<IngredientResponse> streamOnIngredient();
    @GET("list.php?a=list")
    Observable<AreaResponse> getAreas();
    @GET("lookup.php")
    Observable<DetailMealResponse> streamOnMealByID(@Query("i") String id);

}
