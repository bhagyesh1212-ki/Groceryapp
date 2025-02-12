package com.one.groceryapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    ActivityFilterBinding binding;
    boolean ischecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> onBackPressed());

        binding.ratingbar.setOnRatingBarChangeListener(this);

        binding.discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischecked = !ischecked;
                if (ischecked) {
                    binding.discountCheck.setImageResource(R.drawable.checkmarkgreen);
                } else {
                    binding.discountCheck.setImageResource(R.drawable.checkmark);
                }
            }
        });

        binding.freeShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischecked = !ischecked;
                if (ischecked) {
                    binding.shippingCheck.setImageResource(R.drawable.checkmarkgreen);
                } else {
                    binding.shippingCheck.setImageResource(R.drawable.checkmark);
                }
            }
        });

        binding.delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ischecked = !ischecked;
                if (ischecked) {
                    binding.deliveryCheck.setImageResource(R.drawable.checkmarkgreen);
                } else {
                    binding.deliveryCheck.setImageResource(R.drawable.checkmark);
                }
            }
        });

        binding.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.min.setText("");
                binding.max.setText("");
                binding.ratingbar.setRating(0f);
                binding.discountCheck.setImageResource(R.drawable.checkmark);
                binding.shippingCheck.setImageResource(R.drawable.checkmark);
                binding.deliveryCheck.setImageResource(R.drawable.checkmark);
            }
        });
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        binding.ratingNumber.setText(String.valueOf(rating));
    }
}