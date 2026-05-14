package com.devpro.android_dp58_nguyenvuphuong.day05_pinterest_ui_demo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day05_pinterest_ui_demo.adapter.PinAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day05_pinterest_ui_demo.model.PinItem;

import java.util.ArrayList;
import java.util.List;

public class MainDay5Activity extends AppCompatActivity {

    RecyclerView rcPinterestReel;
    List<PinItem> pinList;
    PinAdapter pinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_day5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rcPinterestReel = findViewById(R.id.rc_pinterest_reel);
        pinList = getPinList();
        pinAdapter = new PinAdapter(pinList);
        rcPinterestReel.setLayoutManager(
                new StaggeredGridLayoutManager(
                        2,
                        StaggeredGridLayoutManager.VERTICAL
                )
        );
        rcPinterestReel.setAdapter(pinAdapter);
    }
    private List<PinItem> getPinList() {
        List<PinItem> list = new ArrayList<>();
        list.add(new PinItem(R.drawable.pd1_d5, 260));
        list.add(new PinItem(R.drawable.pd2_d5, 280));
        list.add(new PinItem(R.drawable.pd3_d5, 300));
        list.add(new PinItem(R.drawable.pd4_d5, 220));
        list.add(new PinItem(R.drawable.pd5_d5, 340));
        list.add(new PinItem(R.drawable.pd6_d5, 200));
        list.add(new PinItem(R.drawable.pd7_d5, 280));
        list.add(new PinItem(R.drawable.pd8_d5, 240));
        list.add(new PinItem(R.drawable.pd9_d5, 320));
        list.add(new PinItem(R.drawable.pd10_d5, 190));
        list.add(new PinItem(R.drawable.pd11_d5, 280));
        return list;
    }
}