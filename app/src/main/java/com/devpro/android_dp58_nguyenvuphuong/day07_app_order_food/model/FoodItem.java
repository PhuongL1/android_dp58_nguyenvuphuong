package com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model;

import java.util.ArrayList;

public class FoodItem {

    private int id;
    private String name;
    private String description;
    private String time;
    private String difficulty;
    private String serving;
    private float rating;
    private int image;
    private boolean favorite;
    private ArrayList<IngredientItem> ingredients;
    public FoodItem(int id, String name, String description, String time,
                    String difficulty, String serving, float rating,
                    int image, boolean favorite,
                    ArrayList<IngredientItem> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.difficulty = difficulty;
        this.serving = serving;
        this.rating = rating;
        this.image = image;
        this.favorite = favorite;
        this.ingredients = ingredients;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getTime() {
        return time;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public String getServing() {
        return serving;
    }
    public float getRating() {
        return rating;
    }
    public int getImage() {
        return image;
    }
    public boolean isFavorite() {
        return favorite;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public ArrayList<IngredientItem> getIngredients() {
        return ingredients;
    }
}