package com.example.flashpig.Flashcard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.flashpig.R;

public class StartFragment extends Fragment implements View.OnClickListener {

    private FlashcardViewModel viewModel;
    private TextView titleCard, txtBack, txtFront;
    private ProgressBar progressBar;
    private CardView cardFront, cardBack;
    private Button btnEasy, btnMedium, btnHard;
    private int currentQuestion = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(FlashcardViewModel.class);
        findViews(view);
        loadUI();
        cardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardBack.setVisibility(View.VISIBLE);
                enableOptions(true);
                currentQuestion += 1;
            }
        });
        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnHard.setOnClickListener(this);
    }

    private void findViews(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        titleCard = view.findViewById(R.id.card_title);
        txtFront = view.findViewById(R.id.front_txt);
        txtBack = view.findViewById(R.id.back_txt);
        btnEasy = view.findViewById(R.id.btn_easy);
        btnMedium = view.findViewById(R.id.btn_medium);
        btnHard = view.findViewById(R.id.btn_hard);
        cardFront = view.findViewById(R.id.front_card);
        cardBack = view.findViewById(R.id.back_card);
    }

    private void loadUI() {
        titleCard.setText("Card nr. " + currentQuestion + 1);
        updateProgressBar();
        loadCard(currentQuestion);
    }

    private void loadCard(int i) {
        txtFront.setText(viewModel.flashcard.getDeck().cards.get(i).getFrontsideStr());
        txtBack.setText(viewModel.flashcard.getDeck().cards.get(i).getBacksideStr());
    }

    private void enableOptions(boolean bol) {
        if (bol) {
            btnEasy.setVisibility(View.VISIBLE);
            btnMedium.setVisibility(View.VISIBLE);
            btnHard.setVisibility(View.VISIBLE);
        }
        else {
            btnEasy.setVisibility(View.INVISIBLE);
            btnMedium.setVisibility(View.INVISIBLE);
            btnHard.setVisibility(View.INVISIBLE);
        }
    }

    private void updateProgressBar() {
        progressBar.setMax(viewModel.flashcard.getDeck().getAmountCards());
        progressBar.setProgress(currentQuestion);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_easy: viewModel.flashcard.addEasyCard(viewModel.flashcard.getDeck().cards.get(currentQuestion));
            case R.id.btn_medium: viewModel.flashcard.addMediumCard(viewModel.flashcard.getDeck().cards.get(currentQuestion));
            case R.id.btn_hard: viewModel.flashcard.addHardCard(viewModel.flashcard.getDeck().cards.get(currentQuestion));
        }
        enableOptions(false);
        cardBack.setVisibility(View.INVISIBLE);
        loadUI();
    }
}