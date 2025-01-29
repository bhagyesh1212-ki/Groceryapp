package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.one.groceryapp.R;
import com.one.groceryapp.ui.adapter.SubCategoryAdapter;
import com.one.groceryapp.databinding.ActivitySubVegetableBinding;
import com.one.groceryapp.model.CategoriesModel;

import java.util.ArrayList;

public class SubVegetableActivity extends AppCompatActivity {

    ActivitySubVegetableBinding binding;
    ArrayList<CategoriesModel> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubVegetableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = (ArrayList<CategoriesModel>) getIntent().getSerializableExtra("category");

//        list.add(new CategoriesModel( "Vegetable",R.drawable.leaf,R.color.grocery_purple));
//        list.add(new CategoriesModel("Fruits", R.drawable.apple, R.color.fruit_apple));
//        list.add(new CategoriesModel("Beverages", R.drawable.drinks,R.color.beverage_cold_drinks_yellow));
//        list.add(new CategoriesModel("Grocery", R.drawable.grocery,R.color.grocery_purple));
//        list.add(new CategoriesModel("Edible oil", R.drawable.oil,R.color.oil_blue));
//        list.add(new CategoriesModel("Household", R.drawable.household,R.color.house_hold_pink));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SubVegetableActivity.this, 3);
        binding.rcv.setLayoutManager(gridLayoutManager);
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(list, SubVegetableActivity.this);
        binding.rcv.setAdapter(subCategoryAdapter);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.filter.setOnClickListener(v -> {
            startActivity(new Intent(SubVegetableActivity.this, FilterActivity.class));
        });

    }
}