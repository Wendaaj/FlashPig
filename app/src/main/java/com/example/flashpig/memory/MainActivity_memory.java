package com.example.flashpig.memory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.R;

import android.os.Bundle;
import android.view.View;


public class MainActivity_memory extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        // populate the RecyclerView with cards
        List<Card> cardsList = new ArrayList<>();
        cardsList.add(new Card());
        cardsList.add(new Card());

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.memoryCardRecyclerView);

        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new memoryRecyclerViewAdapter(this, cardsList);
        recyclerView.setAdapter(adapter);


        toolbar = findViewById(R.id.toolbarMemory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
