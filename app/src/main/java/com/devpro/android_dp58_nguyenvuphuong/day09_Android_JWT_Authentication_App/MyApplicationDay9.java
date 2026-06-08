package com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App;

import android.app.Application;

import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.ApiClient;

public class MyApplicationDay9 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Khởi tạo ApiClient
        ApiClient.initialize(this);

        // Khởi tạo DataStore để lưu trữ dữ liệu
    }

}
