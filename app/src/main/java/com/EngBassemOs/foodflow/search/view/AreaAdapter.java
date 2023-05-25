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
import com.EngBassemOs.foodflow.model.Area;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {
    private final Context context;
    private List<Area> areas;
    private OnAreaClickListner listener;



    public AreaAdapter(Context context, List<Area> areas, OnAreaClickListner listener) {
        this.context = context;
        this.areas = areas;
        this.listener=listener;
    }

    public Context getContext() {
        return context;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.country_view_holder,parent,false);
        AreaAdapter.AreaViewHolder viewHolder=new AreaAdapter.AreaViewHolder(view);
        Log.e("tag","iam on the oncreateView Holder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        holder.txtTitle.setText(areas.get(position).getStrArea());
        holder.constraintLayout.setOnClickListener(view -> listener.onClick(getContext(),areas.get(position).getStrArea(),"area"));

    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public class AreaViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;

        public ConstraintLayout constraintLayout;
        public View layout;



        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            txtTitle = itemView.findViewById(R.id.countryStr);
            constraintLayout = itemView.findViewById(R.id.countryParent);
        }
    }

}




