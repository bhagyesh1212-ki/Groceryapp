package com.one.groceryapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
            int vpCurrentItem = binding.vp.getCurrentItem();
            if (vpCurrentItem == 0) {
                onBackPressed();
            } else if (vpCurrentItem == 1) {
                binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
                binding.text.setText("Next");
                binding.vp.setCurrentItem(0);
            } else if (vpCurrentItem == 2) {
                binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
                binding.text.setText("Next");
                binding.vp.setCurrentItem(1);
            }
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

        binding.next.setOnClickListener(v -> {
            nextButtonToSwipeScreen();
        });
    }

    @Override
    public void onBackPressed() {
        int count = binding.vp.getCurrentItem();
        if (count == 0) {
            super.onBackPressed();
        } else if (count == 1) {
            binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
            binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
            binding.text.setText("Next");
            binding.vp.setCurrentItem(0);
        } else if (count == 2) {
            binding.yourStateProgressBarId.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
            binding.yourStateProgressBarId.enableAnimationToCurrentState(true);
            binding.text.setText("Next");
            binding.vp.setCurrentItem(1);
        }
    }

    private void nextButtonToSwipeScreen() {
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
                MakeAPaymentButton();
            });
        }
    }

    private void MakeAPaymentButton() {
        SharedPreferences sf = getSharedPreferences("DeliveryType", Context.MODE_PRIVATE);
        int selectedPosition = sf.getInt("selectedPositionForDelivery", 0);
        price = getIntent().getIntExtra("price", 0);

        if (selectedPosition == 0) {
            price = price + 7;
        } else if (selectedPosition == 1) {
            price = price + 5;
        } else if (selectedPosition == 2) {
            price = price + 3;
        }

        int shippingcharge = getIntent().getIntExtra("itemquantity", 0);
        if (binding.text.getText() == "Make a  payment") {
                Intent i = new Intent(this, OrderSuccessActivity.class);
                i.putExtra("price", price);
                i.putExtra("itemquantity", shippingcharge);
                startActivity(i);
                finish();
        } else {
            nextButtonToSwipeScreen();
        }
    }
}