package com.EngBassemOs.foodflow.home.view;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toolbar;


import com.EngBassemOs.foodflow.MealDetail.view.MealDetail;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.databinding.FragmentHomeBinding;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.home.presenter.HomePresenter;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Meal;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.NetworkUtils;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mig35.carousellayoutmanager.CenterScrollListener;

import java.util.List;

public class Home extends Fragment implements HomeInterface,onHomeClickListener{
    private FragmentHomeBinding fragmentHomeBinding;
    MealAdapter mealAdapter;
    List<Meal> meals;
    BottomNavigationView bottomNavigationView;
    NavController navController;
    HomePresenter homePresenter;
    private GoogleSignInClient googleSignInClient;
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView lottieAnimationView2;
    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding=FragmentHomeBinding.inflate(inflater,container,false);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        lottieAnimationView = fragmentHomeBinding.lottieAnimationView2;
        lottieAnimationView2=fragmentHomeBinding.loadingAnimation;

        scrollView = fragmentHomeBinding.scrollView2;



        // Inflate the layout for this fragment
        return fragmentHomeBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView=requireActivity().findViewById(R.id.bottomNavBar);
       bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (NetworkUtils.isNetworkConnected(requireContext())) {// Internet connection is available
            scrollView.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.GONE);
            lottieAnimationView2.setVisibility(View.VISIBLE);
        } else {
            // No internet connection
            scrollView.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.VISIBLE);
        }

        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        String status = preferences.getString("status","");
        homePresenter=new HomePresenter(Repository.getInstance(AppClient.getInstance(),ConcreteLocalSource.getInstance(getActivity().getApplicationContext())),this);
        if (status.isEmpty()||status.equals("logout")) {

            fragmentHomeBinding.personToolbar.personToolbar.setVisibility(View.GONE);
        } else {
            fragmentHomeBinding.personToolbar.personToolbar.setVisibility(View.VISIBLE);
        }

        CarouselLayoutManager carouselLayoutManager =new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
        carouselLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        carouselLayoutManager.setMaxVisibleItems(1);
        fragmentHomeBinding.mealRecycleView.setLayoutManager(carouselLayoutManager);
        mealAdapter=new MealAdapter(getContext(), meals,this);
        fragmentHomeBinding.personToolbar.logoutButton.setOnClickListener(v ->{
            if (NetworkUtils.isNetworkConnected(requireContext())) {
                // Internet connection is available
                scrollView.setVisibility(View.VISIBLE);
                lottieAnimationView.setVisibility(View.GONE);
                homePresenter.logout();
            } else {
                // No internet connection
                scrollView.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.VISIBLE);

            }
        });
        homePresenter.getSearchByAreaMeals("Egyptian","area");
        homePresenter.getRandomMeal();
        int fallbackImage = R.drawable.logo2;
        Glide.with(getContext())
                .load(GoogleSignIn.getLastSignedInAccount(getContext())==null?"imageURL":GoogleSignIn.getLastSignedInAccount(getContext()).getPhotoUrl())
                .fallback(fallbackImage)
                .error(fallbackImage)
                .into(fragmentHomeBinding.personToolbar.drawerButton);
//        homePresenter.getSearchByAreaMeals("British","area");



    }


    @Override
    public void showData(List<Meal> meals) {
        mealAdapter.setMeals(meals);
        fragmentHomeBinding.mealRecycleView.setAdapter(mealAdapter);
        fragmentHomeBinding.mealRecycleView.addOnScrollListener(new CenterScrollListener());
        lottieAnimationView2.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        mealAdapter.notifyDataSetChanged();


    }

    @Override
    public void showError(String error) {


    }




    @Override
    public void logout() {
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("status", "logout");
        editor.apply();
        navController= Navigation.findNavController(getView());
        navController.navigate(R.id.action_home_to_intro);
        bottomNavigationView.setVisibility(View.GONE);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        if (account != null) {
            googleSignInClient.signOut()
                    .addOnCompleteListener(getActivity(), task -> {

                    });
        } else {
            // User is not signed in with Google Sign-In
            // Handle the situation accordingly (e.g., show an error message)
        }

    }

    @Override
    public void confirmNavigate(DetailMeal detailMeal) {
        lottieAnimationView2.setVisibility(View.GONE);
        System.out.println("i arrived here ");
        Intent intent = new Intent(getActivity(), MealDetail.class);
        intent.putExtra("detailMeal", detailMeal);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        startActivity(intent);
    }

    @Override
    public void showRandomMeal(DetailMeal randomMeal) {
        fragmentHomeBinding.mealOfTheDay.mealName.setText(randomMeal.getStrMeal());
        Glide.with(this)
                .load(randomMeal.getStrMealThumb())
                .into(fragmentHomeBinding.mealOfTheDay.mealThumb);
        fragmentHomeBinding.mealOfTheDay.mealParent.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MealDetail.class);
            intent.putExtra("detailMeal", randomMeal);
            startActivity(intent);
        });
    }


    @Override
    public void navigate(String id) {
        lottieAnimationView2.setVisibility(View.VISIBLE);
        homePresenter.navigateToMealByID(id);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}