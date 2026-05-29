package com.devpro.android_dp58_nguyenvuphuong.day09_networking.network;

public interface ApiCallback<T> {
    void onSuccess(T result); // Thanh cong thi goi ham nay
    void onError(String error); // Loi thi goi ham nay
}