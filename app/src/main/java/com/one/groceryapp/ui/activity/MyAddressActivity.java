package com.one.groceryapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.ActivityMyAddressBinding;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;
import com.one.groceryapp.ui.adapter.MyAddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity {

    ActivityMyAddressBinding binding;
    List<AddressModel> addressModelList = new ArrayList<>();
    AppDatabase appDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
//                new IntentFilter("custom-message"));

        binding.addaddress.setOnClickListener(v -> {
            Intent i = new Intent(this, AddAddressActivity.class);
            startActivity(i);
        });

        binding.back.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.saveSettingsBtn.setOnClickListener(v -> {
            onBackPressed();
        });

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        userDao = appDatabase.userDao();

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String address = getIntent().getStringExtra("address");
        String zip = getIntent().getStringExtra("zip");
        String city = getIntent().getStringExtra("city");
        String country = getIntent().getStringExtra("country");
        String phone = getIntent().getStringExtra("phone");

        if (name != null) {
            addressModelList.add(new AddressModel(name, email, phone, address, zip, city, country));
            userDao.insertAddress(addressModelList);
        }

        addressModelList = userDao.getaddress();

        MyAddressAdapter adapter = new MyAddressAdapter(addressModelList, MyAddressActivity.this);
        binding.rcv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);
    }

//    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String nameUpdate = intent.getStringExtra("nameUpdate");
//            String addressUpdate = intent.getStringExtra("addressUpdate");
//            String zipUpdate = intent.getStringExtra("zipUpdate");
//            String cityUpdate = intent.getStringExtra("cityUpdate");
//            String phoneUpdate = intent.getStringExtra("phoneUpdate");
//
//            binding.saveSettingsBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i = 0; i < userDao.getDataCount(); i++) {
//                        userDao.updateaddress(nameUpdate, cityUpdate, zipUpdate, phoneUpdate, addressUpdate,i);
//                    }
//                }
//            });
//        }
//    };
}
