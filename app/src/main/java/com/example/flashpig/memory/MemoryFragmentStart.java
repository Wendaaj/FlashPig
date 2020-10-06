package com.example.flashpig.memory;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Memory;
import com.example.flashpig.R;

//TODO disable scroll, max 3 card/board, don't duplicate card

public class MemoryFragmentStart extends Fragment implements memoryRecyclerViewAdapter.ItemClickListener {

    private TextView frontSideTextView;
    private TextView backSideTextView;
    private ImageView frontImageView;
    private ImageView backImageView;
    private AnimatorSet setRightOut;
    private AnimatorSet setLeftIn;
    Card card1, card2;
    private memoryRecyclerViewAdapter adapter;
    Deck deck = new Deck("hej", 0);
    Memory memory = new Memory("Zoris", this.deck);
    int position1;
    private RecyclerView recyclerView;
    private memoryRecyclerViewAdapter memoryRecyclerViewAdapter;

    public MemoryFragmentStart() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // populate the RecyclerView with cards
        deck.addCard(new Card(1,"front 1","back 1", null,
                null));
        deck.addCard(new Card(2,"front 1","back 1", null,
                null));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memory_fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);
        loadAnimations();

        // set up the RecyclerView
        recyclerView = view.findViewById(R.id.memoryCardRecyclerView);

        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new memoryRecyclerViewAdapter(getActivity(), deck.cards);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new GridSpacingCardDecoration(2, 30));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        memoryRecyclerViewAdapter = new memoryRecyclerViewAdapter(getContext(), deck.cards);
    }

    private void findView(View v) {
        frontSideTextView = v.findViewById(R.id.frontCardTextView);
        recyclerView = v.findViewById(R.id.memoryCardRecyclerView);
        backSideTextView = v.findViewById(R.id.backCardTextView);
        frontImageView = v.findViewById(R.id.frontCardImageView);
        backImageView = v.findViewById(R.id.backCardImageView);
    }

    private void loadAnimations() {
        setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.out_animation);
        setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.in_animation);
    }

    @Override
    public void onItemClick(View view, int position) throws InterruptedException {
        int cardsCount = adapter.getItemCount();
        while(cardsCount != 0) {
            if (card1 == null) {
                card1 = adapter.getItem(position);
                position1 = position;
                adapter.flip(view);
                card1.setFrontside(true);
                view.setClickable(false);
            } else {
                card2 = adapter.getItem(position);
                adapter.flip(view);
                memory.isMatched(card1, card2, deck);
                if (memory.isMatched(card1, card2, deck)) {
                    view.setClickable(false);
                    cardsCount -= 2;
                } else {
                    delay();
                    adapter.flip(view.findViewById(position1));
                }
            }
        }
    }

    private void delay() throws InterruptedException {
        //set 1 sec delay before turn when not a match
        Thread.sleep(1000);
    }


    private void endGame() {
        NavHostFragment.findNavController(MemoryFragmentStart.this)
                .navigate(R.id.action_startFragmentMemory_to_endFragmentMemory);
    }
}
