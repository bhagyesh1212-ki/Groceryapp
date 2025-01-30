package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoCardBinding;
import com.one.groceryapp.model.AddressModel;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.List;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {

    static List<CardModel> cardModelList;
    Context context;
    AppDatabase appDatabase;
    UserDao userDao;

    public MyCardAdapter(List<CardModel> cardModelList, Context context) {
        this.cardModelList = cardModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoCardBinding binding = DemoCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        appDatabase = AppDatabase.getInstance(parent.getContext());
        userDao = appDatabase.userDao();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCardAdapter.ViewHolder holder, int position) {
        CardModel cardModel = cardModelList.get(position);
        holder.binding.lastFourDigit.setText(cardModel.getCardnumber());
        holder.binding.date.setText(cardModel.getCarddate());
        holder.binding.cvv.setText(cardModel.getCardcvv());

        for (int i = 1; i < userDao.getCardDataCount(); i++) {
            holder.binding.cardNumEdt.setText(cardModel.getCardnumber());
            holder.binding.cvvEdt.setText(cardModel.getCardcvv());
            holder.binding.dateEdt.setText(cardModel.getCarddate());
            holder.binding.nameEdt.setText(cardModel.getCardholder());
        }


            String holdername = holder.binding.nameEdt.getText().toString();
            String cardnumber = holder.binding.cardNumEdt.getText().toString();
            String carddate = holder.binding.dateEdt.getText().toString();
            String cardcvv = holder.binding.cvvEdt.getText().toString();


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

        holder.binding.switchView.setOnClickListener(v -> {
            if(holder.binding.switchView.isChecked()){
                holder.binding.default12.setVisibility(View.VISIBLE);
            }else {
                holder.binding.default12.setVisibility(View.GONE);
            }
        });

    }

    public static void updatedata(int position){
        CardModel cardModel = cardModelList.get(position);


    }

    @Override
    public int getItemCount() {
        return cardModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        DemoCardBinding binding;

        public ViewHolder(@NonNull DemoCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
