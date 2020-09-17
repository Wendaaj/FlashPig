package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

public class Flashcard extends GameLogic {

    public Flashcard(String title, View viewGame, Deck deck, List<Card> frontsideCards, List<Card> backsideCards) {
        super(title, viewGame, deck, frontsideCards, backsideCards);
    }

    private void addEasyCard(Card card) {
        card.setDifficulty(Difficulties.EASY);
    }

    private void addMediumCard(Card card) {
        card.setDifficulty(Difficulties.MEDIUM);
    }

    private void addHardCard(Card card) {
        card.setDifficulty(Difficulties.HARD);
    }

    private void flipCard(Card card) {
        card.getId().showBackside();
    }

    @Override
    Image gameWon(Deck deck) {
        if (deck.size() == 0) {
            return null;
        } else {
            return null;
        }
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
