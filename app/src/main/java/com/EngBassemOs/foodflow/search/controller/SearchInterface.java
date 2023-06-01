package com.EngBassemOs.foodflow.search.controller;

import com.EngBassemOs.foodflow.model.Area;
import com.EngBassemOs.foodflow.model.CategoryItem;
import com.EngBassemOs.foodflow.model.Ingredient;
import com.EngBassemOs.foodflow.model.Meal;

import java.util.List;

public interface SearchInterface {
    public void showIngData(List<Ingredient> ingredients);
    public void showCategories(List<CategoryItem> categoryItems);
    public void showAreas(List<Area> areas);

}
