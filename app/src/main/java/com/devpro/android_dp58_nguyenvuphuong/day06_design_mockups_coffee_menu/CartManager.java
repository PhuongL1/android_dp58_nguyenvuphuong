package com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu;

import android.content.Context;
import android.content.SharedPreferences;

import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.model.CoffeeItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartManager {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public ArrayList<CoffeeItem> getCartList() {
        String json = sharedPreferences.getString(Constants.KEY_CART_LIST, "");

        if (json.isEmpty()) {
            return new ArrayList<>();
        }

        Type type = new TypeToken<ArrayList<CoffeeItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveCartList(ArrayList<CoffeeItem> cartList) {
        String json = gson.toJson(cartList);

        sharedPreferences.edit()
                .putString(Constants.KEY_CART_LIST, json)
                .apply();
    }

    public void addOrUpdateItem(CoffeeItem coffeeItem) {
        ArrayList<CoffeeItem> cartList = getCartList();

        boolean isExist = false;

        for (CoffeeItem item : cartList) {
            if (item.getKey().equals(coffeeItem.getKey())) {
                item.setQuantity(coffeeItem.getQuantity());
                isExist = true;
                break;
            }
        }

        if (!isExist && coffeeItem.getQuantity() > 0) {
            cartList.add(coffeeItem);
        }

        saveCartList(cartList);
    }

    public void removeItem(String key) {
        ArrayList<CoffeeItem> cartList = getCartList();

        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getKey().equals(key)) {
                cartList.remove(i);
                break;
            }
        }

        saveCartList(cartList);
    }

    public int getTotalQuantity() {
        ArrayList<CoffeeItem> cartList = getCartList();
        int totalQuantity = 0;

        for (CoffeeItem item : cartList) {
            totalQuantity += item.getQuantity();
        }

        return totalQuantity;
    }

    public int getTotalPrice() {
        ArrayList<CoffeeItem> cartList = getCartList();
        int totalPrice = 0;

        for (CoffeeItem item : cartList) {
            totalPrice += item.getQuantity() * item.getPrice();
        }

        return totalPrice;
    }

    public void clearCart() {
        sharedPreferences.edit()
                .remove(Constants.KEY_CART_LIST)
                .apply();
    }
}