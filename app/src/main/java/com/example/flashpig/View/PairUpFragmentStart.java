package com.example.flashpig.View;

import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.PairUp;
import com.example.flashpig.R;
import com.example.flashpig.ViewModel.PairUpViewModel;

import java.util.Collections;

/**
 * Represents the start screen of a Pair Up game
 *
 * @author Madeleine
 * @version 2020-10-16
 */


public class PairUpFragmentStart extends Fragment implements PairUpRecyclerViewAdapter.ItemClickListener {

    Card card1, card2;
    Deck deck = new Deck("Hej", 0);
    private PairUpRecyclerViewAdapter adapter = new PairUpRecyclerViewAdapter(getContext(), deck.cards);
    private PairUpRecyclerViewAdapter2 adapter2 = new PairUpRecyclerViewAdapter2(getContext(), deck.cards);
    PairUp pairup = new PairUp("Zorris", this.deck);
    int position1;
    private RecyclerView recyclerView, recyclerView2;
    int deckSize;
    int showingCards = adapter.getItemCount() * 2;

    PairUpViewModel pairUpViewModel = new PairUpViewModel();


    /**
     * Does initial creations of the fragment
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // populate the RecyclerView with cards
        deck.addCard(new Card(1, "front 1", "back 1", null,
                null));
        deck.addCard(new Card(2, "front 2", "back 2", null,
                null));
        deck.addCard(new Card(3, "front 3", "back 3", null,
                null));
        deck.addCard(new Card(4, "front 4", "back 4", null,
                null));
        deck.addCard(new Card(5, "front 5", "back 5", null,
                null));
        deck.addCard(new Card(6, "front 6", "back 6", null,
                null));
        deckSize = deck.getAmountCards();
    }

    /**
     * Creates and returns the fragment's view hierarchy
     *
     * @param inflater Used to inflate views in the fragment
     * @param container Generates the LayoutParams of the view
     * @param savedInstanceState Save instance state
     * @return The view which represents the end of a Pair Up game
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pairup_fragment_start, container, false);
        return view;
    }

    /**
     *  Subclasses initialize themselves after their view hierarchy has been created
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, bundle)
     * @param savedInstanceState Saved instance state
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        int numberOfColumns = 1;

        //first rv
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new PairUpRecyclerViewAdapter(getActivity(), deck.cards);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new GridSpacingCardDecoration(1, 30));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //second rv
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter2 = new PairUpRecyclerViewAdapter2(getActivity(), deck.cards);
        adapter2.setClickListener(this::onItemClick);
        recyclerView2.setAdapter(adapter2);

        recyclerView2.addItemDecoration(new GridSpacingCardDecoration(1, 30));
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 1));

        setFirstViews();
    }

    /**
     * Compares the chosen cards and checks if it is a match and updates the UI according to the results
     *
     * @param view the view of the card chosen by the user
     * @param position the position of the card that the user chooses
     * @throws InterruptedException
     */

    @Override
    public void onItemClick(View view, int position) throws InterruptedException {

        if (card1 == null) { //Select first card

            card1 = adapter.getItem(position);

            position1 = position;

            view.setClickable(false);

        } else { //Select second card

            card2 = adapter2.getItem(position);

            if (pairup.isMatched(card1, card2, deck) && showingCards != 2) { //If pair and not the last pair

                updateAmountCards();

                view.setClickable(false); //this

                setCorrectFrame(view);

            } else if (showingCards == 2) { //If it is last pair

                setCorrectFrame(view);

                deckSize--;

                if (pairup.isEndOfGame(deckSize)) { //If it's game done

                    endGame();

                }else { //Load next cards

                    delay(view);

                    loadNewCards();
                }
            }
            else { //If not a pair

                recyclerView.findViewHolderForAdapterPosition(position1).itemView.setClickable(true);

                setIncorrectFrame(view);

                delay(view);
            }
            card1 = null; //Reset first card
        }
    }

    /**
     * Sets the incorrect frame on the view
     *
     * @param view the view chosen by the user
     */

    private void setIncorrectFrame(View view){
        recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_incorrect);
        view.setBackgroundResource(R.drawable.frame_incorrect);
    }

    /**
     * Sets the correct frame on the view
     *
     * @param view the view chosen by the user
     */

    private void setCorrectFrame(View view){
        view.setBackgroundResource(R.drawable.frame_correct);
        recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_correct);
    }

    /**
     * Updates the amount of cards showing on the game board and the amount of car in the deck
     */

    private void updateAmountCards(){
        showingCards -= 2;
        deckSize--;
    }

    /**
     * Updates the game board with new cards
     */

    private void loadNewCards() {
        int i = 3;
        while (i != 0) {
            deck.cards.remove(0);
            i--;
        }
        adapter.notifyDataSetChanged();
        adapter2.setReload(true);
        showingCards = 6;
    }

    /**
     * Sets a 1 second delay before setting the default frame on the view
     * @param view the view chosen by the user
     */

    private void delay(View view) {
        //Delay 1 sec before changing back.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1s = 1000ms
                recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_default);
                view.setBackgroundResource(R.drawable.frame_default);
            }
        }, 500);
    }

    /**
     * Connects the fragment's attributes with the view's components
     *
     * @param view the view chosen by the user
     */

    private void findViews(View view) {

        recyclerView = view.findViewById(R.id.memoryCardRecyclerView);
        recyclerView2 = view.findViewById(R.id.memoryCardRecyclerView2);
    }

    /**
     * Loads the cards to game board and shuffles the order of cards in the deck
     */

    private void setFirstViews() {

        int deckSize = deck.cards.size();

        for (int i = 0; i < deckSize; i++) {
            Card card = adapter.getItem(i);
            card.setFrontside(false);
            adapter.notifyDataSetChanged();
            Collections.shuffle(deck.cards);

        }

        for (int j = 0; j < deckSize; j++) {
            Card card = adapter2.getItem(j);
            card.setFrontside(true);
            adapter2.notifyDataSetChanged();
        }
    }

    /**
     * Uploads the end fragment of the Pair Up game
     */

    private void endGame() {
        NavHostFragment.findNavController(PairUpFragmentStart.this)
                .navigate(R.id.action_pairUpFragmentStart_to_pairUpFragmentEnd);
    }
}
