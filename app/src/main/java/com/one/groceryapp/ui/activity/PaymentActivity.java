package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.one.groceryapp.databinding.ActivityPaymentBinding;
import com.one.groceryapp.ui.adapter.ViewPagerStateProgress;

public class PaymentActivity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    String[] descriptionData = {"DELIVERY", "ADDRESS", "PAYMENT"};

    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.yourStateProgressBarId.setStateDescriptionData(descriptionData);
        binding.yourStateProgressBarId.setStateSize(30f);
        binding.yourStateProgressBarId.setStateNumberTextSize(15f);
        binding.yourStateProgressBarId.setStateLineThickness(2f);
        binding.yourStateProgressBarId.setDescriptionTopSpaceIncrementer(10f);
        binding.yourStateProgressBarId.setStateDescriptionSize(10f);

        ViewPagerStateProgress ViewPagerStateProgress = new ViewPagerStateProgress(this);
        binding.vp.setAdapter(ViewPagerStateProgress);
        binding.vp.setUserInputEnabled(false);

         price = getIntent().getIntExtra("price", 0);

        binding.next.setOnClickListener(v -> {
            if (binding.yourStateProgressBarId.getCurrentStateNumber() == 0) {
                binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
                binding.vp.setCurrentItem(0);
            } else if (binding.yourStateProgressBarId.getCurrentStateNumber() == 1) {
                binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
                binding.vp.setCurrentItem(1);
            } else if (binding.yourStateProgressBarId.getCurrentStateNumber() == 2) {
                binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
                binding.vp.setCurrentItem(2);
                binding.text.setText("Make a  payment");
                binding.next.setOnClickListener(v1 -> {
                    Intent i = new Intent(this, OrderSuccessActivity.class);
                    i.putExtra("price", price);
                    startActivity(i);
                    finish();
                });
            }
        });
    }
}