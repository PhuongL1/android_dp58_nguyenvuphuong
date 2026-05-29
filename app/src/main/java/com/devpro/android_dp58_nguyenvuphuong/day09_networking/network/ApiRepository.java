package com.devpro.android_dp58_nguyenvuphuong.day09_networking.network;

import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto.LoginRequest;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto.LoginResponse;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto.UserListResponse;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
    private final ApiService apiService;

    public ApiRepository() {
        this.apiService = ApiClient.getApi();
    }

    public void login(LoginRequest loginRequest, ApiCallback<LoginResponse> callback) {
        Call<LoginResponse> call = apiService.login(loginRequest);
        // enqueue thực hiện gọi mạng bất đồng bộ trên background thread
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Đăng nhập thất bại: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("Lỗi kết nối mạng: " + t.getMessage());
            }
        });
    }
    public void getUsers(final ApiCallback<List<User>> callback) {
        Call<UserListResponse> call = apiService.getUsers();
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().isSuccess()) {
                        callback.onSuccess(response.body().getData());
                    } else {
                        callback.onError(response.body().getMessage());
                    }
                } else {
                    callback.onError("Failed to get users");
                }
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}