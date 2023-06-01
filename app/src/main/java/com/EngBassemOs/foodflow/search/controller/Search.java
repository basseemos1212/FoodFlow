package com.EngBassemOs.foodflow.search.controller;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.databinding.FragmentSearchBinding;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.model.Area;
import com.EngBassemOs.foodflow.model.CategoryItem;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.MyResponse;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.ApiClient;
import com.EngBassemOs.foodflow.network.ApiClientInterface;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.Ingredients.IngredientsNetworkDelegate;
import com.EngBassemOs.foodflow.network.Ingredients.view.IngredientAdapter;
import com.EngBassemOs.foodflow.network.NetworkUtils;
import com.EngBassemOs.foodflow.network.area.AreaNetworkDelegate;
import com.EngBassemOs.foodflow.search.view.AreaAdapter;
import com.EngBassemOs.foodflow.search.view.CategoryAdapter;
import com.EngBassemOs.foodflow.search.view.OnAreaClickListner;
import com.EngBassemOs.foodflow.search.view.SearchByAreaAdapter;
import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Search extends Fragment implements  OnAreaClickListner ,SearchClickListner,SearchInterface{
    List<CategoryItem> categoryItems;
    CategoryAdapter categoryAdapter;

    AreaAdapter areaAdapter;

    IngredientAdapter ingredientAdapter;
    AppClient appClient;
    SearchPresenter searchPresenter;
    FragmentSearchBinding fragmentSearchBinding;
    private LottieAnimationView lottieAnimationView;
    private ScrollView scrollView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding=FragmentSearchBinding.inflate(inflater,container,false);
        lottieAnimationView = fragmentSearchBinding.lottieAnimationView;
        scrollView = fragmentSearchBinding.scrollView2;

        // Inflate the layout for this fragment
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchPresenter=new SearchPresenter(Repository.getInstance(AppClient.getInstance(), ConcreteLocalSource.getInstance(getContext())),this);

        Glide.with(getContext())
                .load(GoogleSignIn.getLastSignedInAccount(getContext())==null?"imageURL":GoogleSignIn.getLastSignedInAccount(getContext()).getPhotoUrl())
                .fallback(R.drawable.logo2)
                .error(R.drawable.logo2)
                .into(fragmentSearchBinding.include2.drawerButton);
        fragmentSearchBinding.include2.logoutButton.setVisibility(View.GONE);
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        String status = preferences.getString("status","");
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentSearchBinding.include2.personToolbar);
        if (NetworkUtils.isNetworkConnected(requireContext())) {
            // Internet connection is available
            scrollView.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.GONE);
            fragmentSearchBinding.loadingAnimation.setVisibility(View.VISIBLE);
        } else {
            // No internet connection
            scrollView.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.VISIBLE);
        }

        if (status.isEmpty()||status.equals("logout")) {

            fragmentSearchBinding.include2.personToolbar.setVisibility(View.GONE);
        } else {
            fragmentSearchBinding.include2.personToolbar.setVisibility(View.VISIBLE);
        }

        fragmentSearchBinding.searchRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        fragmentSearchBinding.searchRecycleView.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(categoryItems,this);
        searchPresenter.getCategories();
        fragmentSearchBinding.searchRecycleView.setAdapter(categoryAdapter);

        //MVC

        fragmentSearchBinding.areaRecycleView.setHasFixedSize(true);
        LinearLayoutManager areaLayoutManager=new LinearLayoutManager(getContext().getApplicationContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        areaAdapter = new AreaAdapter(getContext(), new ArrayList<Area>(),this);
        fragmentSearchBinding.areaRecycleView.setAdapter(areaAdapter);
        fragmentSearchBinding.areaRecycleView.setLayoutManager(areaLayoutManager);
        searchPresenter.getAreas();
        //______________________________________________________________________//


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        searchPresenter.getIngredients();
        ingredientAdapter=new IngredientAdapter(new ArrayList<>(),this);
        fragmentSearchBinding.ingRecycleView.setAdapter(ingredientAdapter);
        fragmentSearchBinding.ingRecycleView.setLayoutManager(layoutManager);






    }


    @Override
    public void onClick(Context context, String areaName,String type) {
        Intent intent = new Intent(getContext(), SearchByAreaActivity.class);
        intent.putExtra("myData", areaName);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public void onClickOnIngredient(Context context,String ingredient,String type) {
        Intent intent = new Intent(getContext(), SearchByAreaActivity.class);
        intent.putExtra("myData", ingredient);
        intent.putExtra("type", type);



        context.startActivity(intent);
    }



    @Override
    public void showIngData(List<Ingredient> ingredients) {
        fragmentSearchBinding.loadingAnimation.setVisibility(View.GONE);
        fragmentSearchBinding.scrollView2.setVisibility(View.VISIBLE);
        ingredientAdapter.setIngredientList(ingredients);
        ingredientAdapter.notifyDataSetChanged();


    }

    @Override
    public void showCategories(List<CategoryItem> cItems) {
        categoryAdapter.setCategoryItems(cItems);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAreas(List<Area> areas) {
        areaAdapter.setAreas(areas);
        areaAdapter.notifyDataSetChanged();
    }
}