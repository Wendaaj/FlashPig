package com.example.flashpig.Model;

import java.util.EnumSet;

public class FlashcardProgress {
    private Deck deck;
    private EnumSet<Difficulties> enumSetEasy = EnumSet.of(Difficulties.EASY);
    private EnumSet<Difficulties> enumSetMedium = EnumSet.of(Difficulties.MEDIUM);
    private EnumSet<Difficulties> enumSetHard = EnumSet.of(Difficulties.HARD);

    public FlashcardProgress(Deck deck) {
        this.deck = deck;
    }

    private void showCards(Difficulties difficulties) {
        switch (difficulties) {
            case EASY:
            case MEDIUM:
            case HARD:
            default:
        }
    }

    private void moveCard(Cards card, Difficulties difficulty) {
        if (!card.getDifficulty().equals(difficulty)) {
            card.setDifficulty(difficulty);
        }
    }

    private void deleteCard(Cards card) {
        card.setDifficulty(Difficulties.NOTHING);
    }

}
