package com.one.groceryapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.one.groceryapp.R;
import com.one.groceryapp.adapter.ViewPagerAdapter;
import com.one.groceryapp.databinding.ActivityFirstIntroScreenBinding;

public class FirstIntroScreenActivity extends AppCompatActivity {

    ActivityFirstIntroScreenBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFirstIntroScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager, true);
        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstIntroScreenActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(FirstIntroScreenActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}

