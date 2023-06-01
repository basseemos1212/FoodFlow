package com.EngBassemOs.foodflow.auth.login.controller;

import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;

import java.util.List;

public interface LoginPresenterInterface {
    void signIn(String email, String password);
    void fillTheFavTable(DetailMeal detailMeal);
    void fillThePlanMealTable(PlanMeal planMeal);
    void signInWithGoogle();
}
