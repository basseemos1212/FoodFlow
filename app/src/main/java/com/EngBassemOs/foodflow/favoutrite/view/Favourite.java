package com.EngBassemOs.foodflow.favoutrite.view;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.EngBassemOs.foodflow.auth.view.intro;
import com.EngBassemOs.foodflow.databinding.FragmentFavouriteBinding;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.db.FavMealRepo;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.favoutrite.presenter.FavPresenter;
import com.EngBassemOs.foodflow.home.view.MealAdapter;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.RemoteSource;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Favourite extends Fragment implements FavInterface {

    FavMealsAdapter favMealAdapter;
    FragmentFavouriteBinding fragmentFavouriteBinding;
    FavPresenter favPresenter;
    List<DetailMeal> detailMealss = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFavouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment+
        return fragmentFavouriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        String status = preferences.getString("status","");
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentFavouriteBinding.include3.personToolbar);

        if(intro.STATUS.equals("GUEST")){
            fragmentFavouriteBinding.signupAnimation.setVisibility(View.VISIBLE);
            fragmentFavouriteBinding.favMealRecView.setVisibility(View.GONE);
            fragmentFavouriteBinding.textView44.setVisibility(View.GONE);
            fragmentFavouriteBinding.signupAnimation.setOnClickListener(view1 -> {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_favourite_to_intro);
                BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavBar);
                bottomNavigationView.setVisibility(View.GONE);
            });
        }
        if (status.isEmpty()||status.equals("logout")) {

            fragmentFavouriteBinding.include3.personToolbar.setVisibility(View.GONE);
        } else {
            fragmentFavouriteBinding.include3.personToolbar.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentFavouriteBinding.favMealRecView.setLayoutManager(linearLayoutManager);
        favMealAdapter = new FavMealsAdapter(getContext(), new ArrayList<>());
        fragmentFavouriteBinding.favMealRecView.setAdapter(favMealAdapter);
        favPresenter = new FavPresenter(Repository.getInstance(AppClient.getInstance(), ConcreteLocalSource.getInstance(getActivity().getApplicationContext())), this);
        favPresenter.showData();
        ItemTouchHelper.Callback itemTouchCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // Enable dragging vertically
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END; // Enable swiping left and right
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Calculate the fraction of the swipe distance
                    float alpha = 1.0f - Math.abs(dX) / (float) viewHolder.itemView.getWidth();

                    Drawable deleteIcon = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.ic_baseline_delete_forever_24);
                    int deleteIconWidth = viewHolder.itemView.getWidth() / 2;
                    int deleteIconHeight = viewHolder.itemView.getHeight() / 2;
                    int itemHeight = viewHolder.itemView.getHeight();
                    int iconMargin = (itemHeight - deleteIconHeight) / 2;
                    int deleteIconLeft;
                    int deleteIconRight;
                    int deleteIconTop = viewHolder.itemView.getTop() + iconMargin;
                    int deleteIconBottom = deleteIconTop + deleteIconHeight;

                    if (dX > 0) {
                        // Swipe to the right
                        deleteIconLeft = viewHolder.itemView.getLeft() + iconMargin;
                        deleteIconRight = deleteIconLeft + deleteIconWidth;
                    } else {
                        // Swipe to the left
                        deleteIconRight = viewHolder.itemView.getRight() - iconMargin;
                        deleteIconLeft = deleteIconRight - deleteIconWidth;
                    }

                    // Set the alpha value of the delete icon for fading effect
                    deleteIcon.setAlpha((int) (255 * alpha));

                    // Draw the delete icon on the canvas at the appropriate position
                    deleteIcon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
                    deleteIcon.draw(c);

                }
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // Handle the item movement, update the data set and notify the adapter
                // For example, swap the items in the list
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                Collections.swap(detailMealss, fromPosition, toPosition);
                favMealAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Handle the item swipe, remove the item from the data set and notify the adapter
                int position = viewHolder.getAdapterPosition();
                showDeleteConfirmationDialog(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(fragmentFavouriteBinding.favMealRecView);
        fragmentFavouriteBinding.include3.logoutButton.setVisibility(View.GONE);
        Glide.with(getContext())
                .load(GoogleSignIn.getLastSignedInAccount(getContext())==null?"imageURL":GoogleSignIn.getLastSignedInAccount(getContext()).getPhotoUrl())
                .fallback(R.drawable.logo2)
                .error(R.drawable.logo2)
                .into(fragmentFavouriteBinding.include3.drawerButton);



    }

    @Override
    public void showLifeMeals(LiveData<List<DetailMeal>> detailMealList) {

        detailMealList.observe(this, new Observer<List<DetailMeal>>() {

            @Override
            public void onChanged(List<DetailMeal> detailMealList) {
                if(detailMealList.isEmpty()&&!intro.STATUS.equals("GUEST")){
                    fragmentFavouriteBinding.emptyAnimation.setVisibility(View.VISIBLE);

                }else{
                    detailMealss = detailMealList;
                    favMealAdapter.setMeals(detailMealList);
                    favMealAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.custom_delete_dialog, null);
        builder.setView(dialogView);
        Button btnDelete = dialogView.findViewById(R.id.btnDelete);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        AlertDialog dialog = builder.create();
        dialog.show();

        btnDelete.setOnClickListener(v -> {
            deleteItem(position);
            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> {

            dialog.dismiss();
            favMealAdapter.notifyDataSetChanged();
        });
    }

    private void deleteItem(int position) {
        favPresenter.deleteFromFav(detailMealss.get(position));

        // Remove the item from your data source
        detailMealss.remove(position);

        // Notify the RecyclerView adapter of the item removal
        favMealAdapter.notifyItemRemoved(position);
    }


}