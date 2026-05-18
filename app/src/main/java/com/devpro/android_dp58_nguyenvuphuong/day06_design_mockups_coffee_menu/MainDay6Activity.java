package com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.adapter.CoffeeAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.model.CoffeeItem;

import java.util.ArrayList;

public class MainDay6Activity extends AppCompatActivity {

    private RecyclerView rcPinterestReel;
    private ImageView ivBtnCart;
    private TextView tvCartBadge;

    private ArrayList<CoffeeItem> listCoffee;
    private CoffeeAdapter coffeeAdapter;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_day6);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cartManager = new CartManager(this);

        rcPinterestReel = findViewById(R.id.rc_pinterest_reel);
        ivBtnCart = findViewById(R.id.iv_btn_cart);
        tvCartBadge = findViewById(R.id.tv_cart_badge);

        initData();

        coffeeAdapter = new CoffeeAdapter(listCoffee, cartManager, () -> updateCartBadge());

        rcPinterestReel.setLayoutManager(new GridLayoutManager(this, 2));
        rcPinterestReel.setAdapter(coffeeAdapter);

        ivBtnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainDay6Activity.this, CartDay6Activity.class);
            startActivity(intent);
        });

        updateCartBadge();
    }

    private void initData() {
        listCoffee = new ArrayList<>();

        ArrayList<CoffeeItem> cartList = cartManager.getCartList();

        listCoffee.add(new CoffeeItem(
                Constants.KEY_ESPRESSO,
                "Espresso",
                R.drawable.espresso_d6,
                Constants.COFFEE_PRICE,
                getQuantityFromCart(cartList, Constants.KEY_ESPRESSO)
        ));

        listCoffee.add(new CoffeeItem(
                Constants.KEY_CARAMEL,
                "Caramel Frappucino",
                R.drawable.caramel_frappucino_d6,
                Constants.COFFEE_PRICE,
                getQuantityFromCart(cartList, Constants.KEY_CARAMEL)
        ));

        listCoffee.add(new CoffeeItem(
                Constants.KEY_ICE_COFFEE,
                "Ice Coffee",
                R.drawable.ice_coffee_d6,
                Constants.COFFEE_PRICE,
                getQuantityFromCart(cartList, Constants.KEY_ICE_COFFEE)
        ));

        listCoffee.add(new CoffeeItem(
                Constants.KEY_HOT_CHOCOLATE,
                "Hot Chocolate",
                R.drawable.hot_chocolate_d6,
                Constants.COFFEE_PRICE,
                getQuantityFromCart(cartList, Constants.KEY_HOT_CHOCOLATE)
        ));

        listCoffee.add(new CoffeeItem(
                Constants.KEY_MIXED_BLACK,
                "Mixed Black Coffee",
                R.drawable.mixed_black_coffee_d6,
                Constants.COFFEE_PRICE,
                getQuantityFromCart(cartList, Constants.KEY_MIXED_BLACK)
        ));
    }

    private int getQuantityFromCart(ArrayList<CoffeeItem> cartList, String key) {
        for (CoffeeItem item : cartList) {
            if (item.getKey().equals(key)) {
                return item.getQuantity();
            }
        }

        return 0;
    }

    private void updateCartBadge() {
        int totalQuantity = cartManager.getTotalQuantity();

        tvCartBadge.setText(String.valueOf(totalQuantity));

        if (totalQuantity > 0) {
            tvCartBadge.setVisibility(View.VISIBLE);
        } else {
            tvCartBadge.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        initData();

        coffeeAdapter = new CoffeeAdapter(listCoffee, cartManager, () -> updateCartBadge());
        rcPinterestReel.setAdapter(coffeeAdapter);

        updateCartBadge();
    }
}