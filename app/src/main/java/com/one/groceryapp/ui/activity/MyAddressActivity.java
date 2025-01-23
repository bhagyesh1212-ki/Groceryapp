package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.RoomSQLiteQuery;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityMyAddressBinding;

public class MyAddressActivity extends AppCompatActivity {

    ActivityMyAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addaddress.setOnClickListener(v -> {
            Intent i = new Intent(this, AddAddressActivity.class);
            startActivity(i);
        });

    }
}