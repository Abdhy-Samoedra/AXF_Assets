package com.example.axf_assets;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends SpinnerLogic implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabPageAdapter tabPageAdapter;
    ViewFlipper carousel;
    TextView username;

//
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabPageAdapter = new TabPageAdapter(getSupportFragmentManager());
        carousel = findViewById(R.id.carousellab);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        username = findViewById(R.id.username_input);
        viewPager.setAdapter(tabPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        this is for assign username input
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username_value = sharedPreferences.getString("USERNAME", "Guest");
        username.setText(username_value);
        Log.d("username", "Retrieved username: " + username_value);


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                TextView tabTextView = new TextView(this);
                tabTextView.setText(tab.getText());
                tabTextView.setGravity(Gravity.CENTER);

                Typeface dmSansTypeface = ResourcesCompat.getFont(this, R.font.dm_sans);
                tabTextView.setTypeface(dmSansTypeface);
                // Adjust font size
                tabTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); // Smaller font size

                // Remove default padding and set custom padding
                tabTextView.setPadding(8, 4, 8, 4); // Adjust padding (left, top, right, bottom)

                // Set layout parameters to control margins and positioning
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tabTextView.setLayoutParams(params);

                tab.setCustomView(tabTextView);
            }
        }

        tabLayout.addOnTabSelectedListener(this);

        // Manually set the first tab as selected to adjust its text color and background
        TabLayout.Tab firstTab = tabLayout.getTabAt(0);
        if (firstTab != null && firstTab.getCustomView() != null) {
            TextView tabText = (TextView) firstTab.getCustomView();
            tabText.setTextColor(Color.WHITE);
            tabText.setBackgroundResource(R.drawable.tab_selected); // Set background for selected tab
        }

        int[] images = {R.drawable.carousel_1, R.drawable.carousel_2, R.drawable.carousel_3};

        for (int image: images
        ) {
            slideImage(image);
        }
    }

    private void slideImage(int image){
        ImageView imageView = new ImageView(HomeActivity.this);
        imageView.setBackgroundResource(image);
        carousel.addView(imageView);
        carousel.setAutoStart(true);
        carousel.setFlipInterval(3000);
        carousel.setInAnimation(HomeActivity.this, R.anim.slide_in_right);
        carousel.setOutAnimation(HomeActivity.this, R.anim.slide_out_left);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        TextView tabText = (TextView) tab.getCustomView();
        if (tabText != null) {
            tabText.setTextColor(Color.WHITE);
            tabText.setBackgroundResource(R.drawable.tab_selected); // Adjust this to your drawable
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
