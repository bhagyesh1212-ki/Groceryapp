package com.one.groceryapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.FragmentLikeBinding;
import com.one.groceryapp.model.AddtocartModel;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.ui.activity.AddToCartActivity;
import com.one.groceryapp.ui.adapter.AddToCartAdapter;
import com.one.groceryapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class LikeFragment extends Fragment implements  AddToCartAdapter.OnPriceChangeListener  {

    FragmentLikeBinding binding;
    AddToCartAdapter adapter;
    List<FeatureProductModel> featureProductModelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLikeBinding.inflate(inflater,container,false);

        for (int i = 0; i < Constants.productModels.size(); i++) {
            if (Constants.arrayList.get(i).getIsliked()) {
                featureProductModelList.add(Constants.productModels.get(i));
            }else {
                featureProductModelList.remove(Constants.productModels.get(i));
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcv.setLayoutManager(linearLayoutManager);
        adapter = new AddToCartAdapter(featureProductModelList,getContext(),this);
        binding.rcv.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onPriceChange(int newPrice) {
//        Log.d("AddToCartActivity", "Updated Price: " + newPrice);
//        runOnUiThread(() -> {
//            binding.subTotal.setText(String.valueOf(newPrice));
//            Log.d("AddToCartActivity", "SubTotal TextView updated: " + newPrice);
//        });
//
//        if (adapter.getItemCount() == 0) {
//            binding.shippingCharge.setText("0");
//            binding.totalPay.setText("0");
//            Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
//        } else {
//            shippingcharge = calculateShippingCharge(adapter.getItemCount());
//            int total = shippingcharge + newPrice;
//            binding.shippingCharge.setText(String.valueOf(shippingcharge));
//            binding.totalPay.setText(String.valueOf(total));
//        }
    }
}