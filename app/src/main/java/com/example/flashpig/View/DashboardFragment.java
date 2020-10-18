package com.example.flashpig.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.flashpig.EditDeck.DeckSpinnerAdapter;
import com.example.flashpig.FakeDataBase;
import com.example.flashpig.R;
import com.example.flashpig.Model.Deck;

import org.parceler.Parcels;


public class DashboardFragment extends Fragment implements DeckSpinnerAdapter.OnEditItemsClickListener {
    private DeckSpinnerAdapter spinnerAdapter;
    private Spinner deckSpinner;
    private Deck choosenDeck;
    private FakeDataBase db = FakeDataBase.getInstance();
    private TextView ndecks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deckSpinner = getActivity().findViewById(R.id.chooseDeckSpinner);
        ndecks = getActivity().findViewById(R.id.ndeckstext);
        spinnerAdapter = new DeckSpinnerAdapter(getActivity(), db.getDeckList(), this, choosenDeck);
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choosenDeck = (Deck) parent.getItemAtPosition(position);
                String deckName = choosenDeck.getDeckName();
                Toast.makeText(getActivity(), deckName + " selected", Toast.LENGTH_SHORT).show();
                spinnerAdapter.setEditBtnVisibility(view);
                if(db.getDeckList().size()==0){
                    ndecks.setText("No decks oink!");
                }else{
                    ndecks.setText(db.getDeckList().size()+" decks");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        view.findViewById(R.id.flashcard1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcelable wrappedDeck = Parcels.wrap(choosenDeck);
                Intent intent = new Intent(getActivity(), FlashcardActivity.class);
                intent.putExtra("deck", wrappedDeck);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.pairup1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PairUpActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.editDeckBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("deck", Parcels.wrap(choosenDeck));
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_FirstFragment_to_editDeckFragment, bundle);
            }
        });

        view.findViewById(R.id.createCardButton11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_FirstFragment_to_createDeckFragment);
            }
        });
    }



    @Override
    public void onRemoveDeckBtnClick(ConstraintLayout c, TextView t1, TextView t2, int pos) {

    }
}