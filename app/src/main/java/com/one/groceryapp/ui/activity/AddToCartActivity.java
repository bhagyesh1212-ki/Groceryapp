package com.one.groceryapp.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.one.groceryapp.databinding.ActivityAddToCartBinding;
import com.one.groceryapp.model.FeatureProductModel;
import com.one.groceryapp.ui.adapter.AddToCartAdapter;
import com.one.groceryapp.ui.fragment.HomeFragment;
import com.one.groceryapp.utils.Constants;

import java.util.ArrayList;

public class AddToCartActivity extends AppCompatActivity implements AddToCartAdapter.OnPriceChangeListener {

    ActivityAddToCartBinding binding;
    ArrayList<FeatureProductModel> modelArrayList = new ArrayList<>();
    AddToCartAdapter adapter;
    int shippingcharge;

    private OnCartItemDeletedListener mCartItemDeletedListener;

    public interface OnCartItemDeletedListener {
        void onCartItemDeleted();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddToCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.back.setOnClickListener(v -> {
            getOnBackPressedDispatcher().onBackPressed();
        });

//        // Set the listener for when an item is deleted from the cart
//        if (getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName()) != null) {
//            mCartItemDeletedListener = (OnCartItemDeletedListener) getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
//        }
//        modelArrayList = Arraylist.featureProductModelArrayList;
//        Arraylist.featureProductModelArrayList = Constants.featureProductModelArrayList();

        for (int i = 0; i < Constants.productModels.size(); i++) {
            if (Constants.arrayList.get(i).getAddtocart()) {
                modelArrayList.add(Constants.productModels.get(i));
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddToCartActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.rcv.setLayoutManager(linearLayoutManager);
        adapter = new AddToCartAdapter(modelArrayList, AddToCartActivity.this, this);

        binding.rcv.setAdapter(adapter);
        int itemCount = binding.rcv.getAdapter().getItemCount();

        shippingcharge = calculateShippingCharge(itemCount);
        binding.shippingCharge.setText(String.valueOf(shippingcharge));

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
////                deleteItem(position);
//            }
//
//            @Override
//            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
//                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
//                                    int actionState, boolean isCurrentlyActive) {
//                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//                    View itemView = viewHolder.itemView;
//                    Paint paint = new Paint();
//                    paint.setColor(Color.parseColor("#EF574B")); // Custom color
//                    RectF background = new RectF(itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//                    c.drawRect(background, paint);
//
//                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.deletepng);
//                    if (icon != null) {
//                        // Resize the icon to 20x20
//                        int targetWidth = 100; // Target width in pixels
//                        int targetHeight = 150; // Target height in pixels
//                        Bitmap scaledIcon = Bitmap.createScaledBitmap(icon, targetWidth, targetHeight, false);
//
//                        // Get item dimensions
//                        float itemHeight = itemView.getHeight();
//                        float iconMargin = (itemHeight - targetHeight) / 2; // Center the icon vertically
//                        float iconLeft = itemView.getRight() - iconMargin - targetWidth; // Position icon at the right
//                        float iconTop = itemView.getTop() + iconMargin;
//
//                        // Set the paint color to #EF574B
//                        paint = new Paint();
//                        paint.setColor(Color.parseColor("#EF574B")); // Custom color
//
//                        // Draw the background behind the icon
//                        background = new RectF(
//                                itemView.getRight() - targetWidth - iconMargin * 2, // Add margin on the left
//                                itemView.getTop(),
//                                itemView.getRight(),
//                                itemView.getBottom()
//                        );
//
//                        c.drawRect(background, paint);
//                        // Draw the resized icon
//                        c.drawBitmap(scaledIcon, iconLeft, iconTop, null);
//                        Log.e("AddToCartActivity", "Error:Delete icon resource could not be loaded.");
//                    }
//                }
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
//        });
//        itemTouchHelper.attachToRecyclerView(binding.rcv);
    }

    public void deleteItem(int position) {
        adapter.notifyItemRemoved(position);
        Constants.arrayList.remove(position);
        Constants.arrayList.get(position).setAddtocart(false);
    }


    private int calculateShippingCharge(int itemCount) {
        return itemCount * 1;
    }

    @Override
    public void onPriceChange(int newPrice) {
        Log.d("AddToCartActivity", "Updated Price: " + newPrice);
        runOnUiThread(() -> {
            binding.subTotal.setText(String.valueOf(newPrice));
            Log.d("AddToCartActivity", "SubTotal TextView updated: " + newPrice);
        });

        if (adapter.getItemCount() == 0) {
            binding.shippingCharge.setText("0");
            binding.totalPay.setText("0");
            Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
        } else {
            shippingcharge = calculateShippingCharge(adapter.getItemCount());
            int total = shippingcharge + newPrice;
            binding.shippingCharge.setText(String.valueOf(shippingcharge));
            binding.totalPay.setText(String.valueOf(total));
        }
    }
}




