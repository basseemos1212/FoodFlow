package com.EngBassemOs.foodflow.auth.signUp.contrller;

import com.EngBassemOs.foodflow.auth.signUp.view.SignUpinterface;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenter implements SignUpPresenterInterface{
    private final SignUpinterface signUpinterface;
    private final FirebaseAuth firebaseAuth;

    public SignUpPresenter(SignUpinterface signUpinterface) {

        this.signUpinterface = signUpinterface;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signUpinterface.showSignUpSuccess();
                    } else {
                        signUpinterface.showSignUpError(task.getException().getMessage());
                    }
                });
    }


}
