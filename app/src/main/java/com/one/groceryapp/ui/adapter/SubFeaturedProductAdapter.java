package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoProductBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.utils.Constants;

import java.util.List;

public class SubFeaturedProductAdapter extends RecyclerView.Adapter<SubFeaturedProductAdapter.ViewHolder> {
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
        holder.binding.bgProduct.setBackgroundResource(model.getProductColor());

        if (model.getIsliked()) {
            holder.binding.like.setImageResource(R.drawable.heartfill);
        } else {
            holder.binding.like.setImageResource(R.drawable.heart);
        }

        holder.binding.like.setOnClickListener(v -> {
            model.setIsliked(!model.getIsliked());
            if (model.getIsliked()) {
                holder.binding.like.setImageResource(R.drawable.heartfill);
            } else {
                holder.binding.like.setImageResource(R.drawable.heart);
            }
        });

        if (model.getAddtocart()) {
            holder.binding.addtocart.setVisibility(View.GONE);
            holder.binding.itemCount.setVisibility(View.VISIBLE);
        } else {
            holder.binding.addtocart.setVisibility(View.VISIBLE);
            holder.binding.itemCount.setVisibility(View.GONE);
        }

        holder.binding.addtocart.setOnClickListener(v -> {
//            featureProductModelList.get(position).setAddtocart(true);
            Constants.productModels.get(position).setAddtocart(true);
            holder.binding.addtocart.setVisibility(View.GONE);
            holder.binding.itemCount.setVisibility(View.VISIBLE);
            notifyItemChanged(position);
        });

        holder.binding.minus.setOnClickListener(v -> {
            int quantity = model.getProductNumber();
            if (quantity >= 1) {
                quantity--;
                holder.binding.itemNumber.setText(String.valueOf(quantity));
                model.setProductNumber(quantity);
                if(quantity == 0){
                    Constants.productModels.get(position).setAddtocart(false);
                    holder.binding.addtocart.setVisibility(View.VISIBLE);
                    holder.binding.itemCount.setVisibility(View.GONE);
                    notifyItemChanged(position);
                }
            }
        });

        holder.binding.plus.setOnClickListener(v -> {
            int quantity = model.getProductNumber();
            quantity++;
            holder.binding.itemNumber.setText(String.valueOf(quantity));
            featureProductModelList.get(position).setProductNumber(quantity);
        });
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
