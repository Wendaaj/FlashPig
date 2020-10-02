package com.example.flashpig.EditDeck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.flashpig.Model.Card;
import com.example.flashpig.R;

import java.util.ArrayList;
import java.util.List;

public class EditDeckActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdeck);

        // populate the RecyclerView with cards
        List<Card> cardsList = new ArrayList<>();
        cardsList.add(new Card(1,"hello","bye", null, null));
        cardsList.add(new Card(2, "hello", "bye", null, null));

        RecyclerView recyclerView = findViewById(R.id.cardRecyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new RecyclerViewAdapter(this, cardsList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    //in progress...
    @Override
    public void onItemClick(View view, int position) {
        //Log.i();
    }
}