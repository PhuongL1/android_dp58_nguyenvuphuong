package com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.adapter.IngredientAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.model.FoodItem;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.viewmodel.FoodViewModel;

public class FragmentDetail extends Fragment {

    private ImageView imgDetailFood;
    private ImageView btnBackDetail;
    private ImageView btnFavoriteDetail;

    private TextView tvDetailName;
    private TextView tvDetailDescription;
    private TextView tvDetailTime;
    private TextView tvDetailDifficulty;
    private TextView tvDetailServing;
    private TextView tvIngredientsTitle;

    private RecyclerView rcvIngredients;
    private IngredientAdapter ingredientAdapter;

    private FoodViewModel foodViewModel;
    private FoodItem currentFood;

    public FragmentDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_day7, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgDetailFood = view.findViewById(R.id.img_detail_food);
        btnBackDetail = view.findViewById(R.id.btn_back_detail);
        btnFavoriteDetail = view.findViewById(R.id.btn_favorite_detail);

        tvDetailName = view.findViewById(R.id.tv_detail_name);
        tvDetailDescription = view.findViewById(R.id.tv_detail_description);
        tvDetailTime = view.findViewById(R.id.tv_detail_time);
        tvDetailDifficulty = view.findViewById(R.id.tv_detail_difficulty);
        tvDetailServing = view.findViewById(R.id.tv_detail_serving);
        tvIngredientsTitle = view.findViewById(R.id.tv_ingredients_title);

        rcvIngredients = view.findViewById(R.id.rcv_ingredients);

        foodViewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);

        ingredientAdapter = new IngredientAdapter();
        rcvIngredients.setLayoutManager(new LinearLayoutManager(requireContext()));
        rcvIngredients.setAdapter(ingredientAdapter);

        foodViewModel.getSelectedFoodLiveData().observe(getViewLifecycleOwner(), foodItem -> {
            currentFood = foodItem;
            showFoodDetail(foodItem);
        });

        btnFavoriteDetail.setOnClickListener(v -> {
            if (currentFood != null) {
                foodViewModel.toggleFavorite(currentFood.getId());
            }
        });

        btnBackDetail.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void showFoodDetail(FoodItem foodItem) {
        imgDetailFood.setImageResource(foodItem.getImage());

        tvDetailName.setText(foodItem.getName());
        tvDetailDescription.setText(foodItem.getDescription());
        tvDetailTime.setText(foodItem.getTime());
        tvDetailDifficulty.setText(foodItem.getDifficulty());
        tvDetailServing.setText(foodItem.getServing());

        if (foodItem.isFavorite()) {
            btnFavoriteDetail.setImageResource(R.drawable.red_heart_d7);
        } else {
            btnFavoriteDetail.setImageResource(R.drawable.empty_heart_d7);
        }

        if (foodItem.getIngredients() != null) {
            tvIngredientsTitle.setText("Ingredients (" + foodItem.getIngredients().size() + ")");
            ingredientAdapter.setData(foodItem.getIngredients());
        }
    }
}