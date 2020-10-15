package com.example.flashpig.memory;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.flashpig.memory.MemoryViewModel;
import com.example.flashpig.Model.Card;
import com.example.flashpig.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*TODO
- vänd på korten och matcha
    - ta en knapp och vänd och sätt ist frontside på kortet på positio 0-3 och resten backSide
    kolla ismatch och så och JA håll de uppe OM Nej vänd tillbaka med delay
 */

public class MemoryFragmentStart extends Fragment implements View.OnClickListener {

    private int isMatched;
    private AnimatorSet setRightOut;
    private AnimatorSet setLeftIn;
    private boolean isBackVisible = false;
    private MemoryViewModel viewModel;
    private TextView titleCard, txtBack, txtFront;
    private ProgressBar progressBar;
    private FrameLayout cardFront, cardBack;
    private int currentQuestion = 0;
    memoryRecyclerViewAdapter rva;

    public MemoryFragmentStart() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memory_fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        loadUI();
        loadAnimations();
        changeCameraDistance();
        allCardBack();
        try {
            delay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
    }

    private void findViews(View view) {
        //titleCard = view.findViewById(R.id.card_title);
        txtFront = view.findViewById(R.id.front_txt);
        txtBack = view.findViewById(R.id.back_txt);
        cardFront = view.findViewById(R.id.front_card);
        cardBack = view.findViewById(R.id.back_card);
    }

    private void loadUI() {
    }

    private void loadCard(int i) {
        //txtFront.setText();
        //txtBack.setText();
    }

    private void loadAnimations() {
        setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.out_animation);
        setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.in_animation);
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        cardFront.setCameraDistance(scale);
        cardBack.setCameraDistance(scale);
    }

    public void allCardBack() {

    }

    public void flipCard() {
        if (!isBackVisible) {
            cardBack.setVisibility(View.VISIBLE);
            cardFront.setClickable(false);
            setRightOut.setTarget(cardFront);
            setLeftIn.setTarget(cardBack);
            setRightOut.start();
            setLeftIn.start();
            isBackVisible = true;

        } else {
            setRightOut.setTarget(cardBack);
            setLeftIn.setTarget(cardFront);
            setRightOut.start();
            setLeftIn.start();
            isBackVisible = false;
            cardFront.setClickable(true);
        }
    }

    @Override
    public void onClick(View v) {
        int i = 0;
        rva.getCardsList().get(i);
        flipCard();

    }

    private void delay() throws InterruptedException {
        //set delay before turn when not a match
        TimeUnit.SECONDS.sleep(10);
    }

    private void endGame() {
        Intent intent = new Intent(getActivity(), MemoryFragmentEnd.class);
        startActivity(intent);
    }

}
