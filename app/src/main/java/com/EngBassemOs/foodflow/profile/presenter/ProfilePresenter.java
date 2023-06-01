package com.EngBassemOs.foodflow.profile.presenter;

import androidx.lifecycle.Observer;

import com.EngBassemOs.foodflow.favoutrite.view.FavInterface;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.profile.view.ProfileIntercae;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ProfilePresenter implements ProfilePresenterInterface {
    private RepositoryInterface repositoryInterface;
    private ProfileIntercae profileIntercae;

    public ProfilePresenter(RepositoryInterface repositoryInterface, ProfileIntercae profileIntercae) {
        this.repositoryInterface = repositoryInterface;
        this.profileIntercae = profileIntercae;
    }

    @Override
    public void getPlanMeal(String day) {
        profileIntercae.showLifeMeals(repositoryInterface.getAllPlanMeals(),day);
    }

    @Override
    public void deleteFromPlanMeals(PlanMeal planMeal) {
        repositoryInterface.deletePlanMeal(planMeal);
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection(FirebaseAuth.getInstance().getUid()).document("type").
                collection("planMeal").document(planMeal.getIdMeal()).delete();
    }
}
