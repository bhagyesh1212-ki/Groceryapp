package com.one.groceryapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.one.groceryapp.databinding.FragmentDeliveryBinding;
import com.one.groceryapp.model.DeliveryModel;
import com.one.groceryapp.ui.adapter.DeliveryAdapter;

import java.util.ArrayList;


public class DeliveryFragment extends Fragment {
    FragmentDeliveryBinding binding;
    ArrayList<DeliveryModel> deliveryModelArrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentDeliveryBinding binding = FragmentDeliveryBinding.inflate(inflater, container, false);

        deliveryModelArrayList.add(new DeliveryModel("Standard Delivery","Order will be delivered between 3 - 4 business days straights to your doorstep.",7));
        deliveryModelArrayList.add(new DeliveryModel("Next Day Delivery","Order will be delivered between 3 - 4 business days straights to your doorstep.",5));
        deliveryModelArrayList.add(new DeliveryModel("Nominated Delivery","Order will be delivered between 3 - 4 business days straights to your doorstep.",3));

        DeliveryAdapter deliveryAdapter = new DeliveryAdapter(deliveryModelArrayList, getContext(),0);
        binding.rcv.setAdapter(deliveryAdapter);

        return binding.getRoot();
    }
}