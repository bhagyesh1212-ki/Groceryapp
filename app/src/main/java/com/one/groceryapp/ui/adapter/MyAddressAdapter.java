package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoAddressBinding;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.List;

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> implements AdapterView.OnItemSelectedListener {

    public static MyAddressAdapter.ViewHolder ViewHolder;
    List<AddressModel> addressModelList;
    Context context;
    AppDatabase appDatabase;
    UserDao userDao;
    private int selectedposition = 0;
    private static final int UNSELECTED = -1;
    public static int selectedItem = UNSELECTED;

    private static SparseBooleanArray expandedPositions = new SparseBooleanArray();


    public MyAddressAdapter(List<AddressModel> addressModelList, Context context, int selectedposition) {
        this.addressModelList = addressModelList;
        this.context = context;
        this.selectedposition = selectedposition;
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
    public void onBindViewHolder(@NonNull MyAddressAdapter.ViewHolder holder, int position) {
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
            holder.binding.spinner.setText(addressModel.getCountry());
        }


        // Restore the expanded state
        boolean isExpanded = expandedPositions.get(position, false);
        holder.binding.dropUp.setSelected(isExpanded);
        holder.binding.expandableLayout.setExpanded(isExpanded, false);

        holder.binding.mainLayout.setOnClickListener(v -> {
            boolean isSelected = expandedPositions.get(position, false);
            expandedPositions.put(position, !isSelected);
            notifyItemChanged(position);
        });

        if (isExpanded) {
            holder.binding.dropUp.setImageResource(R.drawable.dropdown);
        } else {
            holder.binding.dropUp.setImageResource(R.drawable.dropup);
        }

        holder.binding.switchview.setOnCheckedChangeListener(null);
        holder.binding.switchview.setChecked(position == selectedposition);

        if (holder.binding.switchview.isChecked()) {
            holder.binding.default12.setVisibility(View.VISIBLE);
        } else {
            holder.binding.default12.setVisibility(View.GONE);
        }

        holder.binding.switchview.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedposition = position;
                addressModel.setIsswitched(true);
                selectedPosition(selectedposition);
                notifyDataSetChanged();
            } else {
                addressModel.setIsswitched(false);
            }
        });

//        boolean isSelected = position == selectedItem;
//        holder.binding.dropUp.setSelected(isSelected);
//        holder.binding.expandableLayout.setExpanded(isSelected, false);
//
//        holder.binding.dropUp.setOnClickListener(v -> {
//
//            holder.binding.dropUp.setSelected(false);
//            holder.binding.dropUp.setImageResource(R.drawable.dropup);
//            holder.binding.expandableLayout.collapse();
//
//            if (position == selectedItem) {
//                selectedItem = UNSELECTED;
//            } else {
//                holder.binding.dropUp.setSelected(true);
//                holder.binding.expandableLayout.expand();
//                holder.binding.dropUp.setImageResource(R.drawable.dropdown);
//                selectedItem = position;
//            }
//        });
//
//        holder.binding.switchview.setOnCheckedChangeListener(null);
//        holder.binding.switchview.setChecked(position == selectedposition);
//
//        if (holder.binding.switchview.isChecked()) {
//            holder.binding.default12.setVisibility(View.VISIBLE);
//        } else {
//            holder.binding.default12.setVisibility(View.GONE);
//        }
//
//        holder.binding.switchview.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                selectedposition = holder.getAdapterPosition();
//                notifyDataSetChanged();
//                addressModel.setIsswitched(true);
//                selectedPosition(selectedposition);
//            } else {
//                if (selectedposition == holder.getAdapterPosition()) {
//                    selectedposition = -1;
//                    addressModel.setIsswitched(false);
//                }
//            }
//        });

        if (getItemCount() == 1) {
            holder.binding.switchview.setChecked(true);
            holder.binding.default12.setVisibility(View.VISIBLE);
            String name = addressModelList.get(selectedposition).getName();
            String email = addressModelList.get(selectedposition).getEmail();
            String phone = addressModelList.get(selectedposition).getMobile_number();
            String address = addressModelList.get(selectedposition).getAddress();
            String zip = addressModelList.get(selectedposition).getZip();
            String city = addressModelList.get(selectedposition).getCity();
            String country = addressModelList.get(selectedposition).getCountry();
            SharedPreferences sf = context.getSharedPreferences("saveaddress", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("address", address);
            editor.putString("zip", zip);
            editor.putString("country", country);
            editor.putString("city", city);
            editor.apply();
        }

        if (addressModel.getIsswitched()) {
            String name = addressModelList.get(selectedposition).getName();
            String email = addressModelList.get(selectedposition).getEmail();
            String phone = addressModelList.get(selectedposition).getMobile_number();
            String address = addressModelList.get(selectedposition).getAddress();
            String zip = addressModelList.get(selectedposition).getZip();
            String country = addressModelList.get(selectedposition).getCountry();
            String city = addressModelList.get(selectedposition).getCity();
            SharedPreferences sf = context.getSharedPreferences("saveaddress", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("address", address);
            editor.putString("zip", zip);
            editor.putString("country", country);
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

    private void selectedPosition(int selectedposition) {
        SharedPreferences sf = context.getSharedPreferences("selectedPositionAddress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("lastSelectedPositionInAddress", selectedposition);
        editor.apply();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoAddressBinding binding;

        public ViewHolder(@NonNull DemoAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
