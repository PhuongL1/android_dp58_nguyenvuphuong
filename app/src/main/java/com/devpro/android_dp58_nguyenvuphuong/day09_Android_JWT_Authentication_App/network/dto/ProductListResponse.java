package com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto;

import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.model.Product;
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