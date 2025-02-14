package com.one.groceryapp.ui.activity;

import android.content.SharedPreferences;
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
        SharedPreferences sf1 = getSharedPreferences("saveSwitch", MODE_PRIVATE);
        boolean allowNotificationSwitch = sf1.getBoolean("allowNotificationSwitch", false);
        boolean EmailNotificationSwitch = sf1.getBoolean("EmailNotificationSwitch", false);
        boolean OrderNotificationSwitch = sf1.getBoolean("OrderNotificationSwitch", false);
        boolean GeneralNotificationSwitch = sf1.getBoolean("GeneralNotificationSwitch", false);

        binding.allowNotificationSwitch.setChecked(allowNotificationSwitch);
        binding.EmailNotificationSwitch.setChecked(EmailNotificationSwitch);
        binding.OrderNotificationSwitch.setChecked(OrderNotificationSwitch);
        binding.GeneralNotificationSwitch.setChecked(GeneralNotificationSwitch);

        binding.saveSettingsBtn.setOnClickListener(v -> {
            onBackPressed();
            SharedPreferences sf = getSharedPreferences("saveSwitch", MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putBoolean("allowNotificationSwitch", binding.allowNotificationSwitch.isChecked());
            editor.putBoolean("EmailNotificationSwitch", binding.EmailNotificationSwitch.isChecked());
            editor.putBoolean("OrderNotificationSwitch", binding.OrderNotificationSwitch.isChecked());
            editor.putBoolean("GeneralNotificationSwitch", binding.GeneralNotificationSwitch.isChecked());
            editor.apply();

            Toast.makeText(this, "save settings successfully", Toast.LENGTH_SHORT).show();
        });


    }
}