package com.EngBassemOs.foodflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.EngBassemOs.foodflow.model.LapModel;


public class lapResult extends Fragment {
    String str1="";
    String str2="";
    String str3="";
    String str4="";
    TextView strz;

    LapModel model;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lap_result, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model=lapResultArgs.fromBundle(getArguments()).getLapModel();
        strz=view.findViewById(R.id.strz);
        str1="Hello "+model.getEmail().toString();
        str2=" you are a great " +model.getGender();
        System.out.println(model.getLangList().toString());

        str3=model.getLangList().isEmpty()==false?" ":" O you love "+model.getLangList().toString();
        strz.setText(str1+str2+str3);







    }
}