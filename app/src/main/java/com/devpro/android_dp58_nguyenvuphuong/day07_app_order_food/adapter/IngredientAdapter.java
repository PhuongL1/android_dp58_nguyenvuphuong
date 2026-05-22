package com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model.IngredientItem;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private ArrayList<IngredientItem> ingredientList = new ArrayList<>();

    public void setData(ArrayList<IngredientItem> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient_day7, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        IngredientItem item = ingredientList.get(position);

        holder.imgIngredient.setImageResource(item.getImage());
        holder.tvIngredientName.setText(item.getName());
        holder.tvIngredientAmount.setText(item.getAmount());

        ArrayList<String> nutritionList = item.getNutritionList();

        holder.tvNutrition1.setVisibility(View.GONE);
        holder.tvNutrition2.setVisibility(View.GONE);
        holder.tvNutrition3.setVisibility(View.GONE);

        if (nutritionList.size() > 0) {
            holder.tvNutrition1.setText(nutritionList.get(0));
            holder.tvNutrition1.setVisibility(View.VISIBLE);
        }

        if (nutritionList.size() > 1) {
            holder.tvNutrition2.setText(nutritionList.get(1));
            holder.tvNutrition2.setVisibility(View.VISIBLE);
        }

        if (nutritionList.size() > 2) {
            holder.tvNutrition3.setText(nutritionList.get(2));
            holder.tvNutrition3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return ingredientList == null ? 0 : ingredientList.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIngredient;
        TextView tvIngredientName;
        TextView tvIngredientAmount;
        TextView tvNutrition1;
        TextView tvNutrition2;
        TextView tvNutrition3;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIngredient = itemView.findViewById(R.id.img_ingredient);
            tvIngredientName = itemView.findViewById(R.id.tv_ingredient_name);
            tvIngredientAmount = itemView.findViewById(R.id.tv_ingredient_amount);
            tvNutrition1 = itemView.findViewById(R.id.tv_nutrition_1);
            tvNutrition2 = itemView.findViewById(R.id.tv_nutrition_2);
            tvNutrition3 = itemView.findViewById(R.id.tv_nutrition_3);
        }
    }
}