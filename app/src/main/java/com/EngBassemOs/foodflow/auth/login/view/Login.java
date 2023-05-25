package com.EngBassemOs.foodflow.auth.login.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.auth.login.controller.LoginPresenter;


public class Login extends Fragment implements LoginInterface {
    private EditText emailText;
    private  EditText passText;
    private ImageView loginBtn;
    NavController navController;
    String email;
    String passWord;

    private LoginPresenter loginPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailText=view.findViewById(R.id.loginEmailText);
        passText=view.findViewById(R.id.passwordEmailText);
        loginBtn=view.findViewById(R.id.loginBtn);
        navController= Navigation.findNavController(view);
        loginPresenter=new LoginPresenter(this);
        loginBtn.setOnClickListener(view1 -> {
                 email=emailText.getText().toString();
                 passWord=passText.getText().toString();
                loginPresenter.signIn(email,passWord);
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoginSuccess() {
       navController.navigate(R.id.action_login_to_home);
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", passWord);
        editor.apply();

    }

    @Override
    public void showLoginError(String errorMessage) {
        System.out.println(errorMessage);
    }
}