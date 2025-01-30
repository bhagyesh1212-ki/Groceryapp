package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityMyCardsBinding;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.adapter.MyAddressAdapter;
import com.one.groceryapp.ui.adapter.MyCardAdapter;
import com.one.groceryapp.ui.fragment.ProfileFragment;

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
        });

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.saveSettingsBtn.setOnClickListener(v -> {
          onBackPressed();
        });

        String nameEdt = getIntent().getStringExtra("nameEdt");
        String cardNumEdt = getIntent().getStringExtra("cardNumEdt");
        String dateEdt = getIntent().getStringExtra("dateEdt");
        String cvvEdt = getIntent().getStringExtra("cvvEdt");

        if(nameEdt != null){
            CardModel cardModel = new CardModel(nameEdt,cardNumEdt,dateEdt,cvvEdt);
            cardModelArrayList.add(cardModel);
            userDao.insercard(cardModelArrayList);
        }

        cardModelArrayList = userDao.getallcards();

        MyCardAdapter adapter = new MyCardAdapter(cardModelArrayList,this);
        binding.rcv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);
    }
}