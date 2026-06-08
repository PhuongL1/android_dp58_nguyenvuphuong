package com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto;

import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListResponse extends BaseResponse<List<User>> {
    @SerializedName("pagination")
    private Pagination pagination;

    // Getters and setters
}
