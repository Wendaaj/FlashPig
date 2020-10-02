package com.example.flashpig.EditDeck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EditDeckActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;
    private RecyclerView cardList;
    private TextView deckName, amountCards;
    private Spinner deckSpinner;
    private Button saveDeck, frontSideBtn;
    private ImageButton addCardButton;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdeck);
        findViews();
        frontSideBtn.setText(R.string.front_to_back);

        // populate the RecyclerView with cards
        Deck deck = new Deck("Zorrisen", random.nextInt());
        deck.cards.add(new Card(1,"hello","bye", null, null));
        deck.cards.add(new Card(2, "hello", "bye", null, null));
        deck.cards.add(new Card(3, "hello", "bye", null, null));
        deck.cards.add(new Card(4, "hello", "bye", null, null));
        deck.cards.add(new Card(5, "hello", "bye", null, null));
        deck.cards.add(new Card(6, "hello", "bye", null, null));
        deck.cards.add(new Card(7, "hello", "bye", null, null));
        deck.cards.add(new Card(8, "hello", "bye", null, null));

        RecyclerView recyclerView = findViewById(R.id.cardRecyclerView);
        int numberOfColumns = 2;
        recyclerView.addItemDecoration(new GridSpacingCardDecoration(numberOfColumns, 30, true));
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new RecyclerViewAdapter(this, deck.cards);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        frontSideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deck.isFrontside){
                    deck.setIsFrontside(false);
                    frontSideBtn.setText(R.string.back_to_front);
                } else{
                    deck.setIsFrontside(true);
                    frontSideBtn.setText(R.string.front_to_back);
                }

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void findViews() {
        cardList = findViewById(R.id.cardRecyclerView);
        deckName = findViewById(R.id.deckNameTextView);
        amountCards = findViewById(R.id.ammountCardsTextView);
        deckSpinner = findViewById(R.id.chooseDeckSpinner);
        saveDeck = findViewById(R.id.saveDeckButton);
        frontSideBtn = findViewById(R.id.frontSideButton);
        addCardButton = findViewById(R.id.addButton);
    }

    //in progress...
    @Override
    public void onItemClick(View view, int position) {
        //Log.i();
    }
}