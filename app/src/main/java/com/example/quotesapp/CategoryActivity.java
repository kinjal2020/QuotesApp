package com.example.quotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    GridView gridView;
    List<String> itemsList = new ArrayList<>();
    List<Integer> colorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initBinding();

        itemsList.add("Alone");
        itemsList.add("Happy");
        itemsList.add("Sad");
        itemsList.add("Angry");
        itemsList.add("Anniversary");
        itemsList.add("Attitude");

        colorList.add(R.color.black);
        colorList.add(R.color.Red);
        colorList.add(R.color.yellow);
        colorList.add(R.color.pink);
        colorList.add(R.color.black);
        colorList.add(R.color.skyBlue);
//        colorList.add(R.color.Orange);
//        colorList.add(R.color.Green);


        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_grid_view, itemsList, colorList);
        gridView.setAdapter(customAdapter);


    }

    private void initBinding() {
        gridView = findViewById(R.id.grid_view);
//        gridView = findViewById(R.id.cardView);
    }


}