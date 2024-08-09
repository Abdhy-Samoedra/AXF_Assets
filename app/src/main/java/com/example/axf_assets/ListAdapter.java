package com.example.axf_assets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {

    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.catalogue, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.catalogue, parent, false);
        }

        // Initialize views
        TextView listDesc = view.findViewById(R.id.listDesc);
        ImageView listImage = view.findViewById(R.id.listImage);
        ImageView listImage_banner = view.findViewById(R.id.banner_image);
        TextView listNumber = view.findViewById(R.id.listNumber);
        TextView listName = view.findViewById(R.id.listName);
        TextView listPrice = view.findViewById(R.id.listPrice);
        TextView detailButton = view.findViewById(R.id.detail_button);

        // Set data to views
        listImage.setImageResource(listData.detail_image);
        // listImage_banner.setImageResource(listData.banner_image); // Commented as it was not used
        listNumber.setText(listData.number);
        listName.setText(listData.name);
        listPrice.setText(listData.price);

        // Set truncated description
        String fullDesc = getContext().getString(listData.desc);
        String truncatedDesc = limitToThreeWords(fullDesc);
        listDesc.setText(truncatedDesc);

        // Set an OnClickListener for the detail button
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailedActivity.class);
                intent.putExtra("name", listData.name);
                intent.putExtra("price", listData.price);
                intent.putExtra("desc", fullDesc); // Send the full description
                intent.putExtra("image", listData.detail_image);
                intent.putExtra("imageBanner", listData.banner_image);
                intent.putExtra("trending" , listData.trending);
                getContext().startActivity(intent);
            }
        });

        // Override click effect by setting empty OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do nothing
            }
        });

        return view;
    }

    private String limitToThreeWords(String text) {
        String[] words = text.split("\\s+"); // Split by whitespace
        if (words.length > 3) {
            return words[0] + " " + words[1] + " " + words[2];
        } else {
            return text;
        }
    }
}



