package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivitySubFeatureProductBinding;
import com.one.groceryapp.databinding.DemoProductBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.ui.activity.DetailProductActivity;
import com.one.groceryapp.utils.Constants;

import java.util.List;

public class SubFeaturedProductAdapter extends RecyclerView.Adapter<SubFeaturedProductAdapter.ViewHolder>{

    List<FeatureProductModel> featureProductModelList;
    Context context;

    public SubFeaturedProductAdapter(List<FeatureProductModel> featureProductModelList, Context context) {
        this.featureProductModelList = featureProductModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubFeaturedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoProductBinding binding = DemoProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubFeaturedProductAdapter.ViewHolder holder, int position) {
        FeatureProductModel model = Constants.productModels.get(position);
        holder.binding.productImage.setImageResource(model.getImageProduct());
        holder.binding.price.setText(String.valueOf(model.getPrice()));
        holder.binding.productName.setText(model.getProductName());
        holder.binding.productQuantity.setText(model.getQuantity());
        holder.binding.itemNumber.setText(String.valueOf(model.getProductNumber()));


    }

    @Override
    public int getItemCount() {
        return featureProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoProductBinding binding;
        public ViewHolder(@NonNull DemoProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
