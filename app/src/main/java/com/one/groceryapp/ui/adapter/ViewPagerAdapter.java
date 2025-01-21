package com.one.groceryapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.one.groceryapp.R;

public class ViewPagerAdapter extends PagerAdapter {
    Context mContext;
    public ViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.intro_first, collection, false);
        TextView welcome_txt = layout.findViewById(R.id.welcome_txt);
        ImageView imageView2 = layout.findViewById(R.id.imageView2);
        ImageView big_cart_txt = layout.findViewById(R.id.big_cart_txt);

        switch (position) {
            case 0:
                welcome_txt.setText("Welcome to");
                imageView2.setImageResource(R.drawable.introone);
                big_cart_txt.setImageResource(R.drawable.bigcart);
                big_cart_txt.setVisibility(View.VISIBLE);
                break;
            case 1:
                welcome_txt.setText("Buy Quality Dairy Products");
                imageView2.setImageResource(R.drawable.introtwo);
                big_cart_txt.setImageResource(R.drawable.bigcart);
                big_cart_txt.setVisibility(View.GONE);
                break;
            case 2:
                welcome_txt.setText("Buy Premium Quality Fruits");
                imageView2.setImageResource(R.drawable.introthree);
                big_cart_txt.setImageResource(R.drawable.bigcart);
                big_cart_txt.setVisibility(View.GONE);
                break;
            case 3:
                welcome_txt.setText("Get Discounts On All Products");
                imageView2.setImageResource(R.drawable.introfour);
                big_cart_txt.setImageResource(R.drawable.bigcart);
                big_cart_txt.setVisibility(View.GONE);
                break;
        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }
}
