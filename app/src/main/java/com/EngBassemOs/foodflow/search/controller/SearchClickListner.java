package com.EngBassemOs.foodflow.search.controller;

import android.content.Context;

import com.EngBassemOs.foodflow.model.Ingredient;

public interface SearchClickListner {
   void onClickOnIngredient(Context context, String ingredient,String type);

}
