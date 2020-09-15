package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

public class Memory extends GameLogic implements MemoryPairUpLogic {
    Deck deck;

    public Memory(String title, View viewGame, Deck deck, List<Cards> frontsideCards, List<Cards> backsideCards) {
        super(title, viewGame, deck, frontsideCards, backsideCards);
        this.deck = deck;
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
            gameWon(0);
        }
    }

    private void flipCard(Cards chosenCard1){
       chosenCard1.getIsFront() = !chosenCard1.getIsFront();
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
