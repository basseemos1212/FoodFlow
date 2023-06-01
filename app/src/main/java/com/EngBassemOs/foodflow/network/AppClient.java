package com.EngBassemOs.foodflow.network;

import com.EngBassemOs.foodflow.model.AreaResponse;
import com.EngBassemOs.foodflow.model.DetailMealResponse;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.IngredientResponse;
import com.EngBassemOs.foodflow.model.MealDBResponse;
import com.EngBassemOs.foodflow.model.MyResponse;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.EngBassemOs.foodflow.network.randomMeal.RandomMealNetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppClient implements RemoteSource {

    public static String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static AppClient client = null;
    Call<MealDBResponse> call;
    ApiClientInterface apiInterface;

    private AppClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Add RxJava adapter
                .build();
        apiInterface = retrofit.create(ApiClientInterface.class);
    }

    public static AppClient getInstance() {
        if (client == null) {
            client = new AppClient();
        }
        return client;

    }


    @Override
    public void searchByAreaMeals(SearchByAreaNetworkDelegate searchByAreaNetworkDelegate, String areaName, String type) {


        if (type.equals("area")) {
            call = apiInterface.getMealsByArea(areaName);
        } else if (type.equals("ing")) {
            call = apiInterface.getMealsByIngredient(areaName);
        } else if (type.equals("category")) {
            call = apiInterface.getMealsByCategory(areaName);
        }
        call.enqueue(new Callback<MealDBResponse>() {
            @Override
            public void onResponse(Call<MealDBResponse> call, Response<MealDBResponse> response) {
                searchByAreaNetworkDelegate.onSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealDBResponse> call, Throwable t) {
                searchByAreaNetworkDelegate.onFailureResult(t.getMessage());
            }
        });


    }


    @Override
    public Observable<IngredientResponse> ingredientObservable() {
        return apiInterface.streamOnIngredient().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public void randomMealEnqeue(RandomMealNetworkDelegate randomMealNetworkDelegate) {

        Call<DetailMealResponse> call = apiInterface.getRandomMeal();
        call.enqueue(new Callback<DetailMealResponse>() {
            @Override
            public void onResponse(Call<DetailMealResponse> call, Response<DetailMealResponse> response) {
                randomMealNetworkDelegate.onRandomSuccess(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<DetailMealResponse> call, Throwable t) {
                randomMealNetworkDelegate.onRandomFail(t.getMessage());

            }
        });

    }

    @Override
    public void detailMealEnqeue(DetailMealNetworkDelegate detailMealNetworkDelegate, String mealID) {
        Call<DetailMealResponse> call = apiInterface.getDetailMeal(mealID);
        call.enqueue(new Callback<DetailMealResponse>() {
            @Override
            public void onResponse(Call<DetailMealResponse> call, Response<DetailMealResponse> response) {
                detailMealNetworkDelegate.onSuccessIngResult(response.body().getMeals().get(0));
            }

            @Override
            public void onFailure(Call<DetailMealResponse> call, Throwable t) {
                detailMealNetworkDelegate.onFailureIngResult(t.getMessage());

            }
        });

    }

    @Override
    public Observable<MyResponse> getCategories() {
        return apiInterface.getCategories().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<AreaResponse> getAreas() {
        return apiInterface.getAreas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<DetailMealResponse> streamOnMealByID(String id) {
        return apiInterface.streamOnMealByID(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
