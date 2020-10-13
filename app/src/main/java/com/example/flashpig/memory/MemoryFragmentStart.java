package com.example.flashpig.memory;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

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

public class MemoryFragmentStart extends Fragment implements memoryRecyclerViewAdapter.ItemClickListener {

    private AnimatorSet setRightOut, setLeftIn;
    Card card1, card2;
    Deck deck = new Deck("Hej", 0);
    private memoryRecyclerViewAdapter adapter = new memoryRecyclerViewAdapter(getContext(), deck.cards);
    private memoryRecyclerViewAdapter2 adapter2 = new memoryRecyclerViewAdapter2(getContext(), deck.cards);
    PairUp pairup = new PairUp("Zorris", this.deck);
    int position1, position2;
    private RecyclerView recyclerView, recyclerView2;
    int cardsCount;
    int deckSize;
    int showingCards = adapter.getItemCount() * 2;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.memory_fragment_start, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        loadAnimations();

        int numberOfColumns = 1;

        //first rv
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new memoryRecyclerViewAdapter(getActivity(), deck.cards);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new GridSpacingCardDecoration(1, 30));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        //second rv
        recyclerView2.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter2 = new memoryRecyclerViewAdapter2(getActivity(), deck.cards);
        adapter2.setClickListener(this::onItemClick);
        recyclerView2.setAdapter(adapter2);

        recyclerView2.addItemDecoration(new GridSpacingCardDecoration(1, 30));
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 1));

        setFirstViews();
    }

    private void loadAnimations() {
        setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.out_animation);
        setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.in_animation);
    }
    //TODO notify second column, last row is wierd

    @Override
    public void onItemClick(View view, int position) throws InterruptedException {
        if (card1 == null) {
            card1 = adapter.getItem(position);
            view.setClickable(false);
            position1 = position;
            // adapter.flip(view, position); //set frontside on card1
            adapter2.setClickable(true);
        } else {
            card2 = adapter2.getItem(position);

            // adapter2.flip2(view, position); //set backside on card2
            if (pairup.isMatched(card1, card2, deck) && showingCards != 2) {
                showingCards -= 2;
                deckSize--;
                view.setClickable(false);
                view.setBackgroundResource(R.drawable.frame_correct); //CARD 2
                recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_correct); //CARD 1
                //adapter.getItemCount() -= 1;
            } else if (showingCards == 2) {
                recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_correct);
                view.setBackgroundResource(R.drawable.frame_correct);
                deckSize--;
                delay(view);
                if (pairup.isEndOfGame(deckSize)) {
                    endGame();
                }else {
                    loadNewCards();
                }
            }
            else {
                //adapter2.flip2(view.findViewById(position), position);
                //adapter.flip(view.findViewById(position1), position1);

                recyclerView.findViewHolderForAdapterPosition(position1).itemView.setClickable(true);
                recyclerView.findViewHolderForAdapterPosition(position1).itemView.setBackgroundResource(R.drawable.frame_incorrect);
                view.setBackgroundResource(R.drawable.frame_incorrect);
                delay(view);
            }
            card1 = null;
            adapter2.setClickable(false);
        }
    }

    private void loadNewCards() {
        int i = 3;
        while (i != 0) {
            deck.cards.remove(0);
            i--;
        }
        adapter2.setReload(true);
        adapter.notifyDataSetChanged();
        showingCards = 6;
    }

    void delay(View view) {
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

    private void findViews(View view) {

        recyclerView = view.findViewById(R.id.memoryCardRecyclerView);
        recyclerView2 = view.findViewById(R.id.memoryCardRecyclerView2);
    }

    private void setFirstViews() {

        int deckSize = deck.cards.size();

        for (int i = 0; i < deckSize; i++) {
            Card card = adapter.getItem(i);
            card.setFrontside(false);
            adapter.notifyDataSetChanged();
        }

        for (int j = 0; j < deckSize; j++) {
            Card card = adapter2.getItem(j);
            card.setFrontside(true);
            adapter2.notifyDataSetChanged();
        }
    }

    private void endGame() {
        NavHostFragment.findNavController(MemoryFragmentStart.this)
                .navigate(R.id.action_startFragmentMemory_to_endFragmentMemory);
    }
}

