package com.one.groceryapp.ui.activity;

import static androidx.core.content.ContextCompat.registerReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.ActivityTransactionBinding;
import com.one.groceryapp.model.MyOrderModel;
import com.one.groceryapp.model.TransactionModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.adapter.TransactinAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionActivity extends AppCompatActivity {

    ActivityTransactionBinding binding;
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    AppDatabase appDatabase;
    UserDao userDao;
    int price;
    ArrayList<MyOrderModel> myOrderModelArrayList = new ArrayList<>();
    ArrayList<MyOrderModel> myOrderModelArrayList1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        myOrderModelArrayList = (ArrayList<MyOrderModel>) userDao.getallmyorder();

        for (int i = 0; i < myOrderModelArrayList.size(); i++) {
             price = myOrderModelArrayList.get(i).getPrice();
        }

        myOrderModelArrayList1.add(new MyOrderModel(0,price,dateTime));
        myOrderModelArrayList1 = (ArrayList<MyOrderModel>) userDao.getallmyorder();

        TransactinAdapter adapter = new TransactinAdapter(myOrderModelArrayList1, TransactionActivity.this);
        binding.rcv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);
    }
}