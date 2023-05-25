package com.EngBassemOs.foodflow.auth.login.controller;



import com.EngBassemOs.foodflow.auth.login.view.LoginInterface;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginPresenterInterface{
    private final LoginInterface mView;
    private final FirebaseAuth firebaseAuth;

    public LoginPresenter(LoginInterface mView) {
        this.mView = mView;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signIn(String email, String password) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mView.showLoginSuccess();
                        } else {
                            mView.showLoginError(task.getException().getMessage());
                        }
                    });
        }catch (Exception f){
            System.out.println(f.toString());

        }


    }
}
