package com.example.flashpig.Model;

import android.media.Image;

import java.util.List;

public class GameLogic {
    String gameTitle;
    Deck deck;
    Cards chosenCard1, chosenCard2;
    List<Cards> frontsideCards, backsideCards;

    public GameLogic(String title, Deck deck, List<Cards> frontsideCards, List<Cards> backsideCards) {
        this.gameTitle = title;
        this.deck = deck;

        for (Cards card : deck.deck) {
            frontsideCards.add(card.getFrontside());
            backsideCards.add(card.getFrontside());
        }
    }

    private Image gameWon(int deckSize) {
        if (deckSize == 0) {
            return Image;
        }
    }

    private void restartGame(GameLogic game) {

    }

    private void returnHome(GameLogic game) {

    }

    private void magicAlgoritm() {

    }
}

