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
import com.example.flashpig.Flashcard.FlashcardActivity;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.memory.MainActivity_memory;
import com.example.flashpig.pairup.activity_pairup;


public class FirstFragment extends Fragment {
    private DeckSpinnerAdapter spinnerAdapter;
    private Spinner deckSpinner;
    private Deck choosenDeck;
    private FakeDataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initSpinnerList();

        db = new FakeDataBase();

        deckSpinner = getActivity().findViewById(R.id.chooseDeckSpinner);
        spinnerAdapter = new DeckSpinnerAdapter(getActivity(), db.getDeckList());
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenDeck = (Deck) parent.getItemAtPosition(position);
                String deckName = choosenDeck.getDeckName();
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
}