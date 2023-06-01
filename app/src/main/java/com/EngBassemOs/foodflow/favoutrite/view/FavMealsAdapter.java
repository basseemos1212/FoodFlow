package com.EngBassemOs.foodflow.favoutrite.view;

import android.content.Context;
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
import com.EngBassemOs.foodflow.home.view.MealAdapter;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.bumptech.glide.Glide;

import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.DetailMealViewHolder>{
    private static final String TAG = "RecycleView";

    private final Context context;
    private List<DetailMeal> meals;

    public FavMealsAdapter(Context context, List<DetailMeal> meals) {
        this.context = context;
        this.meals = meals;
    }

    public List<DetailMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<DetailMeal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public FavMealsAdapter.DetailMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            View view = inflater.inflate(R.layout.meal_view_holder, parent, false);
            FavMealsAdapter.DetailMealViewHolder viewHolder = new FavMealsAdapter.DetailMealViewHolder(view);
            Log.e("tag", "iam on the oncreateView Holder");


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull FavMealsAdapter.DetailMealViewHolder holder, int position) {
        holder.txtTitle.setText(meals.get(position).getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meals.get(position).getStrMealThumb())
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener(view -> {

            Intent intent =new Intent(context, MealDetail.class);
            intent.putExtra("detailMeal",meals.get(position));
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }



    public class DetailMealViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;
        public View layout;

        public DetailMealViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.mealName);
            imageView = itemView.findViewById(R.id.mealThumb);
            constraintLayout = itemView.findViewById(R.id.mealParent);

        }
    }


}
