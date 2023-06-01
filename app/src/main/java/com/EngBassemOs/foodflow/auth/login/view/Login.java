package com.EngBassemOs.foodflow.auth.login.view;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.auth.login.controller.LoginPresenter;
import com.EngBassemOs.foodflow.auth.view.intro;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.firebaseFirestore.FireStoreHelper;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.model.RepositoryInterface;
import com.EngBassemOs.foodflow.network.AppClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class Login extends Fragment implements LoginInterface {
    private static final int RC_SIGN_IN = 123;
    private EditText emailText;
    private  EditText passText;
    NavController navController;
    String email="";
    String passWord="";
    private FirebaseAuth firebaseAuth;
    private FireStoreHelper firestoreHandler;
    private LoginPresenter loginPresenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        // Inflate the layout for this fragment
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailText=view.findViewById(R.id.loginEmailText);
        passText=view.findViewById(R.id.passwordEmailText);
        ImageView loginBtn = view.findViewById(R.id.loginBtn);
        navController= Navigation.findNavController(view);
        loginPresenter=new LoginPresenter(this, Repository.getInstance(AppClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        loginBtn.setOnClickListener(view1 -> {
                 email=emailText.getText().toString();
                 passWord=passText.getText().toString();
                signIn();
        });

        // Initialize and set click listener for your sign-in button
        Button googleSignInBtn = view.findViewById(R.id.googleSignInBtn);
        googleSignInBtn.setOnClickListener(v -> signInWithGoogle());

    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoginSuccess() {
        intro.STATUS="USER";
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("status", "login");
        editor.apply();
        FireStoreHelper fireStoreHelper=new FireStoreHelper(loginPresenter);
        fireStoreHelper.getFavouriteMeals();
       navController.navigate(R.id.action_login_to_home);


    }

    @Override
    public void showLoginError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGoogleSucces() {
        NavController navController=new NavController(getContext());
        navController.navigate(R.id.action_login_to_home);
    }
    private void signInWithGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleSignInClient signInClient = GoogleSignIn.getClient(requireContext(), gso);
        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            Log.e("TAG", "Google sign-in failed", e);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {


                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        String uid = user.getUid();
                        System.out.println(uid);
                        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("status", "login");


                        editor.apply();

                        firestoreHandler=new FireStoreHelper();
                        firestoreHandler.addUserData(account.getIdToken(), account.getEmail(), account.getDisplayName(),account.getPhotoUrl().toString());
                        FireStoreHelper fireStoreHelper=new FireStoreHelper(loginPresenter);
                        fireStoreHelper.getFavouriteMeals();
                        intro.STATUS="USER";
                        navController.navigate(R.id.action_login_to_home);
                        // Save the UID to Shared Preferences or perform further actions
                    } else {
                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void signIn(){
        if (TextUtils.isEmpty(email)) {
            // Email is empty
            emailText.setError("Email is required");
            emailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Invalid email format
            emailText.setError("Invalid email format");
            emailText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passWord)) {
            // Password is empty
            passText.setError("Password is required");
            passText.requestFocus();
            return;
        }

        if (passText.length() < 6) {
            // Password is too short
            passText.setError("Password must be at least 6 characters");
            passText.requestFocus();
            return;
        }
        loginPresenter.signIn(email,passWord);
    }

}