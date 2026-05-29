package com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email") // Trường này sẽ đượcSerializedName thành "email" trong JSON request body khi gửi yêu cầu.
    private String email; // duoi nay co the la gi cung duoc nhung tren bat buoc phai giong voi server

    @SerializedName("password")
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters và Setters...
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
