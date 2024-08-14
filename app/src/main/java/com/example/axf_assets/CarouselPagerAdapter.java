package com.example.axf_assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class CarouselPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> images;

    public CarouselPagerAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE; // This creates an "infinite" loop by pretending to have many pages
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.carousel_item, container, false);

        ImageView imageView = view.findViewById(R.id.carouselImage);
        int realPosition = position % images.size(); // This calculates the actual image position
        imageView.setImageResource(images.get(realPosition));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
