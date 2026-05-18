package com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.adapter.CartAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.model.CoffeeItem;

import java.util.ArrayList;

public class CartDay6Activity extends AppCompatActivity {

    private RecyclerView rcCartDay6;
    private ImageView ivBackCart;
    private TextView tvCartItemCount, tvSubTotal, tvTotal;
    private LinearLayout layoutEmptyCart;

    private ArrayList<CoffeeItem> listCart;
    private CartAdapter cartAdapter;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_day6);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartManager = new CartManager(this);

        rcCartDay6 = findViewById(R.id.rc_cart_day6);
        ivBackCart = findViewById(R.id.iv_back_cart);
        tvCartItemCount = findViewById(R.id.tv_cart_item_count);
        tvSubTotal = findViewById(R.id.tv_sub_total);
        tvTotal = findViewById(R.id.tv_total);
        layoutEmptyCart = findViewById(R.id.layout_empty_cart);

        ivBackCart.setOnClickListener(v -> finish());

        listCart = cartManager.getCartList();

        cartAdapter = new CartAdapter(listCart, cartManager, () -> updateCartUI());

        rcCartDay6.setLayoutManager(new LinearLayoutManager(this));
        rcCartDay6.setAdapter(cartAdapter);

        updateCartUI();
    }

    private void updateCartUI() {
        int totalQuantity = 0;
        int totalPrice = 0;

        for (CoffeeItem item : listCart) {
            totalQuantity += item.getQuantity();
            totalPrice += item.getQuantity() * item.getPrice();
        }

        tvCartItemCount.setText(totalQuantity + " Item(s)");
        tvSubTotal.setText(String.valueOf(totalPrice));
        tvTotal.setText("$ " + totalPrice);

        if (listCart.isEmpty()) {
            layoutEmptyCart.setVisibility(View.VISIBLE);
            rcCartDay6.setVisibility(View.GONE);
        } else {
            layoutEmptyCart.setVisibility(View.GONE);
            rcCartDay6.setVisibility(View.VISIBLE);
        }
    }
}