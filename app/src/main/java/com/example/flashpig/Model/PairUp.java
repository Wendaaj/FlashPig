package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

public class PairUp extends GameLogic implements MemoryPairUpLogic {

    private boolean match = false;

    public PairUp(String title, View viewGame, Deck deck, boolean match) {
        super(title, viewGame, deck);
        this.match = match;
    }

    @Override
    public void isMatch(Cards chosenCard1, Cards chosenCard2, Deck deck) {
        if (chosenCard1.getid == chosenCard2.getid) {
            deck.getAmountCards() - 1;
            ifMatch();
        }
    }

    @Override
    public void ifMatch() {
        if (deck.getAmountCards == 0) {
            gameWon(deck);
        }
    }

    @Override
    Image gameWon(Deck deck) {
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

    @Override
    public void isMatch(Cards chosenCard1, Cards chosenCard2, Deck deck) {

    }

    @Override
    public void ifMatch() {

    }
}


