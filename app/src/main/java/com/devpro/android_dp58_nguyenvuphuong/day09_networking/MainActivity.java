package com.devpro.android_dp58_nguyenvuphuong.day09_networking;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.ApiCallback;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.ApiRepository;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.adapter.UserAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.onItemClickListener {

    RecyclerView recyclerView;
    List<User> userList = new ArrayList<>();
    UserAdapter adapter;
    ApiRepository apiRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        apiRepository = new ApiRepository();
        setupViews();
        getUsers();
    }


    private void getUsers() {
        apiRepository.getUsers(new ApiCallback<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                userList.clear();
                userList.addAll(users);
                adapter.notifyDataSetChanged();
                // Xử lý dữ liệu thành công
            }

            @Override
            public void onError(String error) {
                // Xử lý lỗi
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Kết thúc activity hiện tại và quay lại activity trước
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(userList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(User user) {
        Log.i("data", "id: "+user.getId());
        Log.i("data", "Name: "+user.getName());
        Log.i("data", "Email: "+user.getEmail());

//        Intent intent = new Intent(this, FullscreenActivity.class);
//        intent.putExtra("avatar", user.getAvatarUrl());
//        intent.putExtra("name", user.getName());
//        intent.putExtra("email", user.getEmail());
//        startActivity(intent);
    }
}