package com.EngBassemOs.foodflow.auth.signUp.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.auth.signUp.contrller.SignUpPresenter;
import com.EngBassemOs.foodflow.auth.view.intro;
import com.EngBassemOs.foodflow.databinding.FragmentSignUpBinding;


public class SignUp extends Fragment implements SignUpinterface{


    private  SignUpPresenter signUpPresenter;
    FragmentSignUpBinding fragmentSignUpBinding;
    NavController navController;
    String email="";
    String password="";
    String rePassword="";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSignUpBinding=FragmentSignUpBinding.inflate(inflater,container,false);
        return fragmentSignUpBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getView());

        fragmentSignUpBinding.gotoSignin.setOnClickListener(view12 -> navController.navigate(R.id.action_signUp_to_login));
        signUpPresenter=new SignUpPresenter(this);
        fragmentSignUpBinding.signUpBtn.setOnClickListener(view1 -> {
             email = fragmentSignUpBinding.emailText.getText().toString().trim();
             password = fragmentSignUpBinding.pasText.getText().toString().trim();
             rePassword=fragmentSignUpBinding.rePassText.getText().toString().trim();
            signUp();
        });

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showSignUpSuccess() {
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("status", "login");


        editor.apply();
        intro.STATUS="USER";
        navController.navigate(R.id.action_signUp_to_home);
    }

    @Override
    public void showSignUpError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
    private void signUp(){
        if (TextUtils.isEmpty(email)) {
            // Email is empty
            fragmentSignUpBinding.emailText.setError("Email is required");
            fragmentSignUpBinding.emailText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            // Email is empty
            fragmentSignUpBinding.emailText.setError("Email is required");
            fragmentSignUpBinding.emailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Invalid email format
            fragmentSignUpBinding.emailText.setError("Invalid email format");
            fragmentSignUpBinding.emailText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            // Password is empty
            fragmentSignUpBinding.pasText.setError("Password is required");
            fragmentSignUpBinding.pasText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            // Password is too short
            fragmentSignUpBinding.pasText.setError("Password must be at least 6 characters");
            fragmentSignUpBinding.pasText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(rePassword)) {
            // Confirm password is empty
            fragmentSignUpBinding.rePassText.setError("Please confirm your password");
            fragmentSignUpBinding.rePassText.requestFocus();
            return;
        }

        if (!password.equals(rePassword)) {
            // Passwords do not match
            fragmentSignUpBinding.rePassText.setError("Passwords do not match");
            fragmentSignUpBinding.rePassText.requestFocus();
            return;
        }
        signUpPresenter.signUp(email,password);
    }
}