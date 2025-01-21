package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.databinding.DemoCategoryBinding;
import com.one.groceryapp.model.CategoriesModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoriesModel> categoriesModelList;
    Context context;

    public CategoryAdapter(List<CategoriesModel> categoriesModelList, Context context) {
        this.categoriesModelList = categoriesModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoCategoryBinding demoCategoryBinding = DemoCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(demoCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoriesModel categoriesModel = categoriesModelList.get(position);
        holder.demoCategoryBinding.categoryImage.setImageResource(categoriesModel.getCategoryimage());
        holder.demoCategoryBinding.categoryText.setText(categoriesModel.getCategoryname());
        holder.demoCategoryBinding.categoryBg.setCardBackgroundColor(ContextCompat.getColor(context,categoriesModel.getCategorycolor()));

    }

    @Override
    public int getItemCount() {
        return categoriesModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private DemoCategoryBinding demoCategoryBinding;

        public ViewHolder(@NonNull DemoCategoryBinding demoCategoryBinding) {
            super(demoCategoryBinding.getRoot());
            this.demoCategoryBinding = demoCategoryBinding;
        }
    }
}
