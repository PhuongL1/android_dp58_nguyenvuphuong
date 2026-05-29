package com.devpro.android_dp58_nguyenvuphuong.day09_networking.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient { //ban than la 1 interface
    private static final String BASE_URL = "http://192.168.0.103:3000/"; // Thay bằng IP của Server chạy API
    private static Retrofit retrofit = null;
    private static TokenRepository tokenRepository;
    private static Context appContext;

    public static void initialize(Context context) {
        appContext = context.getApplicationContext();
    }
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Tạo OkHttp client
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            // 1. Thêm auth interceptor trước
            tokenRepository = new TokenRepository(appContext);
            httpClient.addInterceptor(new AuthInterceptor(tokenRepository));

            // 2. Thêm logging interceptor sau cùng để log cả retry và tránh lỗi closed response
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApi() { //bat buoc phai co cai nay de tao ra service
        return getClient().create(ApiService.class);
    }
}