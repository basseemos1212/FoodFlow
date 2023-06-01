package com.EngBassemOs.foodflow.profile.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.network.Ingredients.view.IngredientAdapter;
import com.EngBassemOs.foodflow.profile.presenter.ProfilePresenter;

import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder>{
    ArrayList<String> daysList = new ArrayList<>();
    String selected="Saturday";
    ProfilePresenter profilePresenter;



    public DayAdapter(ProfilePresenter profilePresenter) {

        this.profilePresenter=profilePresenter;
        daysList.add("Saturday");
        daysList.add("Sunday");
        daysList.add("Monday");
        daysList.add("Tuesday");
        daysList.add("Wednesday");
        daysList.add("Thursday");
        daysList.add("Friday");
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day_view_holder, parent, false);
        DayAdapter.DayViewHolder viewHolder = new DayAdapter.DayViewHolder(view);
        Log.e("tag", "iam on the oncreateView Holder");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        if (selected.equals(daysList.get(position))){
            holder.bol=true;
            holder.constraintLayout.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.selected_day));
        }else{
            holder.bol=false;
            holder.constraintLayout.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.countrybg_rip));
        }
        holder.txtTitle.setText(daysList.get(position));
        holder.constraintLayout.setOnClickListener(view ->{
            selected=daysList.get(position);
            notifyDataSetChanged();
            profilePresenter.getPlanMeal(selected);

        });
    }


    @Override
    public int getItemCount() {
        return daysList.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public ConstraintLayout constraintLayout;
        public boolean bol=false;


        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.dayStr);
            constraintLayout=itemView.findViewById(R.id.dayParent);



        }
    }
}
