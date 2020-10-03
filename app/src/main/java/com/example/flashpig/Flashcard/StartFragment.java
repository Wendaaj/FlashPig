package com.example.flashpig.Flashcard;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.R;

public class StartFragment extends Fragment implements View.OnClickListener {

    private FlashcardViewModel viewModel;
    private TextView titleCard, txtBack, txtFront, easyAmount, mediumAmount, hardAmount;
    private FrameLayout cardFront, cardBack;
    private Button btnEasy, btnMedium, btnHard;
    private AnimatorSet setRightOut;
    private AnimatorSet setLeftIn;
    private boolean isBackVisible = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(FlashcardViewModel.class);
        findViews(view);
        loadAnimations();
        changeCameraDistance();
        loadCard();
        cardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnHard.setOnClickListener(this);
    }

    private void loadUI() {

        if (!viewModel.flashcard.roundIsOver()) {
            loadCard();
        }else {
            NavHostFragment.findNavController(StartFragment.this)
                    .navigate(R.id.action_startFragment_to_endFragment);
        }
    }

    private void loadCard() {
        easyAmount.setText(Integer.toString(viewModel.flashcard.getAmountCards(Difficulty.EASY)));
        mediumAmount.setText(Integer.toString(viewModel.flashcard.getAmountCards(Difficulty.MEDIUM)));
        hardAmount.setText(Integer.toString(viewModel.flashcard.getAmountCards(Difficulty.HARD)));
        txtFront.setText(viewModel.flashcard.gameDeck.get(0).getFrontsideStr());
        txtBack.setText(viewModel.flashcard.gameDeck.get(0).getBacksideStr());
        titleCard.setText("Card nr. " + viewModel.flashcard.gameDeck.get(0).getId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_easy:
                viewModel.flashcard.addEasyCard(viewModel.flashcard.gameDeck.get(0));
                break;
            case R.id.btn_medium:
                viewModel.flashcard.addMediumCard(viewModel.flashcard.gameDeck.get(0));
                break;
            case R.id.btn_hard:
                viewModel.flashcard.addHardCard(viewModel.flashcard.gameDeck.get(0));
                break;
        }
        flipCard();
        loadUI();
    }

    public void flipCard() {
        if (!isBackVisible) {
            setRightOut.setTarget(cardFront);
            setLeftIn.setTarget(cardBack);
            setRightOut.start();
            setLeftIn.start();
            isBackVisible = true;
            btnEasy.setVisibility(View.VISIBLE);
            btnMedium.setVisibility(View.VISIBLE);
            btnHard.setVisibility(View.VISIBLE);
            cardBack.setVisibility(View.VISIBLE);
            cardFront.setClickable(false);
        } else {
            setRightOut.setTarget(cardBack);
            setLeftIn.setTarget(cardFront);
            setRightOut.start();
            setLeftIn.start();
            isBackVisible = false;
            btnEasy.setVisibility(View.INVISIBLE);
            btnMedium.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
            cardFront.setClickable(true);
        }
    }

    private void findViews(View view) {
        titleCard = view.findViewById(R.id.card_title);
        txtFront = view.findViewById(R.id.front_txt);
        txtBack = view.findViewById(R.id.back_txt);
        btnEasy = view.findViewById(R.id.btn_easy);
        btnMedium = view.findViewById(R.id.btn_medium);
        btnHard = view.findViewById(R.id.btn_hard);
        cardFront = view.findViewById(R.id.front_card);
        cardBack = view.findViewById(R.id.back_card);
        easyAmount = view.findViewById(R.id.amountEasy);
        mediumAmount = view.findViewById(R.id.amountMedium);
        hardAmount = view.findViewById(R.id.amountHard);
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
}