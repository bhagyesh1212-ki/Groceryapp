package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.DemoAddtocartBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.utils.Constants;

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
        FeatureProductModel featureProductModel = featureProductModelList.get(position);
        int productquantity = featureProductModel.getProductNumber();
        int productprice = featureProductModel.getPrice();
        int itemprice = productprice * productquantity;

        subprice += itemprice;
        priceChangeListener.onPriceChange(subprice);

        holder.binding.minus.setOnClickListener(v -> {
            int quantity = featureProductModel.getProductNumber();
            if (quantity >= 2) {
                quantity--;
                holder.binding.itemNumber.setText(String.valueOf(quantity));
                featureProductModel.setProductNumber(quantity);
                holder.binding.productQuantity.setText(String.valueOf(quantity));
                subprice -= productprice;
                if (priceChangeListener != null) {
                    priceChangeListener.onPriceChange(subprice);
                }
            }
        });

        holder.binding.plus.setOnClickListener(v -> {
            int quantity = featureProductModel.getProductNumber();
            quantity++;
            holder.binding.itemNumber.setText(String.valueOf(quantity));
            featureProductModel.setProductNumber(quantity);

            holder.binding.productQuantity.setText(String.valueOf(quantity));
            subprice += productprice;
            if (priceChangeListener != null) {
                priceChangeListener.onPriceChange(subprice);
            }
        });
        holder.binding.productImage.setImageResource(featureProductModel.getImageProduct());
        holder.binding.productName.setText(featureProductModel.getProductName());
        holder.binding.productPrice.setText(String.valueOf(featureProductModel.getPrice()));
        holder.binding.productQuantity.setText(String.valueOf(featureProductModel.getProductNumber()));
        holder.binding.dozen.setText(featureProductModel.getQuantity());
        holder.binding.itemNumber.setText(String.valueOf(featureProductModel.getProductNumber()));
    }

    public void deleteItem(int position) {
        FeatureProductModel featureProductModel = featureProductModelList.remove(position);
        int removedItemPrice = featureProductModel.getPrice();
        int removedItemQuantity = featureProductModel.getProductNumber();
        int removedPrice = removedItemPrice * removedItemQuantity;

        featureProductModel.setAddtocart(false);
        featureProductModel.setProductNumber(1);

        subprice -= removedPrice;
        Log.d("TAG@@", "deleteItem: "+subprice);
        if (priceChangeListener != null) {
            priceChangeListener.onPriceChange(subprice);
        }
        notifyItemRemoved(position);
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
