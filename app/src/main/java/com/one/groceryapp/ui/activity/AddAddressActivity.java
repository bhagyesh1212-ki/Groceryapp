package com.one.groceryapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.one.groceryapp.databinding.ActivityAddAddressBinding;
import com.one.groceryapp.model.AddressModel;

import java.util.ArrayList;
import java.util.List;

public class AddAddressActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityAddAddressBinding binding;

    List<AddressModel> addressModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(this, MyAddressActivity.class));
            finish();
        });

        final Spinner spinner = binding.spinner;
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("India");
        categories.add("USA");
        categories.add("China");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        binding.addAddressBtn.setOnClickListener(v -> {
            String name = binding.nameEdt.getText().toString();
            String email = binding.email.getText().toString();
            String address = binding.address.getText().toString();
            String zip = binding.zip.getText().toString();
            String city = binding.city.getText().toString();
            String country = binding.spinner.getSelectedItem().toString();
            String phone = binding.phone.getText().toString();

            if(binding.switchView.isChecked()){
                SharedPreferences sf = getSharedPreferences("saveaddress",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("phone", phone);
                editor.putString("address", address);
                editor.putString("country", country);
                editor.putString("zip", zip);
                editor.putString("city", city);
                editor.apply();
            }

            if (name.isEmpty() || email.isEmpty() || address.isEmpty() || zip.isEmpty() || city.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill all the detail", Toast.LENGTH_SHORT).show();
            } else if (!name.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}")) {
                Toast.makeText(this, "Enter valid name", Toast.LENGTH_SHORT).show();
            } else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
            } else if (phone.length() != 10) {
                Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
            } else if (zip.length() != 6) {
                Toast.makeText(this, "Enter valid zip code", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(AddAddressActivity.this, MyAddressActivity.class);
                i.putExtra("name", name);
                i.putExtra("email", email);
                i.putExtra("address", address);
                i.putExtra("zip", zip);
                i.putExtra("city", city);
                i.putExtra("country", country);
                i.putExtra("phone", phone);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(15);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
