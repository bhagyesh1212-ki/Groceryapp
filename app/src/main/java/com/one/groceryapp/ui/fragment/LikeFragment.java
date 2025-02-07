package com.one.groceryapp.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.FragmentLikeBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.ui.activity.MainActivity;
import com.one.groceryapp.ui.adapter.AddToCartAdapter;
import com.one.groceryapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class LikeFragment extends Fragment implements AddToCartAdapter.OnPriceChangeListener {

    FragmentLikeBinding binding;
    AddToCartAdapter adapter;
    List<FeatureProductModel> featureProductModelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLikeBinding.inflate(inflater, container, false);

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
        });

        for (int i = 0; i < Constants.productModels.size(); i++) {
            if (Constants.productModels.get(i).getIsliked()) {
                featureProductModelList.add(Constants.productModels.get(i));
            } else {
                featureProductModelList.remove(Constants.productModels.get(i));
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcv.setLayoutManager(linearLayoutManager);
        adapter = new AddToCartAdapter(featureProductModelList, getContext(), this);
        binding.rcv.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                featureProductModelList.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    Paint paint = new Paint();
                    paint.setColor(Color.parseColor("#EF574B")); // Custom color
                    RectF background = new RectF(itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    c.drawRect(background, paint);

                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.deletepng);
                    if (icon != null) {
                        // Resize the icon to 20x20
                        int targetWidth = 50; // Target width in pixels
                        int targetHeight = 70; // Target height in pixels
                        Bitmap scaledIcon = Bitmap.createScaledBitmap(icon, targetWidth, targetHeight, false);

                        // Get item dimensions
                        float itemHeight = itemView.getHeight();
                        float iconMargin = (itemHeight - targetHeight) / 2; // Center the icon vertically
                        float iconLeft = itemView.getRight() - iconMargin - targetWidth; // Position icon at the right
                        float iconTop = itemView.getTop() + iconMargin;

                        // Set the paint color to #EF574B
                        paint = new Paint();
                        paint.setColor(Color.parseColor("#EF574B")); // Custom color

                        // Draw the background behind the icon
                        background = new RectF(
                                itemView.getRight() - targetWidth - iconMargin * 2, // Add margin on the left
                                itemView.getTop(),
                                itemView.getRight(),
                                itemView.getBottom()
                        );

                        c.drawRect(background, paint);
                        // Draw the resized icon
                        c.drawBitmap(scaledIcon, iconLeft, iconTop, null);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(binding.rcv);


        return binding.getRoot();
    }

    @Override
    public void onPriceChange(int newPrice) {
    }

}