package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.TimelineRowBinding;
import com.one.groceryapp.model.TimeLineModel;

import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {

    List<TimeLineModel> timeLineModelList;
    Context context;

    public TimeLineAdapter(List<TimeLineModel> timeLineModelList, Context context) {
        this.timeLineModelList = timeLineModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public TimeLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TimelineRowBinding binding = TimelineRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
//      binding.timeline.initLine(viewType);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineAdapter.ViewHolder holder, int position) {
        TimeLineModel timeLineModel = timeLineModelList.get(position);
        holder.binding.txtOrder.setText(timeLineModel.getOrderDeliveryStatus());
        holder.binding.txtOrderDate.setText(timeLineModel.getDate());
        holder.binding.timeLineRound.setBackgroundResource(timeLineModel.getImageRound());
        if (position == 0) {
            holder.binding.topLine.setVisibility(View.GONE);
        }
        if (position == timeLineModelList.size() - 1) {
            holder.binding.bottomLine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return timeLineModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TimelineRowBinding binding;

        public ViewHolder(@NonNull TimelineRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
