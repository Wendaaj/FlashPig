package com.example.flashpig.Model;

import android.media.Image;
import android.view.View;

import java.util.List;

public class Flashcard extends GameLogic {
    Deck deck;

    public Flashcard(String title, View viewGame, Deck deck, List<Cards> frontsideCards, List<Cards> backsideCards) {
        super(title, viewGame, deck, frontsideCards, backsideCards);
        this.deck = deck;
    }

    private void addEasyCard(Cards card) {
        card.setDifficulty(Difficulties.EASY);
    }

    private void addMediumCard(Cards card) {
        card.setDifficulty(Difficulties.MEDIUM);
    }

    private void addEasyCard(Cards card) {
        card.setDifficulty(Difficulties.HARD);
    }

    private void flipCard(Cards card) {
        card.getId().showBackside();
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
