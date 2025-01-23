package com.one.groceryapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.one.groceryapp.R;
import com.one.groceryapp.ui.adapter.FeatureProductAdapter;
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
        EdgeToEdge.enable(this);
        binding = ActivitySubFeatureProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.filter.setOnClickListener(v -> {
            startActivity(new Intent(SubFeatureProductActivity.this, FilterActivity.class));
        });

        featureProductModelList = (List<FeatureProductModel>) getIntent().getSerializableExtra("product");
//        ArrayList<FeatureProductModel> modelArrayList = new ArrayList<>();
//        modelArrayList.add(new FeatureProductModel("$8.00", "Fresh Peach", "dozen", R.drawable.peach,false,1));
//        modelArrayList.add(new FeatureProductModel("$7.00", "Avacoda", "2.0 lbs", R.drawable.aocado,false,1));
//        modelArrayList.add(new FeatureProductModel("$9.90", "Pineapple", "1.50 lbs", R.drawable.pineapplepieces,false,1));
//        modelArrayList.add(new FeatureProductModel("$7.05", "Black Grapes", "5.0 lbs", R.drawable.grapes,false,1));
//        modelArrayList.add(new FeatureProductModel("$2.09", "Pomegranate", "1.50 lbs", R.drawable.pomegranate,false,1));
//        modelArrayList.add(new FeatureProductModel("$3.00", "Fresh B roccoli", "1 kg", R.drawable.greenfreshbroccoli,false,1));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        binding.rcv.setLayoutManager(gridLayoutManager);
        FeatureProductAdapter featureProductAdapter = new FeatureProductAdapter(context);

        binding.rcv.setAdapter(featureProductAdapter);

        binding.back.setOnClickListener(v -> onBackPressed());
    }
}