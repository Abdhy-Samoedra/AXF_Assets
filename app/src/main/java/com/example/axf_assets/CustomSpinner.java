package com.example.axf_assets;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomSpinner extends BaseAdapter {

    private Context context;
    private String[] items;
    private int[] images;
    private LayoutInflater inflater;

    public CustomSpinner(Context context, String[] items, int[] images) {
        this.context = context;
        this.items = items;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item_list, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.spinnerImage);
            holder.textView = convertView.findViewById(R.id.spinnerText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(images[position]);
        holder.textView.setText(items[position]);

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
