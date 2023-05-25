package com.EngBassemOs.foodflow.MealDetail.view;

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
import com.EngBassemOs.foodflow.home.view.MealAdapter;
import com.EngBassemOs.foodflow.model.Recipe;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ReacipeViewHolder> {
    private static final String TAG = "RecycleView";
    private final Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    public Context getContext() {
        return context;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ReacipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_view_holder, parent, false);
        RecipeAdapter.ReacipeViewHolder viewHolder = new RecipeAdapter.ReacipeViewHolder(view);
        Log.e("tag", "iam on the oncreateView Holder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReacipeViewHolder holder, int position) {
        holder.ingStr.setText(recipes.get(position).getIngredientName());
        Glide.with(holder.itemView.getContext())
                .load(recipes.get(position).getIngredientThumb())
                .into(holder.imageView);
        holder.measure.setText(recipes.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ReacipeViewHolder extends RecyclerView.ViewHolder {

        public TextView ingStr;
        public TextView measure;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;
        public View layout;

        public ReacipeViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            ingStr = itemView.findViewById(R.id.ingeredientString);
            measure = itemView.findViewById(R.id.measureStr);
            imageView = itemView.findViewById(R.id.ingDetailThumb);


        }
    }
}
