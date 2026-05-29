package com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto;

import com.google.gson.annotations.SerializedName;

public class RefreshRequest {
    @SerializedName("refresh_token")
    private String refreshToken;

    public RefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}