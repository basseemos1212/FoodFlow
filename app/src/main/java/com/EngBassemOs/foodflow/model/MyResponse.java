package com.EngBassemOs.foodflow.model;

import java.util.List;

public class MyResponse {
    private List<CategoryItem> categories;

    public MyResponse() {
    }

    public List<CategoryItem> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryItem> categories) {
        this.categories = categories;
    }
}
