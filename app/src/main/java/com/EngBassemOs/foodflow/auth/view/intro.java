package com.EngBassemOs.foodflow.auth.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.EngBassemOs.foodflow.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class intro extends Fragment {
    Button loginButton;
    Button signUpButton;
    TextView tv;

    private BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
            loginButton=view.findViewById(R.id.loginButton);
            signUpButton=view.findViewById(R.id.signUpButton);
            tv=view.findViewById(R.id.guest);
            loginButton.setOnClickListener(view1 -> {
                navController.navigate(R.id.action_intro_to_login);
            });
        signUpButton.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_intro_to_signUp);
        });
        loginButton.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_intro_to_login);
        });
        tv.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_intro_to_home);
        });


    }


}