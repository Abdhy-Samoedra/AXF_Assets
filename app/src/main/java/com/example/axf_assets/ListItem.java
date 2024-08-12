package com.example.axf_assets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListItem extends SpinnerLogic {
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_list_item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Find the ListView by its ID
        ListView listView = findViewById(R.id.listview);

        int[] imageList = {
                R.drawable.skins1, R.drawable.skins2, R.drawable.skins3,
                R.drawable.skins4, R.drawable.skins5, R.drawable.skins1,
                R.drawable.skins2, R.drawable.skins3, R.drawable.skins4
        };

        int[] imageBannerList = {
                R.drawable.bannerskin1, R.drawable.bannerskin2, R.drawable.bannerskin3,
                R.drawable.bannerskin4, R.drawable.bannerskin5, R.drawable.bannerskin1,
                R.drawable.bannerskin2, R.drawable.bannerskin3, R.drawable.bannerskin4
        };

        int[] descList = {
                R.string.desc1, R.string.desc2, R.string.desc3,
                R.string.desc4, R.string.desc5, R.string.desc6,
                R.string.desc7, R.string.desc8, R.string.desc9
        };

        String[] nameList = {
                "Blaze Fury", "Neon Pulse", "Shadow Serpent", "Arctic Blizzard",
                "Solar Flare", "Cyber Hex", "Emerald Dragon", "Phoenix", "Titan's"
        };

        String[] priceList = {
                "$600", "$700", "$800", "$900", "$2200",
                "$600", "$700", "$800", "$900"
        };

        String[] numberlist = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        String[] trendingList = {
                "Trending #1", "Trending #2", "Trending #3", "Trending #4",
                "Trending #5", "Trending #6", "Trending #7", "Trending #8", "Trending #9"
        };

        // Populate the ArrayList with ListData items
        for (int i = 0; i < imageList.length; i++) {
            listData = new ListData(nameList[i], descList[i], priceList[i], numberlist[i],
                    trendingList[i], imageBannerList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        // Set the adapter to the ListView
        listAdapter = new ListAdapter(ListItem.this, dataArrayList);
        listView.setAdapter(listAdapter);

        // Optionally, handle list item clicks
//        listView.setClickable(true);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ListItem.this, DetailedActivity.class);
//                intent.putExtra("name", nameList[i]);
//                intent.putExtra("price", priceList[i]);
//                intent.putExtra("desc", descList[i]);
//                intent.putExtra("image", imageList[i]);
//                intent.putExtra("imageBanner", imageBannerList[i]);
//                startActivity(intent);
//            }
//        });
    }
}
