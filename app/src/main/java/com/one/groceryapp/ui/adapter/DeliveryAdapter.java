package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.DemoDeliveryOptionBinding;
import com.one.groceryapp.model.DeliveryModel;

import java.util.ArrayList;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    private ArrayList<DeliveryModel> deliveryModelArrayList;
    Context context;

    int selectedPosition = RecyclerView.NO_POSITION;

    public DeliveryAdapter(ArrayList<DeliveryModel> deliveryModelArrayList, Context context, int selectedPosition) {
        this.deliveryModelArrayList = deliveryModelArrayList;
        this.context = context;
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public DeliveryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoDeliveryOptionBinding binding = DemoDeliveryOptionBinding.inflate(LayoutInflater.from(context));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAdapter.ViewHolder holder, int position) {
        DeliveryModel deliveryModel = deliveryModelArrayList.get(position);
        holder.demoDeliveryOptionBinding.txtDelivery.setText(deliveryModel.getDeliveryType());
        holder.demoDeliveryOptionBinding.txtDeliveryCharge.setText("$" + deliveryModel.getDeliveryPrice());

        if (selectedPosition == position) {
            holder.demoDeliveryOptionBinding.first.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.demoDeliveryOptionBinding.first.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            SharedPreferences sf = context.getSharedPreferences("DeliveryType", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sf.edit();
            editor.putInt("selectedPositionForDelivery", selectedPosition);
            Log.d("TAG@@@", "onBindViewHolder: "+selectedPosition);
            editor.apply();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return deliveryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoDeliveryOptionBinding demoDeliveryOptionBinding;
        public ViewHolder(@NonNull DemoDeliveryOptionBinding demoDeliveryOptionBinding) {
            super(demoDeliveryOptionBinding.getRoot());
            this.demoDeliveryOptionBinding = demoDeliveryOptionBinding;
        }
    }
}
