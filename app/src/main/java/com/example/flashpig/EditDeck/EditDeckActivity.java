package com.example.flashpig.EditDeck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;

import java.util.ArrayList;
import java.util.Random;

public class EditDeckActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView cardList;
    private TextView deckName, amountCards;
    private Spinner deckSpinner;
    private Button saveDeck, changeSideButton;
    private ImageButton addCardButton;
    private Random random = new Random();
    private ArrayList<Deck> deckArrayList;
    private DeckSpinnerAdapter spinnerAdapter;
    private ConstraintLayout constraint;
    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdeck);
        findViews();

        initSpinnerList();

        spinnerAdapter = new DeckSpinnerAdapter(this, deckArrayList);
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deck = (Deck) parent.getItemAtPosition(position);
                cardListGrid(deck);
                String deckName = deck.getDeckName();
                Toast.makeText(EditDeckActivity.this, deckName + " selected", Toast.LENGTH_SHORT).show();
                spinnerAdapter.setVisibility(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        changeSideButton.setText(R.string.front_to_back);
        changeSideButton.setOnClickListener(v -> changeSideButton(deck));

        //addCardButton.setOnClickListener(v-> addCardButton());
    }


    private void initSpinnerList(){
        deckArrayList = new ArrayList<>();
        Deck deck1 = new Deck("matte", random.nextInt());
        Deck deck2 = new Deck("svenska", random.nextInt());
        Deck deck3 = new Deck("engelska", random.nextInt());
        Deck deck4 = new Deck("fysik", random.nextInt());
        Deck deck5 = new Deck("OOP", random.nextInt());
        Deck deck6 = new Deck("spanska", random.nextInt());
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck1.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck2.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck3.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck3.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck4.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck5.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck6.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deckArrayList.add(deck1);
        deckArrayList.add(deck2);
        deckArrayList.add(deck3);
        deckArrayList.add(deck4);
        deckArrayList.add(deck5);
        deckArrayList.add(deck6);
    }

    private void cardListGrid(Deck deck){
        cardList.addItemDecoration(new GridSpacingCardDecoration(2, 30));
        cardList.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewAdapter = new RecyclerViewAdapter(this, deck.cards);
        cardList.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setClickListener(this);
    }

    private void changeSideButton(Deck deck) {
        if (deck.isFrontside) {
            deck.setIsFrontside(false);
            changeSideButton.setText(R.string.back_to_front);
        } else {
            deck.setIsFrontside(true);
            changeSideButton.setText(R.string.front_to_back);
        }
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void findViews() {
        cardList = findViewById(R.id.cardRecyclerView);
        deckName = findViewById(R.id.deckNameTextView);
        amountCards = findViewById(R.id.ammountCardsTextView);
        deckSpinner = findViewById(R.id.chooseDeckSpinner);
        saveDeck = findViewById(R.id.saveDeckButton);
        changeSideButton = findViewById(R.id.changeSideButton);
        addCardButton = findViewById(R.id.addButton);
    }

    @Override
    public void onItemClick(View view, int position) {
    }
}