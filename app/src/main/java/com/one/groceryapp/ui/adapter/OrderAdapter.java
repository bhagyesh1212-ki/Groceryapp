package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

    private static final int UNSELECTED = -1;
    public static int selectedItem = UNSELECTED;

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
        timeLineModelList.add(new TimeLineModel("Order placed", "Oct 19 2021", R.drawable.timelineview_bg, R.color.color_primary_dark, R.color.color_primary_dark));
        timeLineModelList.add(new TimeLineModel("Order confirmed", "Oct 20 2021", R.drawable.timelineview_bg, R.color.color_primary_dark, R.color.color_primary_dark));
        timeLineModelList.add(new TimeLineModel("Order shipped", "Oct 20 2021", R.drawable.timelineview_bg, R.color.grey, R.color.color_primary_dark));
        timeLineModelList.add(new TimeLineModel("Out for delivery", "pending", R.drawable.timelineview_grey, R.color.grey, R.color.grey));
        timeLineModelList.add(new TimeLineModel("Order delivered", "pending", R.drawable.timelineview_grey, R.color.grey, R.color.grey));
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
        boolean isSelected = position == selectedItem;
        holder.binding.dropUp.setSelected(isSelected);
        holder.binding.expandableLayout.setExpanded(isSelected, false);

        holder.binding.mainLayout.setOnClickListener(v -> {

            holder.binding.dropUp.setSelected(false);
            holder.binding.dropUp.setImageResource(R.drawable.dropup);
            holder.binding.expandableLayout.collapse();

            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                holder.binding.dropUp.setSelected(true);
                holder.binding.expandableLayout.expand();
                holder.binding.dropUp.setImageResource(R.drawable.dropdown);
                selectedItem = position;
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
