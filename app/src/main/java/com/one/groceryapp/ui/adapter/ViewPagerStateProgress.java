package com.one.groceryapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.one.groceryapp.ui.fragment.AddressFragment;
import com.one.groceryapp.ui.fragment.DeliveryFragment;
import com.one.groceryapp.ui.fragment.PaymentFragment;

public class ViewPagerStateProgress extends FragmentStateAdapter {

    public ViewPagerStateProgress(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DeliveryFragment();
        } else if (position == 1) {
            return new AddressFragment();
        } else {
            return new PaymentFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        // Never allow swiping to switch between pages
//        return false;
//    }
//    public boolean onTouchEvent(MotionEvent event) {
//        // Never allow swiping to switch between pages
//        return false;
//    }

}
