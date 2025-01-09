package com.one.groceryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoCategoryBinding;
import com.one.groceryapp.model.CategoriesModel;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {

    List<CategoriesModel> groceryAdapterList;
    Context context;

    public GroceryAdapter(List<CategoriesModel> groceryAdapterList, Context context) {
        this.groceryAdapterList = groceryAdapterList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroceryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoCategoryBinding demoCategoryBinding = DemoCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(demoCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryAdapter.ViewHolder holder, int position) {
        CategoriesModel categoriesModel = groceryAdapterList.get(position);
        holder.demoCategoryBinding.groceryImg.setImageResource(R.drawable.leaf);
        holder.demoCategoryBinding.groceryText.setText("Caterories");
    }

    @Override
    public int getItemCount() {
        return groceryAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private DemoCategoryBinding demoCategoryBinding;

        public ViewHolder(@NonNull DemoCategoryBinding demoCategoryBinding) {
            super(demoCategoryBinding.getRoot());
            this.demoCategoryBinding = demoCategoryBinding;

        }
    }
}
