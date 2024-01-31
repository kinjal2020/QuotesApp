package com.example.quotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.quotesapp.databinding.ActivityMainBinding;
import com.example.quotesapp.databinding.ActivityQuotesBinding;

public class QuotesActivity extends AppCompatActivity {

    ActivityQuotesBinding binding;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] quoteList = getIntent().getStringArrayExtra("quoteList");
        String categoryName = getIntent().getStringExtra("categoryName");
        binding.categoryName.setText(categoryName);
        QuotesAdapter adapter = new QuotesAdapter(this, quoteList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        binding.listView.setAdapter(adapter);
        binding.listView.setLayoutManager(lm);
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        binding.


    }
}