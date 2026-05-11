package com.devpro.android_dp58_nguyenvuphuong.day02_simple_profile_app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devpro.android_dp58_nguyenvuphuong.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActionFragment} factory method to
 * create an instance of this fragment.
 */
public class ActionFragment extends Fragment {

    private static final String TAG = "ActionFragment";

    public ActionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "ActionFragment onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ActionFragment onCreate");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "ActionFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_action_day2, container, false);

        Button btnCall = view.findViewById(R.id.btnCall);
        Button btnEmail = view.findViewById(R.id.btnEmail);

        btnCall.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Call clicked", Toast.LENGTH_SHORT).show());

        btnEmail.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Email clicked", Toast.LENGTH_SHORT).show());

        return view;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "ActionFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "ActionFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "ActionFragment onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "ActionFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "ActionFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "ActionFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ActionFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "ActionFragment onDetach");
    }
}