package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.ActivityMyCardsBinding;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.adapter.MyCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCardsActivity extends AppCompatActivity {
    ActivityMyCardsBinding binding;
    List<CardModel> cardModelArrayList = new ArrayList<>();
    AppDatabase appDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyCardsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        binding.addcard.setOnClickListener(v -> {
            startActivity(new Intent(this, AddCardsActivity.class));
            finish();
        });

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.saveSettingsBtn.setOnClickListener(v -> {
            onBackPressed();
            Toast.makeText(this, "save settings successfully", Toast.LENGTH_SHORT).show();
        });

        String nameEdt = getIntent().getStringExtra("nameEdt");
        String cardNumEdt = getIntent().getStringExtra("cardNumEdt");
        String dateEdt = getIntent().getStringExtra("dateEdt");
        String cvvEdt = getIntent().getStringExtra("cvvEdt");

        if (nameEdt != null) {
            CardModel cardModel = new CardModel(nameEdt, cardNumEdt, dateEdt, cvvEdt);
            cardModelArrayList.add(cardModel);
            userDao.insercard(cardModelArrayList);
        }

        cardModelArrayList = userDao.getallcards();

        MyCardAdapter adapter = new MyCardAdapter(cardModelArrayList, this);
        binding.rcv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);
    }
}