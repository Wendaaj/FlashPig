package com.example.flashpig.Flashcard;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.R;

/**
 * The controller that connects the Flashcard model with the views.
 *
 * @author wendy
 * @version 2020-10-04
 */
public class StartFragment extends Fragment implements View.OnClickListener {

    private FlashcardViewModel viewModel;
    private TextView txtBack, txtFront, easyAmount, mediumAmount, hardAmount;
    private FrameLayout cardFront, cardBack;
    private Button btnEasy, btnMedium, btnHard;
    private AnimatorSet setRightOut, setLeftIn;
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
        findViews(view);
        loadAnimations();
        changeCameraDistance();

        viewModel = new ViewModelProvider(getActivity()).get(FlashcardViewModel.class);
        viewModel.init();
        cardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        viewModel.getBackTxt().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                setLeftIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        txtBack.setText(s);
                    }
                });
            }
        });

        viewModel.getFrontTxt().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtFront.setText(s);
            }
        });

        viewModel.getEasyAmount().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                easyAmount.setText(s);
            }
        });

        viewModel.getMediumAmount().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mediumAmount.setText(s);
            }
        });

        viewModel.getHardAmount().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                hardAmount.setText(s);
            }
        });

        btnEasy.setOnClickListener(this);
        btnMedium.setOnClickListener(this);
        btnHard.setOnClickListener(this);

        viewModel.getGameOver().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean gameOver) {
                if (gameOver){
                    NavHostFragment.findNavController(StartFragment.this)
                            .navigate(R.id.action_startFragment_to_endFragment);
                }
            }
        });
    }

    /**
     * Check which button is clicked and sets the card to the specific difficulty.
     *
     * @param v The clicked button view.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_easy:
                viewModel.setCardsDifficulty(Difficulty.EASY); break;
            case R.id.btn_medium:
                viewModel.setCardsDifficulty(Difficulty.MEDIUM); break;
            case R.id.btn_hard:
                viewModel.setCardsDifficulty(Difficulty.HARD); break;
        }
        flipCard();
    }

    /**
     * Flips the card.
     */
    private void flipCard() {
        if (!isBackVisible) {
            setRightOut.setTarget(cardFront);
            setLeftIn.setTarget(cardBack);
            setRightOut.start();
            setLeftIn.start();
            isBackVisible = true;
            btnEasy.setVisibility(View.VISIBLE);
            btnMedium.setVisibility(View.VISIBLE);
            btnHard.setVisibility(View.VISIBLE);
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

    /**
     * Connects the fragments attributes with the views components.
     *
     * @param view The respective view.
     */
    private void findViews(View view) {
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

    /**
     * Loads the animations for flip card.
     */
    private void loadAnimations() {
        setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.out_animation);
        setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getActivity(), R.animator.in_animation);
    }

    /**
     * Changes the camera distance.
     */
    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        cardFront.setCameraDistance(scale);
        cardBack.setCameraDistance(scale);
    }



}