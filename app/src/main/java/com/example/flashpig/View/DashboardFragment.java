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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.flashpig.R;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.View.Adapters.DeckSpinnerAdapter;
import com.example.flashpig.ViewModel.DashboardViewModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jesper Bergquist, Wendy Pau.
 * @responsibility Handles the view for the dashboard/home page and navigate the user to other
 * views from here.
 * @version 2020-10-20
 */
public class DashboardFragment extends Fragment implements DeckSpinnerAdapter.OnEditItemsClickListener {
    private DeckSpinnerAdapter spinnerAdapter;
    private Spinner deckSpinner;
    private TextView ndecks;
    private DashboardViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(DashboardViewModel.class);
        configSpinner();
        updateViews();
        setBtnListeners(view);
    }

    /**
     * Sets the spinner and its selection to the last chosen deck.
     */
    private void configSpinner(){
        deckSpinner = getActivity().findViewById(R.id.chooseDeckSpinner);
        ndecks = getActivity().findViewById(R.id.ndeckstext);
        ArrayList<Object> objectList = new ArrayList<>();
        objectList.addAll(viewModel.getDecks().getValue());
        spinnerAdapter = new DeckSpinnerAdapter(getActivity(), objectList, this, viewModel);
        deckSpinner.setAdapter(spinnerAdapter);
    }

    /**
     * Updates the dashboards view on changes.
     */
    private void updateViews(){
        viewModel.getAmountDecks().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer amountDecks) {
                ndecks.setText(amountDecks + " decks");
            }
        });

        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.getChosenDeck().setValue(viewModel.getDeckAtPos(position));
                view.setBackgroundResource(R.drawable.card_background);
                Toast.makeText(getActivity(), viewModel.getDeckName() + " selected", Toast.LENGTH_SHORT).show();
                spinnerAdapter.setEditBtnVisibility(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Button listeners that will navigate the user to the respective thing.
     * @param view The dashboards view.
     */
    private void setBtnListeners(View view){
        view.findViewById(R.id.flashcard1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.getAmountDecks().getValue() != 0) {
                    Intent intent = new Intent(getActivity(), FlashcardActivity.class);
                    intent.putExtra("deck", Parcels.wrap(viewModel.getDeck()));
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "Please create a deck first! OINK!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.pairup1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.getAmountDecks().getValue() != 0) {
                    Intent intent = new Intent(getActivity(), PairUpActivity.class);
                    intent.putExtra("deck", Parcels.wrap(viewModel.getDeck()));
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "Please create a deck first! OINK!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.editDeckBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!viewModel.decksEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("deck", Parcels.wrap(viewModel.getDeck()));
                    NavHostFragment.findNavController(DashboardFragment.this)
                            .navigate(R.id.action_FirstFragment_to_editDeckFragment, bundle);
                }else {
                    Toast.makeText(getActivity(), "Please create a deck first! OINK!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.createCardButton11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewModel.getAmountDecks().getValue() != 0) {
                    NavHostFragment.findNavController(DashboardFragment.this)
                            .navigate(R.id.action_FirstFragment_to_createDeckFragment);
                }else {
                    Toast.makeText(getActivity(), "Please create a deck first! OINK!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.memory1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "This part is not unlocked OINK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Handles what happens if the user wants to delete a deck.
     * @param c
     * @param deckName The view that shows the decks' name.
     * @param amountCards The view that shows the amount of cards in the deck
     * @param pos The decks position in the spinner.
     */
    @Override
    public void onRemoveDeckBtnClick(ConstraintLayout c, TextView deckName, TextView amountCards, int pos) {
        if (viewModel.getDecks().getValue().size() != 1){ //If not the last deck
            viewModel.removeDeck(pos); //Remove the last deck
            spinnerAdapter.manageVisibility(c, deckName, amountCards);
            setSpinnerSelection(pos);
        }else {
            spinnerAdapter.manageVisibility(c, deckName, amountCards);
            viewModel.removeDeck(pos); //Remove the last deck
            spinnerAdapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "OINK! You have no decks left!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets the spinners selection when a deck is removed.
     * @param position The removed decks' position in spinner.
     */
    private void setSpinnerSelection(int position){
        if((viewModel.getDecks().getValue().size()-1)==position){//size is 1 bigger because position 0 is included
            deckSpinner.setSelection(0);
        }else if(position == 0){
            deckSpinner.setSelection(position+1);
        } else{
            deckSpinner.setSelection(position-1);
        }
    }
}