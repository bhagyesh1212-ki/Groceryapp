package com.one.groceryapp.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.ActivityTransactionBinding;
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
    ArrayList<TransactionModel> transactionModelArrayList = new ArrayList<>();

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

        SharedPreferences sf = getSharedPreferences("saveprice", MODE_PRIVATE);
        int price = sf.getInt("price", 0);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm a");

        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        if(String.valueOf(price) != null){
            transactionModelArrayList.add(new TransactionModel(price, dateTime));
            userDao.inserttransaction(transactionModelArrayList);
        }

        transactionModelArrayList = (ArrayList<TransactionModel>) userDao.getalltransaction();

        TransactinAdapter adapter = new TransactinAdapter(transactionModelArrayList, TransactionActivity.this);
        binding.rcv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);
    }
}