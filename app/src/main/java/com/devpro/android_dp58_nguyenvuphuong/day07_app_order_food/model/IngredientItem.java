package com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model;

import java.util.ArrayList;

public class IngredientItem {

    private String name;
    private String amount;
    private int image;
    private ArrayList<String> nutritionList;

    public IngredientItem(String name, String amount, int image, ArrayList<String> nutritionList) {
        this.name = name;
        this.amount = amount;
        this.image = image;
        this.nutritionList = nutritionList;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public int getImage() {
        return image;
    }

    public ArrayList<String> getNutritionList() {
        return nutritionList;
    }
}