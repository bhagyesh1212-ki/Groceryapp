package com.one.groceryapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.one.groceryapp.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class ViewPagerBannerAdapter extends PagerAdapter {

    Context context;
    DotsIndicator dot;

    public ViewPagerBannerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.demo_banner, container, false);
        dot = layout.findViewById(R.id.dot1);
        ImageView bannerimag = layout.findViewById(R.id.banner);

        switch (position) {
            case 0:
                bannerimag.setImageResource(R.drawable.banner);
                break;
            case 1:
                bannerimag.setImageResource(R.drawable.banner);
                break;
            case 2:
                bannerimag.setImageResource(R.drawable.banner);
                break;
            case 3:
                bannerimag.setImageResource(R.drawable.banner);
                break;
        }
        container.addView(layout);
        return layout;

    }

    @Override
    public void destroyItem(ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }
}
