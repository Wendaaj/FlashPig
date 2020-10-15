package com.example.flashpig;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.flashpig.EditDeck.DeckSpinnerAdapter;
import com.example.flashpig.EditDeck.EditDeckActivity;
import com.example.flashpig.Flashcard.FlashcardActivity;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.memory.MainActivity_memory;
import com.example.flashpig.pairup.activity_pairup;

import java.util.ArrayList;
import java.util.Random;


public class FirstFragment extends Fragment {
    ArrayList<Deck> deckArrayList;
    private Random random = new Random();
    private DeckSpinnerAdapter spinnerAdapter;
    private Spinner deckSpinner;
    private Deck deck;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState


    ) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSpinnerList();
        deckSpinner = getActivity().findViewById(R.id.chooseDeckSpinner);
        spinnerAdapter = new DeckSpinnerAdapter(getActivity(), deckArrayList);
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deck = (Deck) parent.getItemAtPosition(position);

                String deckName = deck.getDeckName();
                Toast.makeText(getActivity(), deckName + " selected", Toast.LENGTH_SHORT).show();
                spinnerAdapter.setVisibility(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        view.findViewById(R.id.flashcard1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FlashcardActivity.class);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.memory1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity_memory.class);
                startActivity(intent);

            }
        });

        view.findViewById(R.id.pairup1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_pairup.class);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.editDeckBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_editDeckActivity2);
            }
        });
        view.findViewById(R.id.createCardButton11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_createDeckFragment);
            }
        });
    }

    private void initSpinnerList() {
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


}