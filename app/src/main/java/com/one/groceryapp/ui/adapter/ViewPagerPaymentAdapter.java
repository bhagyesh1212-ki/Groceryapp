//package com.one.groceryapp.ui.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.one.groceryapp.R;
//
//public class ViewPagerPaymentAdapter extends PagerAdapter {
//    Context context;
//
//    public ViewPagerPaymentAdapter(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
//        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        int resId = 0;
//        switch (position){
//            case 0:
//                resId = R.layout.shipping_delivery;
//                break;
//            case 1:
//                resId = R.layout.shipping_address;
//                break;
//            case 2:
//                resId = R.layout.shipping_payment;
//                break;
//        }
//
//        View view = inflater.inflate(resId, null);
//        container.addView(view, 0);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//}
