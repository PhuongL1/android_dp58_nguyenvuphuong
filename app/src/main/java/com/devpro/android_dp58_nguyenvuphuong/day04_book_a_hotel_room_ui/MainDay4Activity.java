package com.devpro.android_dp58_nguyenvuphuong.day04_book_a_hotel_room_ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.devpro.android_dp58_nguyenvuphuong.R;

public class MainDay4Activity extends AppCompatActivity {
    private TextView tvDescriptionDetail;
    private boolean isExpanded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_d4);
        tvDescriptionDetail = findViewById(R.id.tv_description_detail);
        tvDescriptionDetail.setOnClickListener(v -> {
            if (!isExpanded) {
                tvDescriptionDetail.setText(R.string.full_description);
                isExpanded = true;
            } else {
                tvDescriptionDetail.setText(R.string.decription_detail);
                isExpanded = false;
            }

        });

    }
}