package com.example.quotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    List<String> items_list;
    List<Integer> color_list;
    int custom_layout_id;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<String> itemList,@NonNull List<Integer> colorList) {
        super(context, resource, itemList);
        items_list = itemList;
        color_list = colorList;
        custom_layout_id = resource;

    }

    @Override
    public int getCount() {
        return items_list.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            // getting reference to the main layout and initializing
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(custom_layout_id, null);
        }


//        ImageView imageView = v.findViewById(R.id.imageView);
        TextView textView = v.findViewById(R.id.textView);
        CardView cardView = v.findViewById(R.id.cardView);
        int color = color_list.get(position);
        cardView.setCardBackgroundColor(color);
        String item = items_list.get(position);

//        imageView.setImageResource(item);
        textView.setText(item);
        return v;
    }

}
