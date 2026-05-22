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

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    public interface OnCoffeeChangeListener {
        void onCoffeeChanged();
    }
    private List<CoffeeItem> listCoffee;
    private CartManager cartManager;
    private OnCoffeeChangeListener listener;

    public CoffeeAdapter(List<CoffeeItem> listCoffee,
                         CartManager cartManager,
                         OnCoffeeChangeListener listener) {
        this.listCoffee = listCoffee;
        this.cartManager = cartManager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coffee_day6, parent, false);
        return new CoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        CoffeeItem item = listCoffee.get(position);

        holder.ivCoffee.setImageResource(item.getImage());
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText("$" + item.getPrice());
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        if (item.isFavorite()) {
            holder.ivHeart.setImageResource(R.drawable.red_heart);
        } else {
            holder.ivHeart.setImageResource(R.drawable.empty_heart);
        }

        holder.ivHeart.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;

            CoffeeItem coffeeItem = listCoffee.get(adapterPosition);

            boolean newFavorite = !coffeeItem.isFavorite();
            coffeeItem.setFavorite(newFavorite);

            if (newFavorite) {
                holder.ivHeart.setImageResource(R.drawable.red_heart);
            } else {
                holder.ivHeart.setImageResource(R.drawable.empty_heart);
            }
        });

        holder.ivPlus.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;

            CoffeeItem coffeeItem = listCoffee.get(adapterPosition);

            int quantity = coffeeItem.getQuantity();
            quantity++;
            coffeeItem.setQuantity(quantity);

            cartManager.addOrUpdateItem(coffeeItem);

            notifyItemChanged(adapterPosition);

            if (listener != null) {
                listener.onCoffeeChanged();
            }
        });

        holder.ivMinus.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition == RecyclerView.NO_POSITION) return;

            CoffeeItem coffeeItem = listCoffee.get(adapterPosition);

            int quantity = coffeeItem.getQuantity();

            if (quantity > 0) {
                quantity--;
                coffeeItem.setQuantity(quantity);

                if (quantity == 0) {
                    cartManager.removeItem(coffeeItem.getKey());
                } else {
                    cartManager.addOrUpdateItem(coffeeItem);
                }

                notifyItemChanged(adapterPosition);

                if (listener != null) {
                    listener.onCoffeeChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCoffee.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCoffee, ivPlus, ivMinus, ivHeart;
        TextView tvName, tvPrice, tvQuantity;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);

            ivCoffee = itemView.findViewById(R.id.iv_coffee);
            ivHeart = itemView.findViewById(R.id.iv_heart);
            tvName = itemView.findViewById(R.id.tv_coffee_name);
            tvPrice = itemView.findViewById(R.id.tv_coffee_price);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            ivPlus = itemView.findViewById(R.id.iv_plus);
            ivMinus = itemView.findViewById(R.id.iv_minus);
        }
    }
}