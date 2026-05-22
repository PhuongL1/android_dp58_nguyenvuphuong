package com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model.FoodItem;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    public interface OnFoodClickListener {
        void onFoodClick(FoodItem foodItem);
    }

    private ArrayList<FoodItem> foodList = new ArrayList<>();
    private OnFoodClickListener listener;

    public FoodAdapter(OnFoodClickListener listener) {
        this.listener = listener;
    }

    public void setData(ArrayList<FoodItem> foodList) {
        this.foodList = foodList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_day7, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem item = foodList.get(position);

        holder.imgFood.setImageResource(item.getImage());
        holder.tvFoodName.setText(item.getName());
        holder.tvFoodInfo.setText(item.getTime() + "  •  ⭐ " + item.getRating());

        if (item.isFavorite()) {
            holder.imgFavorite.setVisibility(View.VISIBLE);
        } else {
            holder.imgFavorite.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFoodClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList == null ? 0 : foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        ImageView imgFavorite;
        TextView tvFoodName;
        TextView tvFoodInfo;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.img_food);
            imgFavorite = itemView.findViewById(R.id.img_favorite);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodInfo = itemView.findViewById(R.id.tv_food_info);
        }
    }
}