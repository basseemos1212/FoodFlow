package com.EngBassemOs.foodflow.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.Meal;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchByAreaAdapter extends RecyclerView.Adapter<SearchByAreaAdapter.SearchViewHolder> {
    private List<Meal> meals;
    private OnSearchByAreaClickListner listner;


    public SearchByAreaAdapter( List<Meal> meals,OnSearchByAreaClickListner listner) {

        this.meals = meals;
        this.listner=listner;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_meal_view_holder, parent, false);
        SearchByAreaAdapter.SearchViewHolder viewHolder = new SearchByAreaAdapter.SearchViewHolder(view);
        Log.e("tag", "iam on the oncreateView Holder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {



        holder.txtTitle.setText(meals.get(position).getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meals.get(position).getStrMealThumb())
                .into(holder.imageView);
        holder.favButton.setOnClickListener(view -> {

          listner.onClick(meals.get(position));

        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;
        public TextView favButton;
        public View layout;

        public SearchViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.mByAreaStr);
            favButton=itemView.findViewById(R.id.saveButton);
            imageView = itemView.findViewById(R.id.mByAreaImg);
            constraintLayout = itemView.findViewById(R.id.mByAreaParent);

        }
    }
}
