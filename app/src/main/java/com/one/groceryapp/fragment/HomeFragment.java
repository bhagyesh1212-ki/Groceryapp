package com.one.groceryapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.R;
import com.one.groceryapp.adapter.DemoAdapter;
import com.one.groceryapp.adapter.ViewPagerBannerAdapter;
import com.one.groceryapp.databinding.FragmentHomeBinding;
import com.one.groceryapp.model.CategoriesModel;
import com.one.groceryapp.model.DemoModel;
import com.one.groceryapp.model.FeatureProductModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {

    ArrayList<CategoriesModel> categoriesModelArrayList;
    ArrayList<FeatureProductModel> featureProductModelArrayList;
    FragmentHomeBinding binding;
    ViewPagerBannerAdapter adapter;
    ArrayList<DemoModel> demoModelArrayList;
    int currentPage = 0;
    Timer timer;
    int NUM_PAGES = 5;


    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 4000; // time in milliseconds between successive task executions.


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        adapter = new ViewPagerBannerAdapter(getContext());
        binding.vp.setAdapter(adapter);
        binding.dot1.setViewPager(binding.vp);

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                }
                binding.vp.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        demoModelArrayList = new ArrayList<>();

        categoriesModelArrayList = new ArrayList<>();
        categoriesModelArrayList.add(new CategoriesModel("Vegetables", R.drawable.leaf, R.color.vegetables_green));
        categoriesModelArrayList.add(new CategoriesModel("Fruits", R.drawable.apple, R.color.fruit_apple));
        categoriesModelArrayList.add(new CategoriesModel("Beverages", R.drawable.drinks,R.color.beverage_cold_drinks_yellow));
        categoriesModelArrayList.add(new CategoriesModel("Grocery", R.drawable.grocery,R.color.grocery_purple));
        categoriesModelArrayList.add(new CategoriesModel("Edible oil", R.drawable.oil,R.color.oil_blue));
        categoriesModelArrayList.add(new CategoriesModel("Household", R.drawable.household,R.color.house_hold_pink));

        categoriesModelArrayList.add(new CategoriesModel("Vegetables", R.drawable.leaf, R.color.vegetables_green));
        categoriesModelArrayList.add(new CategoriesModel("Fruits", R.drawable.apple, R.color.fruit_apple));
        categoriesModelArrayList.add(new CategoriesModel("Beverages", R.drawable.drinks,R.color.beverage_cold_drinks_yellow));
        categoriesModelArrayList.add(new CategoriesModel("Grocery", R.drawable.grocery,R.color.grocery_purple));
        categoriesModelArrayList.add(new CategoriesModel("Edible oil", R.drawable.oil,R.color.oil_blue));
        categoriesModelArrayList.add(new CategoriesModel("Household", R.drawable.household,R.color.house_hold_pink));

        demoModelArrayList.add(new DemoModel("Vegetables", categoriesModelArrayList));

        featureProductModelArrayList = new ArrayList<>();
        featureProductModelArrayList.add(new FeatureProductModel("$8.00", "Fresh Peach", "dozen", R.drawable.peach,false,1));
        featureProductModelArrayList.add(new FeatureProductModel("$7.00", "Avacoda", "2.0 lbs", R.drawable.aocado,false,1));
        featureProductModelArrayList.add(new FeatureProductModel("$9.90", "Pineapple", "1.50 lbs", R.drawable.pineapplepieces,false,1));
        featureProductModelArrayList.add(new FeatureProductModel("$7.05", "Black Grapes", "5.0 lbs", R.drawable.grapes,false,1));
        featureProductModelArrayList.add(new FeatureProductModel("$2.09", "Pomegranate", "1.50 lbs", R.drawable.pomegranate,false,1));
        featureProductModelArrayList.add(new FeatureProductModel("$3.00", "Fresh B roccoli", "1 kg", R.drawable.greenfreshbroccoli,false,1));
        demoModelArrayList.add(new DemoModel("Featured products", featureProductModelArrayList));

        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DemoAdapter demoAdapter = new DemoAdapter(demoModelArrayList, getContext());
        binding.rcv.setAdapter(demoAdapter);
        return binding.getRoot();

    }
}

//    ArrayList<MainHomeModel> mainHomeModelArrayList;
//    ArrayList<TitleModel> titleModelArrayList;
//        mainHomeModelArrayList = new ArrayList<>();
//        categoriesModelArrayList = new ArrayList<>();
//        featureProductModelArrayList = new ArrayList<>();
//        titleModelArrayList = new ArrayList<>();

//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.TITLE, new TitleModel("Categories")));
//
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.CATEGORY_TYPE,new CategoriesModel("Vegetables",R.drawable.leaf)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.CATEGORY_TYPE,new CategoriesModel("Vegetables",R.drawable.leaf)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.CATEGORY_TYPE,new CategoriesModel("Vegetables",R.drawable.leaf)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.CATEGORY_TYPE,new CategoriesModel("Vegetables",R.drawable.leaf)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.CATEGORY_TYPE,new CategoriesModel("Vegetables",R.drawable.leaf)));
//
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.TITLE, new TitleModel("Featured products")));
//
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.FEATURE_PRODUCT_TYPE,new FeatureProductModel("$8.00","Fresh Peach","dozen",R.drawable.peach)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.FEATURE_PRODUCT_TYPE,new FeatureProductModel("$8.00","Fresh Peach","dozen",R.drawable.peach)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.FEATURE_PRODUCT_TYPE,new FeatureProductModel("$8.00","Fresh Peach","dozen",R.drawable.peach)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.FEATURE_PRODUCT_TYPE,new FeatureProductModel("$8.00","Fresh Peach","dozen",R.drawable.peach)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.FEATURE_PRODUCT_TYPE,new FeatureProductModel("$8.00","Fresh Peach","dozen",R.drawable.peach)));
//        mainHomeModelArrayList.add(new MainHomeModel(MainHomeAdapter.FEATURE_PRODUCT_TYPE,new FeatureProductModel("$8.00","Fresh Peach","dozen",R.drawable.peach)));
