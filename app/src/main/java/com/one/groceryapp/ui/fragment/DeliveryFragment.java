package com.one.groceryapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.one.groceryapp.databinding.FragmentDeliveryBinding;


public class DeliveryFragment extends Fragment {

    FragmentDeliveryBinding binding;

    boolean isselected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDeliveryBinding binding = FragmentDeliveryBinding.inflate(inflater, container, false);

        binding.first.setBackgroundColor(Color.LTGRAY);
        binding.second.setBackgroundColor(Color.WHITE);
        binding.three.setBackgroundColor(Color.WHITE);

        isselected = false;
        binding.first.setOnClickListener(v -> {
            isselected = !isselected;
            if(isselected){
                binding.first.setBackgroundColor(Color.LTGRAY);
                binding.second.setBackgroundColor(Color.WHITE);
                binding.three.setBackgroundColor(Color.WHITE);
            }
            isselected = false;
        });

        binding.second.setOnClickListener(v -> {
            isselected = !isselected;
            if(isselected){
                binding.first.setBackgroundColor(Color.WHITE);
                binding.second.setBackgroundColor(Color.LTGRAY);
                binding.three.setBackgroundColor(Color.WHITE);
            }
            isselected = false;
        });

        binding.three.setOnClickListener(v -> {
            isselected = !isselected;
            if(isselected){
                binding.first.setBackgroundColor(Color.WHITE);
                binding.second.setBackgroundColor(Color.WHITE);
                binding.three.setBackgroundColor(Color.LTGRAY);
            }
            isselected = false;
        });

        return binding.getRoot();

    }
}