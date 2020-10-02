package com.example.flashpig.EditDeck;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flashpig.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class editDeckFragment extends Fragment {
    private RecyclerView cardList;

    private TextView deckName;
    private TextView amountCards;

    private Spinner deckSpinner;

    private Button saveDeck;
    private Button frontSide;
    private Button addCardButton;


    public editDeckFragment() {
        // an empty constructor is required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editdeck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardList = view.findViewById(R.id.cardRecyclerView);
        deckName = view.findViewById(R.id.deckNameTextView);
        amountCards = view.findViewById(R.id.ammountCardsTextView);
        deckSpinner = view.findViewById(R.id.chooseDeckSpinner);
        saveDeck = view.findViewById(R.id.saveDeckButton);
        frontSide = view.findViewById(R.id.frontSideButton);
        addCardButton = view.findViewById(R.id.addButton);
    }

    public void onClick(View view){
        if(view.getId() == R.id.frontSideButton){

        }

    }


}