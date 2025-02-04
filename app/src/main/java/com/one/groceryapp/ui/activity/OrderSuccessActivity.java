package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.databinding.ActivityOrderSuccessBinding;
import com.one.groceryapp.model.TransactionModel;
import com.one.groceryapp.ui.fragment.HomeFragment;

import java.util.ArrayList;

public class OrderSuccessActivity extends AppCompatActivity {
    ActivityOrderSuccessBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        int price = getIntent().getIntExtra("price",0);

        SharedPreferences sf = getSharedPreferences("saveprice", MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("price", price);
        editor.apply();

    }
}