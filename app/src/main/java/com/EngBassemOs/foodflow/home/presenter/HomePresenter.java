package com.EngBassemOs.foodflow.home.presenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.view.View;

import androidx.navigation.Navigation;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.firebaseFirestore.FireStoreHelper;
import com.EngBassemOs.foodflow.home.view.HomeInterface;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.network.area.SearchByAreaNetworkDelegate;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.EngBassemOs.foodflow.network.randomMeal.RandomMealNetworkDelegate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomePresenter implements SearchByAreaNetworkDelegate ,HomePresenterInterface, DetailMealNetworkDelegate, RandomMealNetworkDelegate {
    private RepositoryInterface repositoryInterface;
    private HomeInterface homeInterface;

    public HomePresenter(RepositoryInterface repositoryInterface,HomeInterface homeInterface) {
        this.repositoryInterface = repositoryInterface;
        this.homeInterface = homeInterface;
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        homeInterface.showData(meals);
    }

    @Override
    public void onFailureResult(String error) {
        homeInterface.showError(error);
    }

    @Override
    public void getSearchByAreaMeals(String area,String type) {
        repositoryInterface.getSearchByAreaMeals(this,area,type);

    }

    @Override
    public void logout() {
        FireStoreHelper fireStoreHelper=new FireStoreHelper();
        fireStoreHelper.postFavouriteMeals(repositoryInterface.getDetailMeals(),FirebaseAuth.getInstance().getCurrentUser().getUid());
        fireStoreHelper.postPlanMeals(repositoryInterface.getAllPlanMeals(),FirebaseAuth.getInstance().getCurrentUser().getUid());
        repositoryInterface.clearPlanTable();
        repositoryInterface.clearFavTable();
        homeInterface.logout();

    }

    @Override
    public void navigateToMealByID(String id) {
        repositoryInterface.getMealByID(id,this);
    }

    @Override
    public void getRandomMeal() {
       repositoryInterface.getRandomMeal(this);
    }


    @Override
    public void onSuccessIngResult(DetailMeal detailMeal)
    {
        homeInterface.confirmNavigate(detailMeal);
    }

    @Override
    public void onFailureIngResult(String error) {
        System.out.println(error);
    }

    @Override
    public void onRandomSuccess(DetailMeal detailMeal) {
        homeInterface.showRandomMeal(detailMeal);
    }

    @Override
    public void onRandomFail(String error) {

    }
}
