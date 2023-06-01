package com.EngBassemOs.foodflow.auth.login.controller;



import android.util.Log;

import androidx.navigation.NavController;

import com.EngBassemOs.foodflow.auth.login.view.LoginInterface;
import com.EngBassemOs.foodflow.firebaseFirestore.FireStoreHelper;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;
import java.util.concurrent.Executor;

public class LoginPresenter implements LoginPresenterInterface{
    private final LoginInterface mView;
    private final FirebaseAuth firebaseAuth;
    private RepositoryInterface repositoryInterface;

    public LoginPresenter(LoginInterface mView,RepositoryInterface repositoryInterface) {
        this.mView = mView;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.repositoryInterface=repositoryInterface;
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

    @Override
    public void fillTheFavTable(DetailMeal detailMeal) {
        repositoryInterface.insertFavDetailMeal(detailMeal);

    }

    @Override
    public void fillThePlanMealTable(PlanMeal planMeal) {
        repositoryInterface.insertPlanMeal(planMeal);
    }

    @Override
    public void signInWithGoogle() {

    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        FireStoreHelper firestoreHandler= new FireStoreHelper(this);
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success, update UI with the signed-in user's information
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        // Add user data to Firestore
                        String userId = user.getUid();
                        String email = user.getEmail();
                        String displayName = user.getDisplayName();
                        String photoUrl = "";
                        if (user.getPhotoUrl() != null) {
                            photoUrl = user.getPhotoUrl().toString();
                        }
                        firestoreHandler.addUserData(userId, email, displayName, photoUrl);


                    } else {
                        // Sign-in failed
                        Log.e("TAG", "Firebase sign-in failed", task.getException());
                    }
                });
    }


}
