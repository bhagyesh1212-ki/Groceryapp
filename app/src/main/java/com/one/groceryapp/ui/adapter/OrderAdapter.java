package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.DemoMyorderBinding;
import com.one.groceryapp.model.MyOrderModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<MyOrderModel> myOrderModelList;
    Context context;

    public OrderAdapter(List<MyOrderModel> myOrderModelList, Context context) {
        this.myOrderModelList = myOrderModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoMyorderBinding binding = DemoMyorderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        MyOrderModel orderModel = myOrderModelList.get(position);
        holder.binding.item.setText(String.valueOf(orderModel.getItem()));
        holder.binding.price.setText(String.valueOf(orderModel.getPrice()));
        holder.binding.date.setText(orderModel.getDate());
    }

    @Override
    public int getItemCount() {
        return myOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoMyorderBinding binding;

        public ViewHolder(@NonNull DemoMyorderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
