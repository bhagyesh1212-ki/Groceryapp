package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.databinding.ActivityOrderSuccessBinding;

public class OrderSuccessActivity extends AppCompatActivity {
    ActivityOrderSuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int price = getIntent().getIntExtra("price", 0);
        int shippingcharge = getIntent().getIntExtra("itemquantity", 0);

        binding.back.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        binding.track.setOnClickListener(v -> {
            Intent i = new Intent(this, MyOrderActivity.class);
            i.putExtra("price", price);
            i.putExtra("itemquantity", shippingcharge);
            startActivity(i);
            finish();
        });
    }
}

