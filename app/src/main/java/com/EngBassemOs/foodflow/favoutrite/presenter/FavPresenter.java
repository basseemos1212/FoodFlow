package com.EngBassemOs.foodflow.favoutrite.presenter;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.favoutrite.view.FavInterface;
import com.EngBassemOs.foodflow.firebaseFirestore.FireStoreHelper;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FavPresenter implements  FavPresenterInterface{
    private RepositoryInterface repositoryInterface;
    private FavInterface favInterface;

    public FavPresenter(RepositoryInterface repositoryInterface,FavInterface favInterfac) {
        this.repositoryInterface = repositoryInterface;
        this.favInterface=favInterfac;
    }


    @Override
    public void showData() {
            favInterface.showLifeMeals(repositoryInterface.getDetailMeals());

    }

    @Override
    public void deleteFromFav(DetailMeal detailMeal) {
        repositoryInterface.deleteFavDetailMeal(detailMeal);
        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection(FirebaseAuth.getInstance().getUid()).document("type").
                collection("favMeals").document(detailMeal.getIdMeal()).delete();
    }

    @Override
    public void deleteFavFromFirestore(DetailMeal detailMeal) {

    }

}
