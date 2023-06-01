package com.EngBassemOs.foodflow.home.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.EngBassemOs.foodflow.MealDetail.view.MealDetail;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.network.NetworkUtils;
import com.bumptech.glide.Glide;

import java.util.List;




public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private static final String TAG = "RecycleView";
    private final Context context;
    private List<Meal> meals;
    private onHomeClickListener listener;

    public MealAdapter(Context context, List<Meal> meals,onHomeClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;

    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.meal_view_holder, parent, false);
        MealAdapter.MealViewHolder viewHolder = new MealAdapter.MealViewHolder(view);
        Log.e("tag", "iam on the oncreateView Holder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.txtTitle.setText(meals.get(position).getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meals.get(position).getStrMealThumb())
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener(view ->{


            if (NetworkUtils.isNetworkConnected(context)) {
                // Internet connection is available

                listener.navigate(meals.get(position).getIdMeal());
            } else {
                // No internet connection
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class MealViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;
        public View layout;

        public MealViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.mealName);
            imageView = itemView.findViewById(R.id.mealThumb);
            constraintLayout = itemView.findViewById(R.id.mealParent);

        }
    }
}
