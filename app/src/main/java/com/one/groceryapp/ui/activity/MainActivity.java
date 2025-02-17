package com.one.groceryapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityMainBinding;
import com.one.groceryapp.ui.fragment.HomeFragment;
import com.one.groceryapp.ui.fragment.LikeFragment;
import com.one.groceryapp.ui.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        fragmentManager.addOnBackStackChangedListener(() -> {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
            if (currentFragment instanceof HomeFragment) {
                changeicon(R.id.home);
            } else if (currentFragment instanceof ProfileFragment) {
                changeicon(R.id.profile);
            } else if (currentFragment instanceof LikeFragment) {
                changeicon(R.id.like);
            }
        });

        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
            changeicon(R.id.home);
        }

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new HomeFragment());
                changeicon(R.id.home);
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ProfileFragment());
                changeicon(R.id.profile);
            }
        });

        binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new LikeFragment());
                changeicon(R.id.like);
            }
        });

        binding.shop.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddToCartActivity.class);
            startActivity(i);
            finish();
        });
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void changeicon(int iconid) {
        binding.home.setImageResource(iconid == R.id.home ? R.drawable.home_black : R.drawable.home_white);
        binding.profile.setImageResource(iconid == R.id.profile ? R.drawable.profile_black : R.drawable.profile_white);
        binding.like.setImageResource(iconid == R.id.like ? R.drawable.like_black : R.drawable.like_white);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finishAffinity();
        }
    }
}