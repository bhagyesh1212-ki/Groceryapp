package com.one.groceryapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.FragmentHomeBinding;
import com.one.groceryapp.preferences.SharedPreferencesUtils;
import com.one.groceryapp.ui.activity.AddToCartActivity;
import com.one.groceryapp.ui.activity.FilterActivity;
import com.one.groceryapp.ui.adapter.DemoAdapter;
import com.one.groceryapp.ui.adapter.ViewPagerBannerAdapter;
import com.one.groceryapp.utils.Constants;

import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements AddToCartActivity.OnCartItemDeletedListener{

    FragmentHomeBinding binding;
    ViewPagerBannerAdapter adapter;
    int currentPage = 0;
    Timer timer;
    int NUM_PAGES = 5;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 4000;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        adapter = new ViewPagerBannerAdapter(getContext());
        binding.vp.setAdapter(adapter);
        binding.dot1.setViewPager(binding.vp);

//        SharedPreferencesUtils preferencesUtils = new SharedPreferencesUtils(requireActivity());
//        Log.e("TAG&&&", "onCreateView: " + preferencesUtils.getLibraryArrayList());
//        if (preferencesUtils.getLibraryArrayList() == null) {
//            Log.e("TAG&&&", "onCreateView:gvgjhvubhu ");
//            preferencesUtils.setLibraryArrayList(Constants.featureProductModelArrayList());
//        }

        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES - 1) {
                currentPage = 0;
            }
            binding.vp.setCurrentItem(currentPage++, true);
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        binding.filter.setOnClickListener(v -> startActivity(new Intent(getContext(), FilterActivity.class)));
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DemoAdapter demoAdapter = new DemoAdapter(Constants.get_home_title(), getContext());
        binding.rcv.setAdapter(demoAdapter);
        return binding.getRoot();
    }

    @Override
    public void onCartItemDeleted() {
        Log.d("HomeFragment", "Cart item deleted, refreshing the fragment");
    }
}
