package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.DemoAddtocartBinding;
import com.one.groceryapp.model.FeatureProductModel;

import java.util.List;


public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {

    List<FeatureProductModel> featureProductModelList;
    Context context;
    int subprice = 0;
    private OnPriceChangeListener priceChangeListener;

    public AddToCartAdapter(List<FeatureProductModel> featureProductModelList, Context context, OnPriceChangeListener priceChangeListener) {
        this.featureProductModelList = featureProductModelList;
        this.context = context;
        this.priceChangeListener = priceChangeListener;
    }

    @NonNull
    @Override
    public AddToCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoAddtocartBinding binding = DemoAddtocartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartAdapter.ViewHolder holder, int position) {
        FeatureProductModel addtocartModel = featureProductModelList.get(position);

        holder.binding.productImage.setImageResource(addtocartModel.getImageProduct());
        holder.binding.productName.setText(addtocartModel.getProductName());
        holder.binding.productPrice.setText(String.valueOf(addtocartModel.getPrice()));
        holder.binding.productQuantity.setText(String.valueOf(addtocartModel.getProductNumber()));
        holder.binding.dozen.setText(addtocartModel.getQuantity());
        holder.binding.itemNumber.setText(String.valueOf(addtocartModel.getProductNumber()));

        int productquantity = featureProductModelList.get(position).getProductNumber();
        int productprice = featureProductModelList.get(position).getPrice();

        int itemprice = productprice * productquantity;
        subprice += itemprice;

        if (priceChangeListener != null) {
            priceChangeListener.onPriceChange(subprice);
        }

        holder.binding.minus.setOnClickListener(v -> {
            int quantity = addtocartModel.getProductNumber();
            if (quantity >= 2) {
                quantity--;
                holder.binding.itemNumber.setText(String.valueOf(quantity));
                addtocartModel.setProductNumber(quantity);
                holder.binding.productQuantity.setText(String.valueOf(quantity));

                subprice -= productprice;

                if (priceChangeListener != null) {
                    priceChangeListener.onPriceChange(subprice);
                }
            }
        });

        holder.binding.plus.setOnClickListener(v -> {
            int quantity = addtocartModel.getProductNumber();
            quantity++;
            holder.binding.itemNumber.setText(String.valueOf(quantity));
            addtocartModel.setProductNumber(quantity);
            holder.binding.productQuantity.setText(String.valueOf(quantity));

            subprice += productprice;
            if (priceChangeListener != null) {
                priceChangeListener.onPriceChange(subprice);
            }
        });
    }

    public interface OnPriceChangeListener {
        void onPriceChange(int newPrice);
    }

    @Override
    public int getItemCount() {
        return featureProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoAddtocartBinding binding;
        public ViewHolder(@NonNull DemoAddtocartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
