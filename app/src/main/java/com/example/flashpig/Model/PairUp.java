package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

public class PairUp extends GameLogic implements MemoryPairUpLogic {

    private boolean match = false;

    public PairUp(String title, View viewGame, Deck deck, List<Cards> frontsideCards, List<Cards> backsideCards) {
        super(title, viewGame, deck, frontsideCards, backsideCards);
    }

    void isMatch(Cards chosenCard1, Cards chosenCard2) {
        if (chosenCard1 == chosenCard2){
            match = true;
        }
    }

    @Override
    Image gameWon(int deckSize) {
        return null;
    }

    @Override
    void restartGame(View v) {

    }

    @Override
    void returnHome(View v) {

    }

    @Override
    void magicAlgoritm() {

    }
}


