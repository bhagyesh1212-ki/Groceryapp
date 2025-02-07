package com.one.groceryapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoAddressBinding;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener {

    public static MyAddressAdapter.ViewHolder ViewHolder;
    List<AddressModel> addressModelList;
    Context context;
    AppDatabase appDatabase;
    UserDao userDao;
    private int selectedposition = 0;

    public MyAddressAdapter(List<AddressModel> addressModelList, Context context) {
        this.addressModelList = addressModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoAddressBinding binding = DemoAddressBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        appDatabase = AppDatabase.getInstance(parent.getContext());
        userDao = appDatabase.userDao();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AddressModel addressModel = addressModelList.get(position);
        holder.binding.name.setText(addressModel.getName());
        holder.binding.address.setText(addressModel.getAddress() + "." + addressModel.getCity() + "," + addressModel.getCountry() + " " + addressModel.getZip());
        holder.binding.mobileNumber.setText(String.valueOf(addressModel.getMobile_number()));

        for (int i = 0; i < userDao.getDataCount(); i++) {
//          List<AddressModel> addressModels = userDao.getaddressbyid(i);
            holder.binding.nameEdt.setText(addressModel.getName());
            holder.binding.phoneEdit.setText(addressModel.getMobile_number());
            holder.binding.addressEdit.setText(addressModel.getAddress());
            holder.binding.cityEdit.setText(addressModel.getCity());
            holder.binding.zipEdit.setText(addressModel.getZip());
        }

//        holder.binding.switchview.setOnClickListener(v -> {
//            if (holder.binding.switchview.isChecked()) {
//
//                holder.binding.default12.setVisibility(View.VISIBLE);
//            } else {
//                holder.binding.default12.setVisibility(View.GONE);
//            }
//        });

        final Spinner spinner = holder.binding.spinner;
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("India");
        categories.add("USA");
        categories.add("China");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        holder.binding.dropUp.setOnClickListener(v -> {
            if (holder.binding.personalDetail.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.binding.mainLayout, new AutoTransition());
                holder.binding.personalDetail.setVisibility(View.GONE);
                holder.binding.dropUp.setImageResource(R.drawable.dropup);
            } else {
                TransitionManager.beginDelayedTransition(holder.binding.mainLayout, new AutoTransition());
                holder.binding.personalDetail.setVisibility(View.VISIBLE);
                holder.binding.dropUp.setImageResource(R.drawable.dropdown);
            }
        });

        holder.binding.switchview.setOnCheckedChangeListener(null);
        holder.binding.switchview.setChecked(position == selectedposition);

        if (holder.binding.switchview.isChecked()) {
            holder.binding.default12.setVisibility(View.VISIBLE);
        } else {
            holder.binding.default12.setVisibility(View.GONE);
        }

        holder.binding.switchview.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedposition = holder.getAdapterPosition();
                notifyDataSetChanged();
                addressModel.setIsswitched(true);
            } else {
                if (selectedposition == holder.getAdapterPosition()) {
                    selectedposition = -1;
                    addressModel.setIsswitched(false);
                }
            }
        });

        if(addressModel.getIsswitched()) {
            String name = addressModelList.get(selectedposition).getName();
            String email = addressModelList.get(selectedposition).getEmail();
            String phone = addressModelList.get(selectedposition).getMobile_number();
            String address = addressModelList.get(selectedposition).getAddress();
            String zip = addressModelList.get(selectedposition).getZip();
            String city = addressModelList.get(selectedposition).getCity();
            SharedPreferences sf = context.getSharedPreferences("saveaddress",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("address", address);
            editor.putString("zip", zip);
            editor.putString("city", city);
            editor.apply();
        }
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.GRAY);
        ((TextView) parent.getChildAt(0)).setTextSize(12);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DemoAddressBinding binding;

        public ViewHolder(@NonNull DemoAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
