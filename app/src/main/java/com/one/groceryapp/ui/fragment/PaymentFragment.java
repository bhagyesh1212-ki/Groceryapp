package com.one.groceryapp.ui.fragment;

import android.os.Bundle;
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
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        for (int i = 2; i < userDao.getCardDataCount() ; i++) {
            cardModelArrayList = (ArrayList<CardModel>) userDao.getcardforfinalpayment();

            String name = cardModelArrayList.get(i).getCardholder();
            String number = cardModelArrayList.get(i).getCardnumber();
            String date = cardModelArrayList.get(i).getCarddate();
            String cvv = cardModelArrayList.get(i).getCardcvv();

            binding.nameEdt.setText(name);
            binding.cardNumEdt.setText(number);
            binding.dateEdt.setText(date);
            binding.cvvEdt.setText(cvv);
        }

        return binding.getRoot();
    }
}