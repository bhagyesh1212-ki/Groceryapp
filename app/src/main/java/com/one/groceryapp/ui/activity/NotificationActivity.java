package com.one.groceryapp.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {

    ActivityNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.saveSettingsBtn.setOnClickListener(v -> {
            onBackPressed();
            Toast.makeText(this, "save settings successfully", Toast.LENGTH_SHORT).show();
        });
    }
}