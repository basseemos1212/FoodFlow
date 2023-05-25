package com.EngBassemOs.foodflow.network.Ingredients.view;

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
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.search.controller.SearchClickListner;
import com.EngBassemOs.foodflow.search.view.SearchByAreaAdapter;
import com.bumptech.glide.Glide;


import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{
    private List<Ingredient> ingredientList;
    private SearchClickListner listner;

    public IngredientAdapter(List<Ingredient> ingredientList, SearchClickListner listner) {
        this.ingredientList = ingredientList;
        this.listner = listner;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_view_holder, parent, false);
        IngredientAdapter.IngredientViewHolder viewHolder = new IngredientAdapter.IngredientViewHolder(view);
        Log.e("tag", "iam on the oncreateView Holder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        String url = "https://www.themealdb.com/images/ingredients/" + ingredientList.get(position).getStrIngredient() + "-Small.png";
        holder.txtTitle.setText(ingredientList.get(position).getStrIngredient());
        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener(view -> {

            listner.onClickOnIngredient(view.getContext(),ingredientList.get(position).getStrIngredient(),"ing");

        });

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public ConstraintLayout constraintLayout;
        public ImageView imageView;
        public View layout;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.ingStr);
            imageView = itemView.findViewById(R.id.ingThumb);
            constraintLayout = itemView.findViewById(R.id.ingParent);

        }
    }
}
