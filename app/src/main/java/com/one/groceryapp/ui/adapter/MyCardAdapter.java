package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoCardBinding;
import com.one.groceryapp.model.CardModel;
import com.one.groceryapp.roomdb.AppDatabase;
import com.one.groceryapp.roomdb.UserDao;

import java.util.List;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {

    List<CardModel> cardModelList;
    Context context;
    AppDatabase appDatabase;
    UserDao userDao;
    private int selectedposition;

    public MyCardAdapter(Context context, List<CardModel> cardModelList, int selectedposition) {
        this.context = context;
        this.cardModelList = cardModelList;
        this.selectedposition = selectedposition;
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

        for (int i = 0; i < userDao.getCardDataCount(); i++) {
            holder.binding.cardNumEdt.setText(cardModel.getCardnumber());
            holder.binding.cvvEdt.setText(cardModel.getCardcvv());
            holder.binding.dateEdt.setText(cardModel.getCarddate());
            holder.binding.nameEdt.setText(cardModel.getCardholder());
        }

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

        holder.binding.switchView.setOnCheckedChangeListener(null);
        holder.binding.switchView.setChecked(position == selectedposition);

        if (holder.binding.switchView.isChecked()) {
            holder.binding.default12.setVisibility(View.VISIBLE);
        } else {
            holder.binding.default12.setVisibility(View.GONE);
        }

        holder.binding.switchView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedposition = holder.getAdapterPosition();
                cardModel.setSwitched(true);
                notifyDataSetChanged();
                selectedPosition(selectedposition);
//                userDao.updateSwitch(true, selectedposition);
            } else {
                cardModel.setSwitched(false);
            }
        });

        if (getItemCount() == 1) {
            holder.binding.switchView.setChecked(true);
            holder.binding.default12.setVisibility(View.VISIBLE);
            String name = cardModelList.get(selectedposition).getCardholder();
            String number = cardModelList.get(selectedposition).getCardnumber();
            String date = cardModelList.get(selectedposition).getCarddate();
            String cvv = cardModelList.get(selectedposition).getCardcvv();
            SharedPreferences sf = context.getSharedPreferences("saveCard", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("name", name);
            editor.putString("number", number);
            editor.putString("date", date);
            editor.putString("cvv", cvv);
            editor.apply();
        }

        if (cardModel.isSwitched()) {
            String name = cardModelList.get(selectedposition).getCardholder();
            String number = cardModelList.get(selectedposition).getCardnumber();
            String date = cardModelList.get(selectedposition).getCarddate();
            String cvv = cardModelList.get(selectedposition).getCardcvv();
            SharedPreferences sf = context.getSharedPreferences("saveCard", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("name", name);
            editor.putString("number", number);
            editor.putString("date", date);
            editor.putString("cvv", cvv);
            editor.apply();
        }
    }

    private void selectedPosition(int selectedposition) {
        SharedPreferences sf = context.getSharedPreferences("selectedPosition", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("lastSelectedPosition", selectedposition);
        editor.apply();
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
//                if (selectedposition == holder.getAdapterPosition()) {
//                    selectedposition = 0;
//                    cardModel.setSwitched(false);
//                }