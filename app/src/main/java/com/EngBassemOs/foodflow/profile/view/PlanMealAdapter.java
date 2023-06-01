package com.EngBassemOs.foodflow.profile.view;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.EngBassemOs.foodflow.MealDetail.view.MealDetail;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.profile.presenter.ProfilePresenterInterface;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PlanMealAdapter extends RecyclerView.Adapter<PlanMealAdapter.PlanMealAdapterViewHolder>{
    List<PlanMeal> planMeals;

    public PlanMealAdapter(List<PlanMeal> planMeals) {
        this.planMeals = planMeals;
    }

    public List<PlanMeal> getPlanMeals() {
        return planMeals;
    }

    public void setPlanMeals(List<PlanMeal> planMeals) {
        this.planMeals = planMeals;
        notifyDataSetChanged();
    }

    public PlanMealAdapter() {

    }

    @NonNull
    @Override
    public PlanMealAdapter.PlanMealAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.meal_view_holder, parent, false);
        PlanMealAdapter.PlanMealAdapterViewHolder viewHolder = new PlanMealAdapter.PlanMealAdapterViewHolder(view);
        Log.e("tag", "iam on the oncreateView Holder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanMealAdapterViewHolder holder, int position) {
        holder.txtTitle.setText(planMeals.get(position).getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(planMeals.get(position).getStrMealThumb())
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener(view -> {

            Intent intent =new Intent(view.getContext(), MealDetail.class);
            intent.putExtra("detailMeal",planMeals.get(position).copyPlanToDetail());
            view.getContext().startActivity(intent);

        });
    }




    @Override
    public int getItemCount() {
        return planMeals.size();
    }

    public class PlanMealAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;
        public View layout;

        public PlanMealAdapterViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.mealName);
            imageView = itemView.findViewById(R.id.mealThumb);
            constraintLayout = itemView.findViewById(R.id.mealParent);

        }
    }
}