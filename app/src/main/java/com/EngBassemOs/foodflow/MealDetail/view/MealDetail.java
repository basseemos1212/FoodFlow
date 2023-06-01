package com.EngBassemOs.foodflow.MealDetail.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.EngBassemOs.foodflow.MealDetail.controller.MealDetailPresenter;
import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.auth.view.intro;
import com.EngBassemOs.foodflow.databinding.ActivityMealDetailBinding;
import com.EngBassemOs.foodflow.db.ConcreteLocalSource;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.PlanMeal;
import com.EngBassemOs.foodflow.model.Recipe;
import com.EngBassemOs.foodflow.model.Repository;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.NetworkUtils;
import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import java.util.ArrayList;
import java.util.List;
public class MealDetail extends AppCompatActivity implements MealDetailInterface ,BottomSheetDialogInterFace {
    private ActivityMealDetailBinding mainBinding;
    DetailMeal meal=new DetailMeal();
    AppClient client;
    List<Recipe> recipes;
    RecipeAdapter recipeAdapter;
    private String videoId = "";
    MealDetailPresenter mealDetailPresenter;
    Animation rotateOpen ;
    Animation rotateClose ;
    Animation fromBottom ;
    Animation toBottom ;
    boolean isOpened = false;
    BottomSheetDialog bottomSheetDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        meal = (DetailMeal) intent.getSerializableExtra("detailMeal");
        mainBinding=ActivityMealDetailBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        if (intro.STATUS.equals("GUEST")){
            mainBinding.mainFab.setVisibility(View.GONE);
        }
        if (NetworkUtils.isNetworkConnected(getApplicationContext())) {
                // Internet connection is available

            mainBinding.youtubePlayerView.setVisibility(View.VISIBLE);
            mainBinding.nothing3.setVisibility(View.VISIBLE);

        } else {
            // No internet connection
            mainBinding.youtubePlayerView.setVisibility(View.GONE);
            mainBinding.nothing3.setVisibility(View.GONE);
        }
        client= AppClient.getInstance();
        rotateOpen = AnimationUtils.loadAnimation(this , R.anim.rotate_open_btn);
        rotateClose = AnimationUtils.loadAnimation(this , R.anim.rotate_close_btn);
        fromBottom = AnimationUtils.loadAnimation(this , R.anim.from_bottom_btn);
        toBottom = AnimationUtils.loadAnimation(this , R.anim.to_bottom_btn);
        mealDetailPresenter=new MealDetailPresenter(this, Repository.getInstance(client, ConcreteLocalSource.getInstance(getApplicationContext())));
        getLifecycle().addObserver(mainBinding.youtubePlayerView);
        recipes=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainBinding.ingrecView.setLayoutManager(layoutManager);
        recipeAdapter=new RecipeAdapter(this,recipes);
        String mealID=getIntent().getStringExtra("mealID");
        mainBinding.ingrecView.setAdapter(recipeAdapter);
        showMeal();
        mainBinding.mainFab.setOnClickListener(view -> {
            setVisibility(isOpened);
            setAnimation(isOpened);
            isOpened = !isOpened;
        });
        mainBinding.addtoPlan.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.show(getSupportFragmentManager(), bottomSheetDialog.getTag());
        });
        mainBinding.addToFav.setOnClickListener(v -> {
            mealDetailPresenter.addToFav(meal);
        });

    }
    @Override
    public void onBackPressed() {
        // Finish the current activity
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }


    private void showMeal() {

        System.out.println(this.meal.getStrMealThumb());
        mainBinding.toolbar.setTitle(meal.getStrMeal());
        mainBinding.strAreaDetail.setText(meal.getStrArea());
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(mainBinding.detailMealThumb);
        mainBinding.strSteps.setText(meal.getStrInstructions());
        videoId=extractVideoId(meal.getStrYoutube());

        mainBinding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
        mealDetailPresenter.getFavMeals().observe(this, detailMealList -> {
            if (detailMealList.isEmpty()) {
                mainBinding.addToFav.setIconTint(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                mainBinding.addToFav.setOnClickListener(v -> {
                    mealDetailPresenter.addToFav(meal);
                });
            } else {
                for (DetailMeal detailMeal : detailMealList) {
                    if (detailMeal.getIdMeal().equals(this.meal.getIdMeal())) {
                        mainBinding.addToFav.setIconTint(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                        mainBinding.addToFav.setOnClickListener(v -> {
                            mealDetailPresenter.removeFromFav(meal);
                        });
                        break;
                    } else {
                        mainBinding.addToFav.setIconTint(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                        mainBinding.addToFav.setOnClickListener(v -> {
                            mealDetailPresenter.addToFav(meal);
                        });
                    }
                }
            }
        });

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        fillTheIngAdapter(meal);
    }

    private void fillTheIngAdapter(DetailMeal meal){


       try{
           if(!meal.getIng1().isEmpty() && !meal.getMeas1().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng1()+ "-Small.png",meal.getMeas1(),meal.getIng1()));
           }
           if(!meal.getIng2().isEmpty() && !meal.getMeas2().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng2()+ "-Small.png",meal.getMeas2(),meal.getIng2()));
           }
           if(!meal.getIng3().isEmpty() && !meal.getMeas3().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng3()+ "-Small.png",meal.getMeas3(),meal.getIng3()));
           }
           if(!meal.getIng4().isEmpty() && !meal.getMeas4().isEmpty()){

               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng4()+ "-Small.png",meal.getMeas4(),meal.getIng4()));
           }
           if(!meal.getIng5().isEmpty() && !meal.getMeas5().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng5()+ "-Small.png",meal.getMeas5(),meal.getIng5()));
           }
           if(!meal.getIng6().isEmpty() && !meal.getMeas6().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng6()+ "-Small.png",meal.getMeas6(),meal.getIng6()));
           }
           if(!meal.getIng7().isEmpty() && !meal.getMeas7().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng7()+ "-Small.png",meal.getMeas7(),meal.getIng7()));
           }
           if(!meal.getIng8().isEmpty() && !meal.getMeas8().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng8()+ "-Small.png",meal.getMeas8(),meal.getIng8()));
           }
           if(!meal.getIng9().isEmpty() && !meal.getMeas9().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng9()+ "-Small.png",meal.getMeas9(),meal.getIng9()));
           }
           if(!meal.getIng10().isEmpty() && !meal.getMeas10().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng10()+ "-Small.png",meal.getMeas10(),meal.getIng10()));
           }
           if(!meal.getIng11().isEmpty() && !meal.getMeas11().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng11()+ "-Small.png",meal.getMeas11(),meal.getIng11()));
           }
           if(!meal.getIng12().isEmpty() && !meal.getMeas12().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng12()+ "-Small.png",meal.getMeas12(),meal.getIng12()));
           }
           if(!meal.getIng13().isEmpty() && !meal.getMeas13().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng13()+ "-Small.png",meal.getMeas13(),meal.getIng13()));
           }
           if(!meal.getIng14().isEmpty() && !meal.getMeas14().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng14()+ "-Small.png",meal.getMeas14(),meal.getIng14()));
           }

           if(!meal.getIng15().isEmpty() && !meal.getMeas15().isEmpty()&&meal.getIng15()!=null&& meal.getMeas15()!=null ){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng15()+ "-Small.png",meal.getMeas15(),meal.getIng15()));
           }


           if(!meal.getIng16().isEmpty() && !meal.getMeas16().isEmpty()){
               recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng16()+ "-Small.png",meal.getMeas16(),meal.getIng16()));
           }
       }catch (Exception e){
           System.out.println(e.toString());
       }


        recipeAdapter.setRecipes(recipes);
        recipeAdapter.notifyDataSetChanged();

    }
    private String extractVideoId(String videoUrl) {
        String id = null;

        if (videoUrl != null && videoUrl.trim().length() > 0) {
            String[] videoUrlParts = videoUrl.split("v=");
            if (videoUrlParts.length == 2) {
                id = videoUrlParts[1];
            }
        }

        return id;
    }
    private void setAnimation(boolean opened) {
        if (!opened){
            mainBinding.mainFab.startAnimation(rotateOpen);
            mainBinding.mainFab.setIcon(getResources().getDrawable(R.drawable.ic_baseline_close_24));

            mainBinding.addToFav.startAnimation(fromBottom);
            mainBinding.addtoPlan.startAnimation(fromBottom);
        }else {
            mainBinding.mainFab.startAnimation(rotateClose);
            mainBinding.mainFab.setIcon(getResources().getDrawable(R.drawable.ic_baseline_add_24));

            mainBinding.addToFav.startAnimation(toBottom);
            mainBinding.addtoPlan.startAnimation(toBottom);
        }
    }

    private void setVisibility(boolean opened) {
        if (!opened){

            mainBinding.addToFav.setVisibility(View.VISIBLE);
            mainBinding.addToFav.setEnabled(true);
            mainBinding.addtoPlan.setVisibility(View.VISIBLE);
            mainBinding.addtoPlan.setEnabled(true);
        }else {

            mainBinding.addToFav.setVisibility(View.GONE);
            mainBinding.addToFav.setEnabled(false);
            mainBinding.addtoPlan.setVisibility(View.GONE);
            mainBinding.addtoPlan.setEnabled(false);
        }
    }

    @Override
    public void onClickButton(String day) {

        PlanMeal planMeal=new PlanMeal(meal);
        planMeal.setDay(day);
        try {
            mealDetailPresenter.addToPlan(planMeal);

        }catch (Exception e){
        }
        bottomSheetDialog.dismiss();
    }
}