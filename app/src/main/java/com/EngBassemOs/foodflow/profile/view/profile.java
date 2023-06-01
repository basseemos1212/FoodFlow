package com.EngBassemOs.foodflow.profile.view;

import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.auth.view.intro;
import com.EngBassemOs.foodflow.databinding.FragmentProfileBinding;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.model.LapModel;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.profile.presenter.ProfilePresenter;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class profile extends Fragment implements ProfileIntercae {
    FragmentProfileBinding fragmentProfileBinding;
    DayAdapter dayAdapter;
    PlanMealAdapter planMealAdapter;
    ProfilePresenter profilePresenter;
    List<PlanMeal> planMeals=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding=FragmentProfileBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(view.getContext());
        if(intro.STATUS.equals("GUEST")){
            fragmentProfileBinding.signupAnimation.setVisibility(View.VISIBLE);
           fragmentProfileBinding.cardView4.setVisibility(View.GONE);
            fragmentProfileBinding.textView3.setVisibility(View.GONE);
            fragmentProfileBinding.textView6.setVisibility(View.GONE);
            fragmentProfileBinding.circleImageView.setVisibility(View.GONE);
            fragmentProfileBinding.scroll.setVisibility(View.GONE);
            fragmentProfileBinding.signupAnimation.setOnClickListener(view1 -> {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_profile_to_intro);
               BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottomNavBar);
                bottomNavigationView.setVisibility(View.GONE);
            });



        }


        if (account != null) {
            String imageUrl = account.getPhotoUrl() != null ? account.getPhotoUrl().toString() : "";
            int fallbackImage = R.drawable.logo2;

            Glide.with(getContext())
                    .load(imageUrl)
                    .fallback(fallbackImage)
                    .error(fallbackImage)
                    .into(fragmentProfileBinding.circleImageView);
            fragmentProfileBinding.textView3.setText(account.getDisplayName());
            fragmentProfileBinding.textView6.setText(account.getEmail());
        } else if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            int fallbackImage = R.drawable.logo2;
            Glide.with(getContext())
                    .load("imageUrl")
                    .fallback(fallbackImage)
                    .error(fallbackImage)
                    .into(fragmentProfileBinding.circleImageView);
            fragmentProfileBinding.textView3.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);
            fragmentProfileBinding.textView6.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }else{
            int fallbackImage = R.drawable.logo2;
            Glide.with(getContext())
                    .load("imageUrl")
                    .fallback(fallbackImage)
                    .error(fallbackImage)
                    .into(fragmentProfileBinding.circleImageView);
            fragmentProfileBinding.textView3.setText("");
            fragmentProfileBinding.textView6.setText("");
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentProfileBinding.dayRecycle.setLayoutManager(linearLayoutManager);
        profilePresenter=new ProfilePresenter(Repository.getInstance(AppClient.getInstance(), ConcreteLocalSource.getInstance(getContext())),this);
        profilePresenter.getPlanMeal("Saturday");
        dayAdapter=new DayAdapter(profilePresenter);
        fragmentProfileBinding.dayRecycle.setAdapter(dayAdapter);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

        planMealAdapter=new PlanMealAdapter(new ArrayList<>());
        fragmentProfileBinding.planMealsRecycle.setLayoutManager(linearLayoutManager1);
        fragmentProfileBinding.planMealsRecycle.setAdapter(planMealAdapter);
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
                Collections.swap(planMeals, fromPosition, toPosition);
                planMealAdapter.notifyItemMoved(fromPosition, toPosition);
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
        itemTouchHelper.attachToRecyclerView(fragmentProfileBinding.planMealsRecycle);


    }

    @Override
    public void showLifeMeals(LiveData<List<PlanMeal>> detailMealList,String day) {
        detailMealList.observe(this, planMeals -> {
            this.planMeals=planMeals;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<PlanMeal> filteredPlanMeals = planMeals.stream()
                        .filter(planMeal -> planMeal.getDay().equals(day))
                        .collect(Collectors.toList());
                if(filteredPlanMeals.isEmpty()&&!intro.STATUS.equals("GUEST")){
                    System.out.println("iam here");
                    fragmentProfileBinding.emptyAnimation.setVisibility(View.VISIBLE);
                    planMealAdapter.setPlanMeals(filteredPlanMeals);
                    planMealAdapter.notifyDataSetChanged();
                }else{
                    planMealAdapter.setPlanMeals(filteredPlanMeals);
                    planMealAdapter.notifyDataSetChanged();
                    fragmentProfileBinding.emptyAnimation.setVisibility(View.GONE);
                }


            }
            // Do something with the filtered list of PlanMeal objects.
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
            planMealAdapter.notifyDataSetChanged();
        });
    }
    private void deleteItem(int position) {
        profilePresenter.deleteFromPlanMeals(planMeals.get(position));

        // Remove the item from your data source
        planMeals.remove(position);

        // Notify the RecyclerView adapter of the item removal
        planMealAdapter.notifyItemRemoved(position);
    }
}