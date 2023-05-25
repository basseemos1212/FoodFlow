package com.EngBassemOs.foodflow.model;

import java.io.Serializable;
import java.util.ArrayList;

public class LapModel implements Serializable {
    private String email;
    private String gender;
    private ArrayList<String> langList;

    public LapModel() {
    }

    public LapModel(String email, String gender, ArrayList<String> langList) {
        this.email = email;
        this.gender = gender;
        this.langList = langList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<String> getLangList() {
        return langList;
    }

    public void setLangList(ArrayList<String> langList) {
        this.langList = langList;
    }
}
