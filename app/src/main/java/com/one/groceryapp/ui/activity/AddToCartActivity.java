package com.one.groceryapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.one.groceryapp.R;
import com.one.groceryapp.databinding.ActivityAddToCartBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.ui.adapter.AddToCartAdapter;
import com.one.groceryapp.ui.adapter.MyAddressAdapter;
import com.one.groceryapp.utils.Constants;

import java.util.ArrayList;

public class AddToCartActivity extends AppCompatActivity implements AddToCartAdapter.OnPriceChangeListener {

    ActivityAddToCartBinding binding;
    ArrayList<FeatureProductModel> modelArrayList = new ArrayList<>();
    MyAddressAdapter myAddressAdapter;
    AddToCartAdapter adapter;
    int total;
    int itemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddToCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        for (int i = 0; i < Constants.productModels.size(); i++) {
            if (Constants.productModels.get(i).getAddtocart()) {
                modelArrayList.add(Constants.productModels.get(i));
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddToCartActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.rcv.setLayoutManager(linearLayoutManager);
        adapter = new AddToCartAdapter(modelArrayList, AddToCartActivity.this, this);
        binding.rcv.setAdapter(adapter);

        itemCount = binding.rcv.getAdapter().getItemCount();
        binding.Checkout.setOnClickListener(v -> {
            if (total > 0) {
                Intent i = new Intent(this, PaymentActivity.class);
                i.putExtra("price", total);
                i.putExtra("itemquantity", itemCount);
                startActivity(i);
            } else {
                Toast.makeText(this, "please add item in cart", Toast.LENGTH_SHORT).show();
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(binding.rcv);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int position = viewHolder.getAdapterPosition();
            adapter.deleteItem(position);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onPriceChange(int newPrice) {
        if (adapter.getItemCount() == 0) {
            binding.shippingCharge.setText("$0");
            binding.totalPay.setText("$0");
            Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
        } else {
            binding.subTotal.setText("$" + newPrice);
            binding.shippingCharge.setText("$5");
            total = newPrice + 5;
            binding.totalPay.setText("$" + total);
        }
    }
}





