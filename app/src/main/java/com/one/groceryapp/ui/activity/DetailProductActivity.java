package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityDetailProductBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.utils.Constants;

import java.util.List;

public class DetailProductActivity extends AppCompatActivity {

    ActivityDetailProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        List<FeatureProductModel> featureProductModelList = (List<FeatureProductModel>) getIntent().getSerializableExtra("featuremodel");
        int position = getIntent().getIntExtra("position", 0);

        binding.price.setText(String.valueOf(featureProductModelList.get(position).getPrice()));
        binding.name.setText(featureProductModelList.get(position).getProductName());
        binding.quantity.setText(featureProductModelList.get(position).getQuantity());
        binding.quantityOfItem.setText(String.valueOf(featureProductModelList.get(position).getProductNumber()));
        binding.image.setImageResource(featureProductModelList.get(position).getImageProduct());

        if (Constants.productModels.get(position).getIsliked()) {
            binding.like.setImageResource(R.drawable.heartfill);
        } else {
            binding.like.setImageResource(R.drawable.heart);
        }

        binding.like.setOnClickListener(v -> {
            if (Constants.productModels.get(position).getIsliked()) {
                binding.like.setImageResource(R.drawable.heart);
                Constants.productModels.get(position).setIsliked(false);
            } else {
                binding.like.setImageResource(R.drawable.heartfill);
                Constants.productModels.get(position).setIsliked(true);
            }
        });

        if (Constants.productModels.get(position).getAddtocart()) {
            binding.addAddressBtn.setVisibility(View.GONE);
            binding.removeFromCart.setVisibility(View.VISIBLE);
        } else {
            binding.addAddressBtn.setVisibility(View.VISIBLE);
            binding.removeFromCart.setVisibility(View.GONE);
        }

        binding.addAddressBtn.setOnClickListener(v -> {
            if (!Constants.productModels.get(position).getAddtocart()) {
                binding.addAddressBtn.setVisibility(View.GONE);
                binding.removeFromCart.setVisibility(View.VISIBLE);
                Constants.productModels.get(position).setAddtocart(true);
            }
        });

        binding.minus.setOnClickListener(v -> {
            int quantityOfItem = Constants.productModels.get(position).getProductNumber();
            if (quantityOfItem > 1) {
                quantityOfItem--;
                binding.quantityOfItem.setText(String.valueOf(quantityOfItem));
                Constants.productModels.get(position).setProductNumber(quantityOfItem);
            }
        });

        binding.plus.setOnClickListener(v -> {
            int quantityOfItem = Constants.productModels.get(position).getProductNumber();
            quantityOfItem++;
            binding.quantityOfItem.setText(String.valueOf(quantityOfItem));
            Constants.productModels.get(position).setProductNumber(quantityOfItem);
        });
    }
}