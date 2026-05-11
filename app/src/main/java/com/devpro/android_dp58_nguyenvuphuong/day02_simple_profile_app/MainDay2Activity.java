package com.devpro.android_dp58_nguyenvuphuong.day02_simple_profile_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.devpro.android_dp58_nguyenvuphuong.R;

public class MainDay2Activity extends AppCompatActivity {

    Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_day2);
        btnProfile = findViewById(R.id.btnProfile);

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainDay2Activity.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}