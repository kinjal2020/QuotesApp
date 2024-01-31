package com.example.quotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    GridView gridView;

    int[] colorList = {
            0xFF00AEE7,
            0xFFF70039,
            0xFFF7C500,
            0xFFAA1A1B,
            0xFFBE72BA,
            0xFFF08BAD,
            0xFFF65C5C,
            0xFFFF618C,
            0xFF4AD562,
            0xFF4E1AAB,
    };
    List<Integer> emojiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initBinding();
        QuotesRepository repository = new QuotesRepository();

        String[][] quoteList = {repository.aloneQuotes, repository.happyQuotes, repository.sadQuotes, repository.angryQuotes, repository.anniversaryQuotes, repository.attitudeQuotes, repository.awesomeQuotes, repository.beardQuotes, repository.beautifulQuotes, repository.bestQuotes};
//        ArrayAdapter adapter=new ArrayAdapter(this,);


        emojiList.add(R.drawable.alone);
        emojiList.add(R.drawable.happy);
        emojiList.add(R.drawable.sad);
        emojiList.add(R.drawable.angry);
        emojiList.add(R.drawable.party);
        emojiList.add(R.drawable.attitude);
        emojiList.add(R.drawable.attitude);
        emojiList.add(R.drawable.attitude);
        emojiList.add(R.drawable.attitude);
        emojiList.add(R.drawable.attitude);


        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_grid_view, quoteList, repository.categoryList, colorList, emojiList, CategoryActivity.this);
        gridView.setAdapter(customAdapter);

    }

    private void initBinding() {
        gridView = findViewById(R.id.grid_view);
//        gridView = findViewById(R.id.cardView);
    }


}