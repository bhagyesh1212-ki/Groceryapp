package com.one.groceryapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.idlestar.ratingstar.RatingStarView;
import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity {

    ActivityFilterBinding binding;
    boolean ischecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        RatingStarView ratingStarView = findViewById(R.id.rating);

        float current = ratingStarView.getRating();
        binding.ratingNumber.setText(String.valueOf(current));

        binding.discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischecked = !ischecked;
                if(ischecked){
                    binding.discountCheck.setImageResource(R.drawable.checkmarkgreen);
                }else {
                    binding.discountCheck.setImageResource(R.drawable.checkmark);
                }

            }
        });
        binding.freeShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischecked = !ischecked;
                if(ischecked){
                    binding.shippingCheck.setImageResource(R.drawable.checkmarkgreen);
                }else {
                    binding.shippingCheck.setImageResource(R.drawable.checkmark);
                }
            }
        });
        binding.delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischecked = !ischecked;
                if(ischecked){
                    binding.deliveryCheck.setImageResource(R.drawable.checkmarkgreen);
                }else {
                    binding.deliveryCheck.setImageResource(R.drawable.checkmark);
                }
            }
        });

        binding.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.min.setText("");
                binding.max.setText("");
                binding.rating.setRating(0f);
                binding.discountCheck.setImageResource(R.drawable.checkmark);
                binding.shippingCheck.setImageResource(R.drawable.checkmark);
                binding.deliveryCheck.setImageResource(R.drawable.checkmark);
            }
        });
    }
}