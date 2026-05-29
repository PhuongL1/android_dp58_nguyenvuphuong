package com.devpro.android_dp58_nguyenvuphuong.day09_networking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.ApiCallback;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.SharedPrefManager;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto.LoginRequest;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.dto.LoginResponse;
import com.devpro.android_dp58_nguyenvuphuong.day09_networking.network.ApiRepository;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    ApiRepository apiRepository;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        if (sharedPrefManager.isLoggedIn()) {
            // Chuyển hướng tới Activity chính (Ví dụ ProductActivity)
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Hủy LoginActivity tránh bấm nút back quay lại được
            return;
        }
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        apiRepository = new ApiRepository();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v ->{
            handleLogin();

        });
    }

    private void handleLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest(email, password);
        apiRepository.login(request, new ApiCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                // Phase 2: Ở đây chúng ta sẽ lưu token nhận được từ response
                // lưu token nhận được từ response
                // Lưu thông tin
                sharedPrefManager.saveUserData(
                        response.getToken(),
                        response.getRefreshToken(),
                        response.getUserId(),
                        response.getUsername(),
                        response.getEmail()
                );
                // Chuyển màn hình
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}