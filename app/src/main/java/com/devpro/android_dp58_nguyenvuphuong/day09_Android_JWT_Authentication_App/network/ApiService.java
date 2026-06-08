package com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network;

import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto.LoginRequest;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto.LoginResponse;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto.ProductListResponse;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto.RefreshRequest;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto.TokenResponse;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto.UserListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest); //Ten method login ; khi goi ko chay ngay
    @GET("users")
    Call<UserListResponse> getUsers();

    @POST("refresh")
    Call<TokenResponse> refreshToken(@Body RefreshRequest refreshToken);
    // them moi cho product
    @GET("products")
    Call<ProductListResponse> getProducts();
}