package com.example.flashpig.EditDeck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class EditDeckActivity extends Fragment implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView cardList;
    private TextView deckName, amountCards;
    private Spinner deckSpinner;
    private Button saveDeck, changeSideButton, yesBtn, noBtn;
    private ImageButton addCardButton;
    private Random random = new Random();
    private ArrayList<Deck> deckArrayList;
    private DeckSpinnerAdapter spinnerAdapter;
    private Deck deck;
    private ConstraintLayout deleteCard;
    private CheckBox checkBox;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editdeck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        populate();


        spinnerAdapter = new DeckSpinnerAdapter(getContext(), deckArrayList, this);
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deck = (Deck) parent.getItemAtPosition(position);
                cardListGrid(deck);
                Toast.makeText(getContext(), deck.getDeckName() + " selected", Toast.LENGTH_SHORT).show();
                spinnerAdapter.setVisibility(view);
                setAmountTxt();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        changeSideButton.setText(R.string.front_to_back);
        changeSideButton.setOnClickListener(v -> changeSideButton(deck));
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(EditDeckActivity.this)
                        .navigate(R.id.action_editDeckActivity_to_cardFragment);
            }
        });
    }

    public void setAmountTxt() {
        if(deck.getAmountCards()>1){
            amountCards.setText(Integer.toString(deck.getAmountCards()) + " cards");
        }else{
            amountCards.setText(Integer.toString(deck.getAmountCards()) + " card");
        }
    }

    private void populate(){
        deckArrayList = new ArrayList<>();
        /*
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
         */
    }

    private void cardListGrid(Deck deck){
        cardList.addItemDecoration(new GridSpacingCardDecoration(2, 30));
        cardList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), this, deck.cards);
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

    private void findViews(View view) {
        cardList = view.findViewById(R.id.cardRecyclerView);
        deckName = view.findViewById(R.id.deckNameTextView);
        amountCards = view.findViewById(R.id.ammountCardsTextView);
        deckSpinner = view.findViewById(R.id.chooseDeckSpinner);
        saveDeck = view.findViewById(R.id.saveDeckButton);
        changeSideButton = view.findViewById(R.id.changeSideButton);
        addCardButton = view.findViewById(R.id.addButton);
        deleteCard = view.findViewById(R.id.deleteCard);
        checkBox = view.findViewById(R.id.checkBox1);
        yesBtn = view.findViewById(R.id.yesBtn);
        noBtn = view.findViewById(R.id.noBtn);
    }
    public void checkboxCardDelete(){
        deleteCard.setVisibility(View.VISIBLE);

        if (!checkBox.isChecked()) {
            deleteCard();
        } else {
            //onBackPressed();
        }
    }
    public void deleteCard(){
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();

            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCard.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void savePreferences(){
        SharedPreferences sharedPreferences =  getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state", checkBox.isChecked());
        editor.apply();
    }
    public void loadPreferences(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("state", false);
        checkBox.setChecked(state);
    }

    public Button getYesBtn() {
        return yesBtn;
    }

    public Button getNoBtn() {
        return noBtn;
    }

    public ConstraintLayout getDeleteCard() {
        return deleteCard;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public Spinner getDeckSpinner() {
        return deckSpinner;
    }

    @Override
    public void onItemClick(View view, int position) {
    }
}