package com.one.groceryapp.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.one.groceryapp.R;
import com.one.groceryapp.adapter.SubCategoryAdapter;
import com.one.groceryapp.databinding.ActivitySubVegetableBinding;
import com.one.groceryapp.model.CategoriesModel;

import java.util.ArrayList;

public class SubVegetableActivity extends AppCompatActivity {

    ActivitySubVegetableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySubVegetableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<CategoriesModel> list = new ArrayList<>();
        list.add(new CategoriesModel( "Vegetable",R.drawable.leaf,R.color.grocery_purple));
        list.add(new CategoriesModel("Fruits", R.drawable.apple, R.color.fruit_apple));
        list.add(new CategoriesModel("Beverages", R.drawable.drinks,R.color.beverage_cold_drinks_yellow));
        list.add(new CategoriesModel("Grocery", R.drawable.grocery,R.color.grocery_purple));
        list.add(new CategoriesModel("Edible oil", R.drawable.oil,R.color.oil_blue));
        list.add(new CategoriesModel("Household", R.drawable.household,R.color.house_hold_pink));


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

    }
}