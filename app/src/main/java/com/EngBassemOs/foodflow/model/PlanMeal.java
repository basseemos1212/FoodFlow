package com.EngBassemOs.foodflow.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "planTable")
public class PlanMeal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "id_meal")
    @SerializedName("idMeal")
    @NonNull
    private String idMeal;
    @ColumnInfo(name = "str_meal")
    @SerializedName("strMeal")
    private String strMeal;
    @ColumnInfo(name = "str_DrinkAlternate")
    @SerializedName("strDrinkAlternate")
    private String strDrinkAlternate;
    @ColumnInfo(name = "str_category")
    @SerializedName("strCategory")
    private String strCategory;
    @ColumnInfo(name = "str_area")
    @SerializedName("strArea")
    private String strArea;
    @ColumnInfo(name = "str_instructions")
    @SerializedName("strInstructions")
    private String strInstructions;
    @ColumnInfo(name = "str_thumn")
    @SerializedName("strMealThumb")
    private String strMealThumb;
    @ColumnInfo(name = "str_tags")
    @SerializedName("strTags")
    private String strTags;
    @ColumnInfo(name = "str_yt")
    @SerializedName("strYoutube")
    private String strYoutube;
    @ColumnInfo(name = "str_ing1")
    @SerializedName("strIngredient1")
    private String ing1;
    @ColumnInfo(name = "str_ing2")
    @SerializedName("strIngredient2")
    private String ing2;
    @ColumnInfo(name = "str_ing3")
    @SerializedName("strIngredient3")
    private String ing3;
    @ColumnInfo(name = "str_ing4")
    @SerializedName("strIngredient4")
    private String ing4;
    @ColumnInfo(name = "str_ing5")
    @SerializedName("strIngredient5")
    private String ing5;
    @ColumnInfo(name = "str_ing6")
    @SerializedName("strIngredient6")
    private String ing6;
    @ColumnInfo(name = "str_ing7")
    @SerializedName("strIngredient7")
    private String ing7;
    @ColumnInfo(name = "str_ing8")
    @SerializedName("strIngredient8")
    private String ing8;
    @ColumnInfo(name = "str_ing9")
    @SerializedName("strIngredient9")
    private String ing9;
    @ColumnInfo(name = "str_ing10")
    @SerializedName("strIngredient10")
    private String ing10;
    @ColumnInfo(name = "str_ing11")
    @SerializedName("strIngredient11")
    private String ing11;
    @ColumnInfo(name = "str_ing12")
    @SerializedName("strIngredient12")
    private String ing12;
    @ColumnInfo(name = "str_ing13")
    @SerializedName("strIngredient13")
    private String ing13;
    @ColumnInfo(name = "str_ing14")
    @SerializedName("strIngredient14")
    private String ing14;
    @ColumnInfo(name = "str_ing15")
    @SerializedName("strIngredient15")
    private String ing15;
    @ColumnInfo(name = "str_ing16")
    @SerializedName("strIngredient16")
    private String ing16;
    @ColumnInfo(name = "str_ing17")
    @SerializedName("strIngredient17")
    private String ing17;
    @ColumnInfo(name = "str_ing18")
    @SerializedName("strIngredient18")
    private String ing18;
    @ColumnInfo(name = "str_ing19")
    @SerializedName("strIngredient19")
    private String ing19;
    @ColumnInfo(name = "str_ing20")
    @SerializedName("strIngredient20")
    private String ing20;
    @ColumnInfo(name = "str_Measure1")
    @SerializedName("strMeasure1")
    private String meas1;
    @ColumnInfo(name = "str_Measure2")
    @SerializedName("strMeasure2")
    private String meas2;
    @ColumnInfo(name = "str_Measure3")
    @SerializedName("strMeasure3")
    private String meas3;
    @ColumnInfo(name = "str_Measure4")
    @SerializedName("strMeasure4")
    private String meas4;
    @ColumnInfo(name = "str_Measure5")
    @SerializedName("strMeasure5")
    private String meas5;
    @ColumnInfo(name = "str_Measure6")
    @SerializedName("strMeasure6")
    private String meas6;
    @ColumnInfo(name = "str_Measure7")
    @SerializedName("strMeasure7")
    private String meas7;
    @ColumnInfo(name = "str_Measure8")
    @SerializedName("strMeasure8")
    private String meas8;
    @ColumnInfo(name = "str_Measure9")
    @SerializedName("strMeasure9")
    private String meas9;
    @ColumnInfo(name = "str_Measure10")
    @SerializedName("strMeasure10")
    private String meas10;
    @ColumnInfo(name = "str_Measure11")
    @SerializedName("strMeasure11")
    private String meas11;
    @ColumnInfo(name = "str_Measure12")
    @SerializedName("strMeasure12")
    private String meas12;
    @ColumnInfo(name = "str_Measure13")
    @SerializedName("strMeasure13")
    private String meas13;
    @ColumnInfo(name = "str_Measure14")
    @SerializedName("strMeasure14")
    private String meas14;
    @ColumnInfo(name = "str_Measure15")
    @SerializedName("strMeasure15")
    private String meas15;
    @ColumnInfo(name = "str_Measure16")
    @SerializedName("strMeasure16")
    private String meas16;
    @ColumnInfo(name = "str_Measure17")
    @SerializedName("strMeasure17")
    private String meas17;
    @ColumnInfo(name = "str_Measure18")
    @SerializedName("strMeasure18")
    private String meas18;
    @ColumnInfo(name = "str_Measure19")
    @SerializedName("strMeasure19")
    private String meas19;
    @ColumnInfo(name = "str_Measure20")
    @SerializedName("strMeasure20")
    private String meas20;
    @ColumnInfo(name = "day")
    private String day;

    public PlanMeal() {
    }
    public PlanMeal(DetailMeal detailMeal){
        this.idMeal=detailMeal.getIdMeal();
        this.strMeal=detailMeal.getStrMeal();
        this.strDrinkAlternate=detailMeal.getStrDrinkAlternate();
        this.strCategory=detailMeal.getStrCategory();
        this.strArea=detailMeal.getStrArea();
        this.strInstructions=detailMeal.getStrInstructions();
        this.strMealThumb=detailMeal.getStrMealThumb();
        this.strTags=detailMeal.getStrTags();
        this.strYoutube=detailMeal.getStrYoutube();
        this.ing1 = detailMeal.getIng1();
        this.ing2 = detailMeal.getIng2();
        this.ing3 = detailMeal.getIng3();
        this.ing4 = detailMeal.getIng4();
        this.ing5 = detailMeal.getIng5();
        this.ing6 = detailMeal.getIng6();
        this.ing7 = detailMeal.getIng7();
        this.ing8 = detailMeal.getIng8();
        this.ing9 = detailMeal.getIng9();
        this.ing10 = detailMeal.getIng10();
        this.ing11 = detailMeal.getIng11();
        this.ing12 = detailMeal.getIng12();
        this.ing13 = detailMeal.getIng13();
        this.ing14 = detailMeal.getIng14();
        this.ing15 = detailMeal.getIng15();
        this.ing16 = detailMeal.getIng16();
        this.ing17 = detailMeal.getIng17();
        this.ing18 = detailMeal.getIng18();
        this.ing19 = detailMeal.getIng19();
        this.ing20 = detailMeal.getIng20();
        this.meas1=detailMeal.getMeas1();
        this.meas1 = detailMeal.getMeas1();
        this.meas2 = detailMeal.getMeas2();
        this.meas3 = detailMeal.getMeas3();
        this.meas4 = detailMeal.getMeas4();
        this.meas5 = detailMeal.getMeas5();
        this.meas6 = detailMeal.getMeas6();
        this.meas7 = detailMeal.getMeas7();
        this.meas8 = detailMeal.getMeas8();
        this.meas9 = detailMeal.getMeas9();
        this.meas10 = detailMeal.getMeas10();
        this.meas11 = detailMeal.getMeas11();
        this.meas12 = detailMeal.getMeas12();
        this.meas13 = detailMeal.getMeas13();
        this.meas14 = detailMeal.getMeas14();
        this.meas15 = detailMeal.getMeas15();
        this.meas16 = detailMeal.getMeas16();
        this.meas17 = detailMeal.getMeas17();
        this.meas18 = detailMeal.getMeas18();
        this.meas19 = detailMeal.getMeas19();
        this.meas20 = detailMeal.getMeas20();

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(String strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrTags() {
        return strTags;
    }

    public void setStrTags(String strTags) {
        this.strTags = strTags;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getIng1() {
        return ing1;
    }

    public void setIng1(String ing1) {
        this.ing1 = ing1;
    }

    public String getIng2() {
        return ing2;
    }

    public void setIng2(String ing2) {
        this.ing2 = ing2;
    }

    public String getIng3() {
        return ing3;
    }

    public void setIng3(String ing3) {
        this.ing3 = ing3;
    }

    public String getIng4() {
        return ing4;
    }

    public void setIng4(String ing4) {
        this.ing4 = ing4;
    }

    public String getIng5() {
        return ing5;
    }

    public void setIng5(String ing5) {
        this.ing5 = ing5;
    }

    public String getIng6() {
        return ing6;
    }

    public void setIng6(String ing6) {
        this.ing6 = ing6;
    }

    public String getIng7() {
        return ing7;
    }

    public void setIng7(String ing7) {
        this.ing7 = ing7;
    }

    public String getIng8() {
        return ing8;
    }

    public void setIng8(String ing8) {
        this.ing8 = ing8;
    }

    public String getIng9() {
        return ing9;
    }

    public void setIng9(String ing9) {
        this.ing9 = ing9;
    }

    public String getIng10() {
        return ing10;
    }

    public void setIng10(String ing10) {
        this.ing10 = ing10;
    }

    public String getIng11() {
        return ing11;
    }

    public void setIng11(String ing11) {
        this.ing11 = ing11;
    }

    public String getIng12() {
        return ing12;
    }

    public void setIng12(String ing12) {
        this.ing12 = ing12;
    }

    public String getIng13() {
        return ing13;
    }

    public void setIng13(String ing13) {
        this.ing13 = ing13;
    }

    public String getIng14() {
        return ing14;
    }

    public void setIng14(String ing14) {
        this.ing14 = ing14;
    }

    public String getIng15() {
        return ing15;
    }

    public void setIng15(String ing15) {
        this.ing15 = ing15;
    }

    public String getIng16() {
        return ing16;
    }

    public void setIng16(String ing61) {
        this.ing16 = ing61;
    }

    public String getIng17() {
        return ing17;
    }

    public void setIng17(String ing17) {
        this.ing17 = ing17;
    }

    public String getIng18() {
        return ing18;
    }

    public void setIng18(String ing18) {
        this.ing18 = ing18;
    }

    public String getIng19() {
        return ing19;
    }

    public void setIng19(String ing19) {
        this.ing19 = ing19;
    }

    public String getIng20() {
        return ing20;
    }

    public void setIng20(String ing20) {
        this.ing20 = ing20;
    }

    public String getMeas1() {
        return meas1;
    }

    public void setMeas1(String meas1) {
        this.meas1 = meas1;
    }

    public String getMeas2() {
        return meas2;
    }

    public void setMeas2(String meas2) {
        this.meas2 = meas2;
    }

    public String getMeas3() {
        return meas3;
    }

    public void setMeas3(String meas3) {
        this.meas3 = meas3;
    }

    public String getMeas4() {
        return meas4;
    }

    public void setMeas4(String meas4) {
        this.meas4 = meas4;
    }

    public String getMeas5() {
        return meas5;
    }

    public void setMeas5(String meas5) {
        this.meas5 = meas5;
    }

    public String getMeas6() {
        return meas6;
    }

    public void setMeas6(String meas6) {
        this.meas6 = meas6;
    }

    public String getMeas7() {
        return meas7;
    }

    public void setMeas7(String meas7) {
        this.meas7 = meas7;
    }

    public String getMeas8() {
        return meas8;
    }

    public void setMeas8(String meas8) {
        this.meas8 = meas8;
    }

    public String getMeas9() {
        return meas9;
    }

    public void setMeas9(String meas9) {
        this.meas9 = meas9;
    }

    public String getMeas10() {
        return meas10;
    }

    public void setMeas10(String meas10) {
        this.meas10 = meas10;
    }

    public String getMeas11() {
        return meas11;
    }

    public void setMeas11(String meas11) {
        this.meas11 = meas11;
    }

    public String getMeas12() {
        return meas12;
    }

    public void setMeas12(String meas12) {
        this.meas12 = meas12;
    }

    public String getMeas13() {
        return meas13;
    }

    public void setMeas13(String meas13) {
        this.meas13 = meas13;
    }

    public String getMeas14() {
        return meas14;
    }

    public void setMeas14(String meas14) {
        this.meas14 = meas14;
    }

    public String getMeas15() {
        return meas15;
    }

    public void setMeas15(String meas15) {
        this.meas15 = meas15;
    }

    public String getMeas16() {
        return meas16;
    }

    public void setMeas16(String meas16) {
        this.meas16 = meas16;
    }

    public String getMeas17() {
        return meas17;
    }

    public void setMeas17(String meas17) {
        this.meas17 = meas17;
    }

    public String getMeas18() {
        return meas18;
    }

    public void setMeas18(String meas18) {
        this.meas18 = meas18;
    }

    public String getMeas19() {
        return meas19;
    }

    public void setMeas19(String meas19) {
        this.meas19 = meas19;
    }

    public String getMeas20() {
        return meas20;
    }

    public void setMeas20(String meas20) {
        this.meas20 = meas20;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public DetailMeal copyPlanToDetail() {
        DetailMeal detailMeal = new DetailMeal();
        detailMeal.setIdMeal(getIdMeal());
        detailMeal.setStrMeal(getStrMeal());
        detailMeal.setStrDrinkAlternate(getStrDrinkAlternate());
        detailMeal.setStrCategory(getStrCategory());
        detailMeal.setStrArea(getStrArea());
        detailMeal.setStrInstructions(getStrInstructions());
        detailMeal.setStrMealThumb(getStrMealThumb());
        detailMeal.setStrTags(getStrTags());
        detailMeal.setStrYoutube(getStrYoutube());
        detailMeal.setIng1(getIng1());
        detailMeal.setIng2(getIng2());
        detailMeal.setIng3(getIng3());
        detailMeal.setIng4(getIng4());
        detailMeal.setIng5(getIng5());
        detailMeal.setIng6(getIng6());
        detailMeal.setIng7(getIng7());
        detailMeal.setIng8(getIng8());
        detailMeal.setIng9(getIng9());
        detailMeal.setIng10(getIng10());
        detailMeal.setIng11(getIng11());
        detailMeal.setIng12(getIng12());
        detailMeal.setIng13(getIng13());
        detailMeal.setIng14(getIng14());
        detailMeal.setIng15(getIng15());
        detailMeal.setIng16(getIng16());
        detailMeal.setIng17(getIng17());
        detailMeal.setIng18(getIng18());
        detailMeal.setIng19(getIng19());
        detailMeal.setIng20(getIng20());
        detailMeal.setMeas1(getMeas1());
        detailMeal.setMeas2(getMeas2());
        detailMeal.setMeas3(getMeas3());
        detailMeal.setMeas4(getMeas4());
        detailMeal.setMeas5(getMeas5());
        detailMeal.setMeas6(getMeas6());
        detailMeal.setMeas7(getMeas7());
        detailMeal.setMeas8(getMeas8());
        detailMeal.setMeas9(getMeas9());
        detailMeal.setMeas10(getMeas10());
        detailMeal.setMeas11(getMeas11());
        detailMeal.setMeas12(getMeas12());
        detailMeal.setMeas13(getMeas13());
        detailMeal.setMeas14(getMeas14());
        detailMeal.setMeas15(getMeas15());
        detailMeal.setMeas16(getMeas16());
        detailMeal.setMeas17(getMeas17());
        detailMeal.setMeas18(getMeas18());
        detailMeal.setMeas19(getMeas19());
        detailMeal.setMeas20(getMeas20());

        return detailMeal;
    }

}
