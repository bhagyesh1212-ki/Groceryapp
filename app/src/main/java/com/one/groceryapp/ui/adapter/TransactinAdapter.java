package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.DemoTransactionBinding;
import com.one.groceryapp.model.TransactionModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactinAdapter extends RecyclerView.Adapter<TransactinAdapter.ViewHolder> {
    ArrayList<TransactionModel> transactionModelArrayList;
    Context context;



    public TransactinAdapter(ArrayList<TransactionModel> transactionModelArrayList, Context context) {
        this.transactionModelArrayList = transactionModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TransactinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoTransactionBinding binding = DemoTransactionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactinAdapter.ViewHolder holder, int position) {
        TransactionModel transactionModel = transactionModelArrayList.get(position);
        holder.binding.price.setText(String.valueOf(transactionModel.getPrice()));
        holder.binding.dateAndTime.setText(transactionModel.getDate());
    }

    @Override
    public int getItemCount() {
        return transactionModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoTransactionBinding binding;

        public ViewHolder(@NonNull DemoTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
