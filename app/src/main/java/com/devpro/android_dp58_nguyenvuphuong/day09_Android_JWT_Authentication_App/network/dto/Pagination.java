package com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.dto;

import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName("current_page") // ten kieu map theo json
    private int currentPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_items")
    private int totalItems;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
