package com.one.groceryapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.R;
import com.one.groceryapp.adapter.GroceryAdapter;
import com.one.groceryapp.databinding.FragmentHomeBinding;
import com.one.groceryapp.model.CategoriesModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<CategoriesModel> modelArrayList;
    FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        modelArrayList = new ArrayList<>();

        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));
        modelArrayList.add(new CategoriesModel("Categories", R.drawable.leaf));

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        GroceryAdapter groceryAdapter = new GroceryAdapter(modelArrayList, getContext());
        binding.rcv.setAdapter(groceryAdapter);
        return binding.getRoot();
    }
}