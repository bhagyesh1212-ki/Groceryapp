package com.one.groceryapp.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.ActivityMyOrderBinding;
import com.one.groceryapp.model.MyOrderModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.adapter.OrderAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyOrderActivity extends AppCompatActivity {

    ActivityMyOrderBinding binding;
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    ArrayList<MyOrderModel> orderModelArrayList = new ArrayList<>();

    AppDatabase appDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MMMM dd yyyy");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        int price = getIntent().getIntExtra("price", 0);
        int shippingcharge = getIntent().getIntExtra("itemquantity", 0);

        if (price > 0) {
            orderModelArrayList.add(new MyOrderModel(shippingcharge, price, dateTime));
            userDao.insertmyorder(orderModelArrayList);
        }
        orderModelArrayList = (ArrayList<MyOrderModel>) userDao.getallmyorder();

        OrderAdapter adapter = new OrderAdapter(orderModelArrayList, MyOrderActivity.this);
        binding.rcv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);

    }
}