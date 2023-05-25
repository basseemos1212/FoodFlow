package com.EngBassemOs.foodflow.MealDetail.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.EngBassemOs.foodflow.R;
import com.EngBassemOs.foodflow.model.DetailMeal;
import com.EngBassemOs.foodflow.model.Recipe;
import com.EngBassemOs.foodflow.network.AppClient;
import com.EngBassemOs.foodflow.network.detailMeail.DetailMealNetworkDelegate;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetail extends AppCompatActivity implements MealDetailInterface, DetailMealNetworkDelegate {
    DetailMeal meal;
    AppClient client;
    CollapsingToolbarLayout toolbar;
    ImageView imageView;
    TextView strArea;
    TextView steps;
    List<Recipe> recipes;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    private YouTubePlayerView youTubePlayerView;
    private String videoId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        toolbar=findViewById(R.id.collapsingToolbar);
        imageView=findViewById(R.id.detailMealThumb);
        strArea=findViewById(R.id.strAreaDetail);
        steps=findViewById(R.id.strSteps);
        String mealID=getIntent().getStringExtra("mealID");
        client= AppClient.getInstance();
        client.detailMealEnqeue(this,mealID);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        recipes=new ArrayList<>();
        recyclerView=findViewById(R.id.ingrecView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recipeAdapter=new RecipeAdapter(this,recipes);
        recyclerView.setAdapter(recipeAdapter);





//        System.out.println(meal.getStrMeal());
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

    @Override
    public void showMeal(DetailMeal meal) {
        this.meal=meal;

       
        System.out.println(this.meal.getStrMealThumb());
        toolbar.setTitle(meal.getStrMeal());
        strArea.setText(meal.getStrArea());
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .centerCrop()
                .into(imageView);
        steps.setText(meal.getStrInstructions());
        videoId=extractVideoId(meal.getStrYoutube());
        fillTheIngAdapter(meal);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(videoId, 0);
            }
        });
    }

    @Override
    public void onSuccessIngResult(DetailMeal detailMeal) {
        showMeal(detailMeal);

    }

    @Override
    public void onFailureIngResult(String error) {
        System.out.println(error);

    }
    private void fillTheIngAdapter(DetailMeal meal){

        if(meal.getIng1().isEmpty()==false&&meal.getMeas1().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng1()+ "-Small.png",meal.getMeas1(),meal.getIng1()));
        }
        if(meal.getIng2().isEmpty()==false&&meal.getMeas2().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng2()+ "-Small.png",meal.getMeas2(),meal.getIng2()));
        }
        if(meal.getIng3().isEmpty()==false&&meal.getMeas3().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng3()+ "-Small.png",meal.getMeas3(),meal.getIng3()));
        }
        if(meal.getIng4().isEmpty()==false&&meal.getMeas4().isEmpty()==false){

            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng4()+ "-Small.png",meal.getMeas4(),meal.getIng4()));
        }
        if(meal.getIng5().isEmpty()==false&&meal.getMeas5().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng5()+ "-Small.png",meal.getMeas5(),meal.getIng5()));
        }
        if(meal.getIng6().isEmpty()==false&&meal.getMeas6().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng6()+ "-Small.png",meal.getMeas6(),meal.getIng6()));
        }
        if(meal.getIng7().isEmpty()==false&&meal.getMeas7().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng7()+ "-Small.png",meal.getMeas7(),meal.getIng7()));
        }
        if(meal.getIng8().isEmpty()==false&&meal.getMeas8().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng8()+ "-Small.png",meal.getMeas8(),meal.getIng8()));
        }
        if(meal.getIng9().isEmpty()==false&&meal.getMeas9().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng9()+ "-Small.png",meal.getMeas9(),meal.getIng9()));
        }
        if(meal.getIng10().isEmpty()==false&&meal.getMeas10().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng10()+ "-Small.png",meal.getMeas10(),meal.getIng10()));
        }
        if(meal.getIng11().isEmpty()==false&&meal.getMeas11().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng11()+ "-Small.png",meal.getMeas11(),meal.getIng11()));
        }
        if(meal.getIng12().isEmpty()==false&&meal.getMeas12().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng12()+ "-Small.png",meal.getMeas12(),meal.getIng12()));
        }
        if(meal.getIng13().isEmpty()==false&&meal.getMeas13().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng13()+ "-Small.png",meal.getMeas13(),meal.getIng13()));
        }
        if(meal.getIng14().isEmpty()==false&&meal.getMeas14().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng14()+ "-Small.png",meal.getMeas14(),meal.getIng14()));
        }

        if(meal.getIng15().isEmpty()==false&&meal.getMeas15().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng15()+ "-Small.png",meal.getMeas15(),meal.getIng15()));
        }

        if(meal.getIng16().isEmpty()==false&&meal.getMeas16().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng16()+ "-Small.png",meal.getMeas16(),meal.getIng16()));
        }
        if(meal.getIng17().isEmpty()==false&&meal.getMeas17().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng17()+ "-Small.png",meal.getMeas17(),meal.getIng17()));
        }
        if(meal.getIng18().isEmpty()==false&&meal.getMeas18().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng18()+ "-Small.png",meal.getMeas18(),meal.getIng18()));
        }
        if(meal.getIng19().isEmpty()==false&&meal.getMeas19().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng19()+ "-Small.png",meal.getMeas19(),meal.getIng19()));
        }    if(meal.getIng20().isEmpty()==false&&meal.getMeas20().isEmpty()==false){
            recipes.add(new Recipe("https://www.themealdb.com/images/ingredients/" + meal.getIng20()+ "-Small.png",meal.getMeas20(),meal.getIng20()));
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
}