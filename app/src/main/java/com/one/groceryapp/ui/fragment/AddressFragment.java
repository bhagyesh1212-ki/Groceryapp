package com.one.groceryapp.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.one.groceryapp.databinding.FragmentAddressBinding;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    FragmentAddressBinding binding;
    AppDatabase appDatabase;
    UserDao userDao;
    List<AddressModel> addressModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDatabase  = AppDatabase.getInstance(getContext());
        userDao = appDatabase.userDao();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddressBinding.inflate(inflater, container, false);
//        final Spinner spinner = binding.spinner;
//        spinner.setOnItemSelectedListener(this);
//
//        List<String> categories = new ArrayList<String>();
//        categories.add("India");
//        categories.add("USA");
//        categories.add("China");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);

        saveToPreference();
        return binding.getRoot();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(15);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void saveToPreference(){
        SharedPreferences sf = getContext().getSharedPreferences("saveaddress",Context.MODE_PRIVATE);
        String name = sf.getString("name","");
        String email = sf.getString("email","");
        String phone = sf.getString("phone","");
        String address = sf.getString("address","");
        String zip = sf.getString("zip","");
        String city = sf.getString("city","");
        String country = sf.getString("country","");
        binding.nameEdt.setText(name);
        binding.email.setText(email);
        binding.phone.setText(phone);
        binding.address.setText(address);
        binding.zip.setText(zip);
        binding.city.setText(city);
        binding.spinner.setText(country);
    }
}