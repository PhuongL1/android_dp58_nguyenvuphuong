package com.devpro.android_dp58_nguyenvuphuong.day02_simple_profile_app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devpro.android_dp58_nguyenvuphuong.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    private static final String TAG = "InfoFragment";

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "InfoFragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "InfoFragment onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "InfoFragment onCreateView");
        return inflater.inflate(R.layout.fragment_info_day2, container, false);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "InfoFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "InfoFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "InfoFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "InfoFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "InfoFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "InfoFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "InfoFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "InfoFragment onDetach");
    }


}