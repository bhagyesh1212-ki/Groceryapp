package com.one.groceryapp.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.one.groceryapp.databinding.FragmentPaymentBinding;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.ArrayList;


public class PaymentFragment extends Fragment {

    FragmentPaymentBinding binding;

    AppDatabase appDatabase;
    UserDao userDao;
    ArrayList<CardModel> cardModelArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase = AppDatabase.getInstance(getActivity());
        userDao = appDatabase.userDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        addValueFromPreference();
        return binding.getRoot();
    }

    private void addValueFromPreference() {
        SharedPreferences sf = getContext().getSharedPreferences("saveCard",Context.MODE_PRIVATE);
        String name = sf.getString("name", "");
        String number = sf.getString("number", "");
        String date = sf.getString("date", "");
        String cvv = sf.getString("cvv", "");
        binding.nameEdt.setText(name);
        binding.cardNumEdt.setText(number);
        binding.dateEdt.setText(date);
        binding.cvvEdt.setText(cvv);
    }
}