package com.EngBassemOs.foodflow.model;

import java.util.List;

public class Ingredient {
    private String idIngredient;
    private String strDescription;
    private String strIngredient;

    public Ingredient() {
    }


    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

}
