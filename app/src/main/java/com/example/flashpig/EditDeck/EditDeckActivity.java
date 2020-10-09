package com.example.flashpig.EditDeck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
    private Button saveDeck, changeSideButton, editSpinnerItemBtn, editSpinnerItemBtn2;
    private ImageButton addCardButton;
    private Random random = new Random();
    private ArrayList<Deck> deckArrayList;
    private DeckSpinnerAdapter spinnerAdapter;
    private ConstraintLayout constraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdeck);
        findViews();

        Deck deck = new Deck("Test", random.nextInt());
        initRecyclerList(deck);


        cardListGrid(deck);


        changeSideButton.setText(R.string.front_to_back);
        changeSideButton.setOnClickListener(v -> changeSideButton(deck));
        //addCardButton.setOnClickListener(v-> addCardButton());
        //editSpinnerItem.setOnClickListener(v -> editSpinnerItemButton());


        initSpinnerList();
        spinnerAdapter = new DeckSpinnerAdapter(this, deckArrayList);
        deckSpinner.setAdapter(spinnerAdapter);

        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Deck clickedDeck = (Deck) parent.getItemAtPosition(position);
                String deckName = clickedDeck.getDeckName();
                Toast.makeText(EditDeckActivity.this, deckName + " selected", Toast.LENGTH_SHORT).show();
                spinnerAdapter.setVisibility(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        editSpinnerItemBtn2.setOnClickListener(v->spinnerAdapter.setConstraintVisibility(v.findViewById(R.id.spinnerConstraintLayout)));

    }


    // private void editSpinnerItemButton() {
        //if()
   // }

    private void initSpinnerList(){
        deckArrayList = new ArrayList<>();

        deckArrayList.add(new Deck("matte", random.nextInt()));
        deckArrayList.add(new Deck("svenska", random.nextInt()));
        deckArrayList.add(new Deck("engelska", random.nextInt()));
        deckArrayList.add(new Deck("fysik", random.nextInt()));
        deckArrayList.add(new Deck("OOP", random.nextInt()));
        deckArrayList.add(new Deck("spanska", random.nextInt()));
    }
    private void initRecyclerList(Deck deck){
        deck.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
        deck.cards.add(new Card(random.nextInt(), "hello", "bye", null, null));
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
        editSpinnerItemBtn2 = findViewById(R.id.editSpinnerItemBtn2);
        constraint = findViewById(R.id.spinnerConstraintLayout);

    }

    @Override
    public void onItemClick(View view, int position) {
    }
}