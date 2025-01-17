package com.one.groceryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.SubDemoCetegoryBinding;
import com.one.groceryapp.model.CategoriesModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    List<CategoriesModel> subCetegoryModelList;
    Context context;

    public SubCategoryAdapter(List<CategoriesModel> subCetegoryModelList, Context context) {
        this.subCetegoryModelList = subCetegoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubDemoCetegoryBinding binding = SubDemoCetegoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.ViewHolder holder, int position) {

        CategoriesModel subCetegoryModel = subCetegoryModelList.get(position);
        holder.binding.productImage.setImageResource(subCetegoryModel.getCategoryimage());
        holder.binding.productName.setText(subCetegoryModel.getCategoryname());
        holder.binding.cetegoryBg.setCardBackgroundColor(ContextCompat.getColor(context,subCetegoryModel.getCategorycolor()));
    }

    @Override
    public int getItemCount() {
        return subCetegoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SubDemoCetegoryBinding binding;

        public ViewHolder(@NonNull SubDemoCetegoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
