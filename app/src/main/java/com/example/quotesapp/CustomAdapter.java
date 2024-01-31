package com.example.quotesapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    CategoryActivity activity;
    String[][] items_list;
    String[] category_list;
    int[] color_list;
    List<Integer> emoji_list;
    int custom_layout_id;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull String[][] itemList, String[] categoryList, int[] colorList, List<Integer> emojiList, @NonNull CategoryActivity categoryActivity) {

        items_list = itemList;
        category_list = categoryList;
        emoji_list = emojiList;
        color_list = colorList;
        custom_layout_id = resource;
        activity = categoryActivity;

    }

    @Override
    public int getCount() {
        return category_list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = null;
        v = LayoutInflater.from(activity).inflate(R.layout.custom_grid_view, parent, false);
        ImageView imageView = v.findViewById(R.id.imageView);
        TextView textView = v.findViewById(R.id.textView);
        CardView cardView = v.findViewById(R.id.cardView);
        cardView.setCardBackgroundColor(color_list[position]);
        int emoji = emoji_list.get(position);
        String item = category_list[position];
        textView.setText(item);
        imageView.setBackgroundResource(emoji);
        imageView.setColorFilter(R.color.white);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", String.format("onClick: %s", items_list[1][0]));
                Intent intent = new Intent(activity, QuotesActivity.class);
                intent.putExtra("quoteList", items_list[position]);
                intent.putExtra("categoryName", category_list[position]);
                activity.startActivity(intent);
            }
        });

        return v;
    }

}
