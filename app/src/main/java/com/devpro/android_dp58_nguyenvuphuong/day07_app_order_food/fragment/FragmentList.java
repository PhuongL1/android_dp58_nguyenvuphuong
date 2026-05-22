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

import com.devpro.android_dp58_nguyenvuphuong.R;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.adapter.FoodAdapter;
import com.devpro.android_dp58_nguyenvuphuong.day07_app_order_food.viewmodel.FoodViewModel;

public class FragmentList extends Fragment {

    private RecyclerView rcvFood;
    private FoodAdapter foodAdapter;
    private FoodViewModel foodViewModel;

    public FragmentList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_day7, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvFood = view.findViewById(R.id.rcv_food);

        foodViewModel = new ViewModelProvider(requireActivity()).get(FoodViewModel.class);

        foodAdapter = new FoodAdapter(foodItem -> {
            foodViewModel.selectFood(foodItem);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_day7, new FragmentDetail())
                    .addToBackStack(null)
                    .commit();
        });

        rcvFood.setLayoutManager(new LinearLayoutManager(requireContext()));
        rcvFood.setAdapter(foodAdapter);

        foodViewModel.getFoodListLiveData().observe(getViewLifecycleOwner(), foodItems -> {
            foodAdapter.setData(foodItems);
        });
    }
}