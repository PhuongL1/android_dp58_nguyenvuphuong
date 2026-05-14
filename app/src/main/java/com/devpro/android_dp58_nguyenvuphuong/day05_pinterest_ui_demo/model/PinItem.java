package com.devpro.android_dp58_nguyenvuphuong.day05_pinterest_ui_demo.model;

public class PinItem {
    private int imageResource;
    private int imageHeight;
    public PinItem(int imageResource, int imageHeight) {
        this.imageResource = imageResource;
        this.imageHeight = imageHeight;
    }
    public int getImageResource() {
        return imageResource;
    }
    public int getImageHeight() {
        return imageHeight;
    }
}