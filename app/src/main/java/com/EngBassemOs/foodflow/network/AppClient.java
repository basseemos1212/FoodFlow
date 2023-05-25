package com.EngBassemOs.foodflow.network;

import com.EngBassemOs.foodflow.model.AreaResponse;
import com.EngBassemOs.foodflow.model.DetailMealResponse;
import com.EngBassemOs.foodflow.model.IngredientResponse;
import com.EngBassemOs.foodflow.model.MealDBResponse;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient implements RemoteSource{
    public static String BASE_URL ="https://www.themealdb.com/api/json/v1/1/";
    private static AppClient client =null;
    Call<MealDBResponse> call;
    private AppClient(){}
    public static AppClient getInstance(){
        if(client==null){
            client=new AppClient();
        }
        return client;

    }

    @Override
    public void areaEnqueueCall(AreaNetworkDelegate areaNetworkDelegate){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiClientInterface apiInterface=retrofit.create(ApiClientInterface.class);

        Call<AreaResponse> call=apiInterface.getArea();

        call.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful()){
                    areaNetworkDelegate.onSuccessResult(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {
                areaNetworkDelegate.onFailureResult(t.getMessage());

            }
        });

    }

    @Override
    public void searchByAreaMeals(SearchByAreaNetworkDelegate searchByAreaNetworkDelegate, String areaName,String type) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiClientInterface apiInterface=retrofit.create(ApiClientInterface.class);


        if(type.equals("area")){
            call=apiInterface.getMealsByArea(areaName);
        }else if(type.equals("ing")){
            call=apiInterface.getMealsByIngredient(areaName);
        }else if(type.equals("category")){
            call=apiInterface.getMealsByCategory(areaName);
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
    public void ingredientEnqueueCall(IngredientsNetworkDelegate ingredientsNetworkDelegate) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiClientInterface apiInterface=retrofit.create(ApiClientInterface.class);
        Call<IngredientResponse> call=apiInterface.getIngredient();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                ingredientsNetworkDelegate.onSuccessIngResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                ingredientsNetworkDelegate.onFailureIngResult(t.getMessage());

            }
        });

    }
    @Override
    public void detailMealEnqeue(DetailMealNetworkDelegate detailMealNetworkDelegate, String mealID) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiClientInterface apiInterface=retrofit.create(ApiClientInterface.class);
        Call<DetailMealResponse> call=apiInterface.getDetailMeal(mealID);
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

}
