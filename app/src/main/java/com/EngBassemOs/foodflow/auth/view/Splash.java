package com.EngBassemOs.foodflow.auth.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.EngBassemOs.foodflow.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class Splash extends Fragment {

BottomNavigationView bottomNavigationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavBar);
        bottomNavigationView.setVisibility(View.GONE);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the NavController object
        NavController navController = Navigation.findNavController(view);
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", Context.MODE_PRIVATE);
        String status = preferences.getString("status", null);

        System.out.println("this is the statusss  "+status);
        if (status!=null&&status.equals("login")) {

            new Handler().postDelayed(() -> navController.navigate(R.id.action_splash_to_home), 4000);
        } else {
            // User is not signed in automatically
            new Handler().postDelayed(() -> navController.navigate(R.id.action_splash_to_intro), 4000);
        }





    }
}