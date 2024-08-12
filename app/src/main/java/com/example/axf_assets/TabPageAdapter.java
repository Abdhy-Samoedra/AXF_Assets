package com.example.axf_assets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class TabPageAdapter extends FragmentPagerAdapter {
    public TabPageAdapter(@NonNull FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position){
        if(position == 0){
            return new TermsFragment();
        }else{
            return new ConditionsFragment();
        }
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0) {
            return "Terms";
        }else{
            return "Conditions";
        }
    }
}