package com.EngBassemOs.foodflow.auth.signUp.view;

public interface SignUpinterface {

    void showProgress() ;
    void hideProgress();
    void showSignUpSuccess();
    void showSignUpError(String errorMessage);
}
