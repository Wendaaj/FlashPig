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

import com.example.flashpig.EditDeck.DeckSpinnerAdapter;
import com.example.flashpig.R;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.ViewModel.DashboardViewModel;

import org.parceler.Parcels;


/**
 * Handles the view for the dashboard/home page and navigate the user to other views from here.
 *
 * @author Jesper Bergquist, Wendy Pau.
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
        spinnerAdapter = new DeckSpinnerAdapter(getActivity(), viewModel.getDecks().getValue(), this);
        deckSpinner.setAdapter(spinnerAdapter);
        if (viewModel.getChosenDeck().getValue()!= null){
            deckSpinner.setSelection(viewModel.getChosenDeckPos());
        }
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
                viewModel.setChosenDeck((Deck) parent.getItemAtPosition(position));
                view.setBackgroundResource(R.drawable.card_background);
                notifySelected();
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
                Parcelable wrappedDeck = Parcels.wrap(viewModel.getChosenDeck().getValue());
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
                if (!viewModel.getDecks().getValue().isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("deck", Parcels.wrap(viewModel.getChosenDeck().getValue()));
                    NavHostFragment.findNavController(DashboardFragment.this)
                            .navigate(R.id.action_FirstFragment_to_editDeckFragment, bundle);
                }else {
                    //Add toast
                }
            }
        });

        view.findViewById(R.id.createCardButton11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_FirstFragment_to_createDeckFragment);
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
     * Sends a toast if the chosen deck is changed.
     */
    private void notifySelected() {
        viewModel.getChosenDeck().observe(getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                Toast.makeText(getActivity(), deck.getDeckName() + " selected", Toast.LENGTH_SHORT).show();
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
            viewModel.removeDeck(viewModel.getDecks().getValue().get(pos)); //Remove the last deck
            spinnerAdapter.manageVisibility(c, deckName, amountCards);
            setSpinnerSelection(pos);
        }else {
            spinnerAdapter.manageVisibility(c, deckName, amountCards);
            viewModel.removeDeck(viewModel.getDecks().getValue().get(pos)); //Remove the last deck
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