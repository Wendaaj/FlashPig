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
    }

    public void onClickFrontToBack(View view){
        frontSide.setText(R.string.back_side_text);
    }

}