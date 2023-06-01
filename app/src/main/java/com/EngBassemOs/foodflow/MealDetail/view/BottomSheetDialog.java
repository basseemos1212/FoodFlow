package com.EngBassemOs.foodflow.MealDetail.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.databinding.BottomSheetLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment  {
    private BottomSheetLayoutBinding binding;
    BottomSheetDialogInterFace bottomSheetDialogInterFace;

    public BottomSheetDialog(BottomSheetDialogInterFace bottomSheetDialogInterFace) {
        this.bottomSheetDialogInterFace = bottomSheetDialogInterFace;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        binding=BottomSheetLayoutBinding.inflate(getLayoutInflater());
        binding.saturdayBtn.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.saturdayBtn.getText().toString()));
        binding.sunDayBtn.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.sunDayBtn.getText().toString()));
        binding.btnMonday.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.btnMonday.getText().toString()));
        binding.btnTuesday.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.btnTuesday.getText().toString()));
        binding.btnWednesday.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.btnWednesday.getText().toString()));
        binding.btnThursday.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.btnThursday.getText().toString()));
        binding.btnFriday.setOnClickListener(view ->bottomSheetDialogInterFace.onClickButton(binding.btnFriday.getText().toString()));
        dialog.setContentView(binding.getRoot());
        return dialog;
    }
}
