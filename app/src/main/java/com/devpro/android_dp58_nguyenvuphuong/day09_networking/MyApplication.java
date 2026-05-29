package com.devpro.android_dp58_nguyenvuphuong.day09_networking;

import android.app.Application;

import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.ApiClient;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Khởi tạo ApiClient
        ApiClient.initialize(this);

        // Khởi tạo DataStore để lưu trữ dữ liệu
    }

}
