package com.EngBassemOs.foodflow.auth.login.view;

public interface LoginInterface {
    void showProgress();
    void hideProgress();
    void showLoginSuccess();
    void showLoginError(String errorMessage);
    void showGoogleSucces();
}
