package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.DemoMyorderBinding;
import com.one.groceryapp.model.MyOrderModel;
import com.one.groceryapp.model.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<MyOrderModel> myOrderModelList;
    Context context;

    List<TimeLineModel> timeLineModelList = new ArrayList<>();

    public OrderAdapter(List<MyOrderModel> myOrderModelList, Context context) {
        this.myOrderModelList = myOrderModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoMyorderBinding binding = DemoMyorderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    private List<TimeLineModel> myOrderModelList() {
        timeLineModelList.add(new TimeLineModel("Order placed", "Oct 19 2021", R.drawable.timelineview_bg));
        timeLineModelList.add(new TimeLineModel("Order confirmed", "Oct 20 2021", R.drawable.timelineview_bg));
        timeLineModelList.add(new TimeLineModel("Order shipped", "Oct 20 2021", R.drawable.timelineview_bg));
        timeLineModelList.add(new TimeLineModel("Out for delivery", "pending", R.drawable.timelineview_grey));
        timeLineModelList.add(new TimeLineModel("Order delivered", "pending", R.drawable.timelineview_grey));
        return timeLineModelList;
    }

    TimeLineAdapter adapter = new TimeLineAdapter(myOrderModelList(), context);

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyOrderModel orderModel = myOrderModelList.get(position);
        holder.binding.item.setText(String.valueOf(orderModel.getItem()));
        holder.binding.price.setText(String.valueOf(orderModel.getPrice()));
        holder.binding.date.setText(orderModel.getDate());

        holder.binding.rcv.setAdapter(adapter);
        holder.binding.dropUp.setOnClickListener(v -> {
            if (holder.binding.rcv.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(holder.binding.main, new AutoTransition());
                holder.binding.rcv.setVisibility(View.GONE);
                holder.binding.dropUp.setImageResource(R.drawable.dropup);
            } else {
                TransitionManager.beginDelayedTransition(holder.binding.main, new AutoTransition());
                holder.binding.rcv.setVisibility(View.VISIBLE);
                holder.binding.dropUp.setImageResource(R.drawable.dropdown);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DemoMyorderBinding binding;

        public ViewHolder(@NonNull DemoMyorderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
