package com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.CartManager;
import com.devpro.android_dp58_nguyenvuphuong.day06_design_mockups_coffee_menu.model.CoffeeItem;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    private ArrayList<CoffeeItem> listCart;
    private CartManager cartManager;
    private OnCartChangeListener listener;

    public CartAdapter(ArrayList<CoffeeItem> listCart,
                       CartManager cartManager,
                       OnCartChangeListener listener) {
        this.listCart = listCart;
        this.cartManager = cartManager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart_coffee_day6, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CoffeeItem item = listCart.get(position);

        holder.ivCoffee.setImageResource(item.getImage());
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("$" + item.getPrice());
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        holder.ivPlus.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;

            CoffeeItem coffeeItem = listCart.get(adapterPosition);

            int quantity = coffeeItem.getQuantity();
            quantity++;
            coffeeItem.setQuantity(quantity);

            cartManager.addOrUpdateItem(coffeeItem);

            notifyItemChanged(adapterPosition);

            if (listener != null) {
                listener.onCartChanged();
            }
        });

        holder.ivMinus.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;

            CoffeeItem coffeeItem = listCart.get(adapterPosition);

            int quantity = coffeeItem.getQuantity();

            if (quantity > 0) {
                quantity--;
                coffeeItem.setQuantity(quantity);

                if (quantity == 0) {
                    cartManager.removeItem(coffeeItem.getKey());
                    listCart.remove(adapterPosition);

                    notifyItemRemoved(adapterPosition);
                    notifyItemRangeChanged(adapterPosition, listCart.size());
                } else {
                    cartManager.addOrUpdateItem(coffeeItem);
                    notifyItemChanged(adapterPosition);
                }

                if (listener != null) {
                    listener.onCartChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCoffee, ivPlus, ivMinus;
        TextView tvName, tvPrice, tvQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCoffee = itemView.findViewById(R.id.iv_cart_coffee);
            tvName = itemView.findViewById(R.id.tv_cart_coffee_name);
            tvPrice = itemView.findViewById(R.id.tv_cart_price);
            tvQuantity = itemView.findViewById(R.id.tv_quantity_cart);
            ivPlus = itemView.findViewById(R.id.tv_plus_cart);
            ivMinus = itemView.findViewById(R.id.tv_minus_cart);
        }
    }
}