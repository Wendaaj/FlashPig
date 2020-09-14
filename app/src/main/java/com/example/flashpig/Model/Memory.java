package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

public class Memory extends GameLogic implements MemoryPairUpLogic {
    private boolean matched = false;

    public Memory(String title, View viewGame, Deck deck, List<Cards> frontsideCards, List<Cards> backsideCards) {
        super(title, viewGame, deck, frontsideCards, backsideCards);
    }

    void isMatch() {    //onFingerPressed    //sätta value för faceUp / faceDown ist för två olika listor?
    int i;
    int j;
        for (i = 0; i < frontsideCards.size(); i++) {
            for (j = 0; j < backsideCards.size(); j++) {
                 if (frontsideCards.get(i) == backsideCards.get(i)) {
            }        matched = true;
                 }
        }
    }

    void flipCard(){
               


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
