package com.EngBassemOs.foodflow.auth.signUp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.auth.signUp.contrller.SignUpPresenter;


public class SignUp extends Fragment implements SignUpinterface{
    private EditText emailText;
    private  EditText passText;
    private EditText rePassText;
    private ImageView signUpBtn;
    private TextView navToLoginBtn;
    private  SignUpPresenter signUpPresenter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailText=view.findViewById(R.id.emailText);
        passText=view.findViewById(R.id.pasText);
        rePassText=view.findViewById(R.id.rePassText);
        signUpBtn=view.findViewById(R.id.signUpBtn);
        navToLoginBtn=view.findViewById(R.id.gotoSignin);
        signUpPresenter=new SignUpPresenter(this);
        signUpBtn.setOnClickListener(view1 -> {
            String email = emailText.getText().toString();
            String password = passText.getText().toString();
            signUpPresenter.signUp(email,password);

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
        System.out.println("Succceeeeeeeeeeeeeeeeeeeesssssssssssssssssssssssssss");
    }

    @Override
    public void showSignUpError(String errorMessage) {
        System.out.println(errorMessage);
    }
}