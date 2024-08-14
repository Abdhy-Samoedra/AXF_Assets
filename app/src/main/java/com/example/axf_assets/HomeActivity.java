package com.example.axf_assets;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends SpinnerLogic implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabPageAdapter tabPageAdapter;
    ViewPager carouselPager;
    TextView username;
    CarouselPagerAdapter carouselPagerAdapter;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int currentPosition = 0;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabPageAdapter = new TabPageAdapter(getSupportFragmentManager());
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        carouselPager = findViewById(R.id.carouselPager);
        username = findViewById(R.id.username_input);
        viewPager.setAdapter(tabPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // Retrieve username from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username_value = sharedPreferences.getString("USERNAME", "Guest");
        username.setText(username_value);
        Log.d("username", "Retrieved username: " + username_value);

        // Customize TabLayout
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                TextView tabTextView = new TextView(this);
                tabTextView.setText(tab.getText());
                tabTextView.setGravity(Gravity.CENTER);

                Typeface dmSansTypeface = ResourcesCompat.getFont(this, R.font.dm_sans);
                tabTextView.setTypeface(dmSansTypeface);
                tabTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tabTextView.setPadding(8, 4, 8, 4);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tabTextView.setLayoutParams(params);

                tab.setCustomView(tabTextView);
            }
        }

        tabLayout.addOnTabSelectedListener(this);

        // Set the first tab as selected
        TabLayout.Tab firstTab = tabLayout.getTabAt(0);
        if (firstTab != null && firstTab.getCustomView() != null) {
            TextView tabText = (TextView) firstTab.getCustomView();
            tabText.setTextColor(Color.WHITE);
            tabText.setBackgroundResource(R.drawable.tab_selected);
        }

        // Prepare carousel images
        int[] images = {R.drawable.carousel_1, R.drawable.carousel_2, R.drawable.carousel_3};
        List<Integer> imageList = new ArrayList<>();
        for (int image : images) {
            imageList.add(image);
        }

        // Set up carousel ViewPager
        carouselPagerAdapter = new CarouselPagerAdapter(this, imageList);
        carouselPager.setAdapter(carouselPagerAdapter);

        // Auto slide the carousel every 3000 milliseconds
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPosition == carouselPagerAdapter.getCount() - 1) {
                    currentPosition = 0;
                } else {
                    currentPosition++;
                }
                carouselPager.setCurrentItem(currentPosition, true);
                handler.postDelayed(this, 3000); // Slide every 3000 milliseconds
            }
        };

        handler.postDelayed(runnable, 3000); // Start sliding after 3000 milliseconds
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable); // Stop the handler when the activity is destroyed
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        TextView tabText = (TextView) tab.getCustomView();
        if (tabText != null) {
            tabText.setTextColor(Color.WHITE);
            tabText.setBackgroundResource(R.drawable.tab_selected);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        TextView tabText = (TextView) tab.getCustomView();
        if (tabText != null) {
            tabText.setTextColor(Color.BLACK);
            tabText.setBackgroundResource(0);
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // Optional: Handle reselection
    }
}
