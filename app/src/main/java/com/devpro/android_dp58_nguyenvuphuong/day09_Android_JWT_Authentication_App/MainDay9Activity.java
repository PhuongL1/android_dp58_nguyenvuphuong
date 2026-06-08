package com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.ApiCallback;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.ApiRepository;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.adapter.ProductAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day09_Android_JWT_Authentication_App.network.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainDay9Activity extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    RecyclerView recyclerView;
    List<Product> productList = new ArrayList<>();
    ProductAdapter adapter;
    ApiRepository apiRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_day9);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        apiRepository = new ApiRepository();

        setupViews();
        getProducts();
    }

    private void setupViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(productList);
        adapter.setOnProductClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    private void getProducts() {
        apiRepository.getProducts(new ApiCallback<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                productList.clear();
                productList.addAll(products);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainDay9Activity.this, "Load sản phẩm thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                if (error == null || error.isEmpty()) {
                    error = "Có lỗi xảy ra khi tải sản phẩm";
                }
                Toast.makeText(MainDay9Activity.this, error, Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "getProducts error: " + error);
            }
        });
    }

    @Override
    public void onProductClick(Product product) {
        Toast.makeText(this, product.getName(), Toast.LENGTH_SHORT).show();

        Log.i("product", "id: " + product.getId());
        Log.i("product", "name: " + product.getName());
        Log.i("product", "description: " + product.getDescription());
        Log.i("product", "price: " + product.getPrice());
        Log.i("product", "image: " + product.getProductImage());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}