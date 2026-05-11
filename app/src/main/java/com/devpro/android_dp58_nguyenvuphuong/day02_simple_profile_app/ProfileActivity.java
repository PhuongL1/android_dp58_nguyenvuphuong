package com.devpro.android_dp58_nguyenvuphuong.day02_simple_profile_app;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.devpro.android_dp58_nguyenvuphuong.R;

public class ProfileActivity extends AppCompatActivity {

    Button btnBack, btnInfoFragment, btnActionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_day2);

        btnBack = findViewById(R.id.btnBack);
        btnInfoFragment = findViewById(R.id.btnInfoFragment);
        btnActionFragment = findViewById(R.id.btnActionFragment);

        btnBack.setOnClickListener(v -> finish());

        btnInfoFragment.setOnClickListener(v -> replaceFragment(new InfoFragment()));

        btnActionFragment.setOnClickListener(v -> replaceFragment(new ActionFragment()));

        if (savedInstanceState == null) {
            replaceFragment(new InfoFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}