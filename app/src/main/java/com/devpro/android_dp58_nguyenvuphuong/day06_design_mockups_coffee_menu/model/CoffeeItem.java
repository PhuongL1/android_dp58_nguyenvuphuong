package com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.model;

public class CoffeeItem {
    private String key;
    private String name;
    private int image;
    private int price;
    private int quantity;
    private boolean favorite;

    public CoffeeItem(String key, String name, int image, int price, int quantity) {
        this.key = key;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.favorite = false;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}