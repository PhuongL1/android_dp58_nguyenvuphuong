package com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto;

import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {

    @SerializedName("products")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}