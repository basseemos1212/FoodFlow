package com.EngBassemOs.foodflow.favoutrite.presenter;

import androidx.lifecycle.LiveData;

import com.EngBassemOs.foodflow.model.DetailMeal;

import java.util.List;

public interface FavPresenterInterface {
    void showData();
    void deleteFromFav(DetailMeal detailMeal);
    void deleteFavFromFirestore(DetailMeal detailMeal);
}
