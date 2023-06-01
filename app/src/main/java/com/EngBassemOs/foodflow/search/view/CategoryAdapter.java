package com.EngBassemOs.foodflow.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.CategoryItem;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryItem> categoryItems;
    private OnAreaClickListner listner;



    public List<CategoryItem> getCategoryItems() {
        return categoryItems;

    }

    public void setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
        notifyDataSetChanged();

    }

    private static final String TAG="RecycleView";
    public CategoryAdapter( List<CategoryItem> categoryItems,OnAreaClickListner listner) {

        this.categoryItems = categoryItems;
        this.listner=listner;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.category_view_holder,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        Log.e("tag","iam on the oncreateView Holder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(categoryItems.get(position).getStrCategory());
        holder.constraintLayout.setOnClickListener(view -> listner.onClick(view.getContext(),categoryItems.get(position).getStrCategory(),"category"));
        Glide.with(holder.itemView.getContext())
                .load(categoryItems.get(position).getStrCategoryThumb())
                .into(holder.imageView);

    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;

        public ConstraintLayout constraintLayout;
        public View layout;
        public CircleImageView imageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView;
            txtTitle=itemView.findViewById( R.id.strCategory);
            imageView=itemView.findViewById(R.id.circleImageView);
            constraintLayout=itemView.findViewById(R.id.pParent);
        }
    }
    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

}
