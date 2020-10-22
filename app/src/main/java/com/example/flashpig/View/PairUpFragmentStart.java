package com.example.flashpig.View;

import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.R;
import com.example.flashpig.View.Adapters.GridSpacingCardDecoration;
import com.example.flashpig.View.Adapters.PairUpRecyclerViewAdapter;
import com.example.flashpig.View.Adapters.PairUpRecyclerViewAdapter2;
import com.example.flashpig.ViewModel.PairUpViewModel;

import java.util.Collections;

/**
 * Represents the start screen of a Pair Up game
 *
 * @author Madeleine
 * @responsibility Observes the changes of the PairUp model by the PairUpViewmodel's LiveData
 * @version 2020-10-16
 */


public class PairUpFragmentStart extends Fragment implements PairUpRecyclerViewAdapter.ItemClickListener {

    private PairUpViewModel pairUpViewModel;
    private PairUpRecyclerViewAdapter adapter;
    private PairUpRecyclerViewAdapter2 adapter2;
    int position1, position2;
    private RecyclerView recyclerView, recyclerView2;
    private int decksize;

    /**
     * Does initial creations of the fragment
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        pairUpViewModel = new ViewModelProvider(getActivity()).get(PairUpViewModel.class);

        adapter = new PairUpRecyclerViewAdapter(getContext(), pairUpViewModel.getChosenDeck().getValue().cards);
        adapter2 = new PairUpRecyclerViewAdapter2(getContext(),pairUpViewModel.getChosenDeck().getValue().cards);
        decksize = pairUpViewModel.getDeckSize();

        int numberOfColumns = 1;

        //first rv
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new PairUpRecyclerViewAdapter(getActivity(), pairUpViewModel.getChosenDeck().getValue().cards);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new GridSpacingCardDecoration(1, 30));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //second rv
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter2 = new PairUpRecyclerViewAdapter2(getActivity(), pairUpViewModel.getChosenDeck().getValue().cards);
        adapter2.setClickListener(this::onItemClick);
        recyclerView2.setAdapter(adapter2);

        recyclerView2.addItemDecoration(new GridSpacingCardDecoration(1, 30));
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 1));

        setFirstViews();

        pairUpViewModel.getCard2().observe(getViewLifecycleOwner(), new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                pairUpViewModel.isPair();
            }
        });

        pairUpViewModel.getIsMatch().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isMatch) {
                View card2 = recyclerView2.findViewHolderForAdapterPosition(position2).itemView;
                if(isMatch){
                    setCorrectFrame(card2);
                    card2.setClickable(false);
                    //pairUpViewModel.setIsMatch(false);
                } else {
                    recyclerView.findViewHolderForAdapterPosition(position1).itemView.setClickable(true);
                    setIncorrectFrame(card2);
                    delay(card2);
                }
            }
        });

        pairUpViewModel.getIfLastPair().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean lastPair) {
                if(lastPair){
                    setCorrectFrame(view);
                    //decksize--;
                }
            }
        });

        pairUpViewModel.getLoadNewCards().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadNewCards) {
                if(loadNewCards){
                    adapter.notifyDataSetChanged();
                    adapter2.setReload(true);
                }
            }
        });

        pairUpViewModel.getSetFirstViews().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean setFirstView) {
                if(setFirstView){

                    adapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();

                    adapter2.setReload(true);
                }
            }
        });

        pairUpViewModel.isEndOfGame().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean endOfGame) {
                if(endOfGame){
                    endGame();
                }
            }
        });
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
        if (pairUpViewModel.getCard1().getValue() == null) { //Select first card
            pairUpViewModel.setCard1(adapter.getItem(position));
            //card1 = adapter.getItem(position);
            position1 = position;
            view.setClickable(false);
        } else { //Select second card
            position2 = position;
            pairUpViewModel.setCard2(adapter2.getItem(position));

            //card2 = adapter2.getItem(position);
        }
    }

    /**
     * Loads the cards to game board and shuffles the order of cards in the deck
     */
    private void setFirstViews() {
        for (int i = 0; i < pairUpViewModel.getChosenDeck().getValue().cards.size(); i++) {
            adapter.getItem(i).setFrontside(false);
            //adapter.notifyDataSetChanged();
            Collections.shuffle(pairUpViewModel.getChosenDeck().getValue().cards);
        }

        for (int j = 0; j < pairUpViewModel.getChosenDeck().getValue().cards.size(); j++) {
            adapter2.getItem(j).setFrontside(true);
            //adapter2.notifyDataSetChanged();
        }
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
     * Sets the incorrect frame on the view
     *
     * @param view the view chosen by the user
     */

    private void setIncorrectFrame(View view){
        recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_incorrect);
        view.setBackgroundResource(R.drawable.frame_incorrect);
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
                //Do something after 1s = 1000ms
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
     * Uploads the end fragment of the Pair Up game
     */

    private void endGame() {
        NavHostFragment.findNavController(PairUpFragmentStart.this)
                .navigate(R.id.action_pairUpFragmentStart_to_pairUpFragmentEnd);
    }
}
