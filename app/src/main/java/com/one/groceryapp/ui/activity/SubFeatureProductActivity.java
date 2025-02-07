package com.one.groceryapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.one.groceryapp.databinding.ActivitySubFeatureProductBinding;
import com.one.groceryapp.model.FeatureProductModel;

import java.util.List;

public class SubFeatureProductActivity extends AppCompatActivity {
    ActivitySubFeatureProductBinding binding;
    List<FeatureProductModel> featureProductModelList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubFeatureProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.filter.setOnClickListener(v -> {
            startActivity(new Intent(SubFeatureProductActivity.this, FilterActivity.class));
        });

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(SubFeatureProductActivity.this, MainActivity.class));
            finish();
        });

        featureProductModelList = (List<FeatureProductModel>) getIntent().getSerializableExtra("product");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        binding.rcv.setLayoutManager(gridLayoutManager);
        com.one.groceryapp.ui.adapter.SubFeaturedProductAdapter subFeaturedProductAdapter = new com.one.groceryapp.ui.adapter.SubFeaturedProductAdapter(featureProductModelList, context);
        binding.rcv.setAdapter(subFeaturedProductAdapter);
    }
}