package com.example.flashpig;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Memory;
import com.example.flashpig.Model.PairUp;

import org.junit.Assert;
import org.junit.Test;

public class ModelMemoryPairUpTest {

    Card chosenCard1 = new Card(1, "Vem är Zorri?",
            "Kungen", null, null);

    Card chosenCard2 = new Card(2, "Vem är Zorri?",
            "Kungen", null, null);

    Deck deck = new Deck("Legender", 2);

    Memory memory = new Memory("GameOfTheGame", deck);

    PairUp pairUp = new PairUp("FunGame", deck);


    @Test
    public void testFlipCardFrontUp() {

        chosenCard1.setFrontside(false);

        memory.flipCard(chosenCard1);

        Assert.assertTrue(chosenCard1.isFrontside());

    }

    @Test
    public void testFlipCardFrontDown() {

        chosenCard1.setFrontside(true);

        memory.flipCard(chosenCard1);

        Assert.assertFalse(chosenCard1.isFrontside());

    }
    @Test
    public void whenMatchedPositive() {


        boolean isGameWon = memory.gameWon(deck);

        memory.isEndOfGame(deck.getAmountCards());

        Assert.assertTrue(isGameWon);
    }

    @Test
    public void whenMatchedNegative() {

        deck.addCard(chosenCard1); // deckSize = 1

        memory.isEndOfGame(deck.getAmountCards());

        boolean isGameWon = memory.gameWon(deck);

        Assert.assertFalse(isGameWon);

    }

    @Test
    public void memoryIsMatchPositive() {

        deck.addCard(chosenCard1);
        deck.addCard(chosenCard2);

        chosenCard1.setBackID(1);
        chosenCard2.setBackID(1);

        memory.isMatched(chosenCard1, chosenCard2, deck);

        Assert.assertTrue(memory.isMatched(chosenCard1, chosenCard2,deck));
}

    @Test
    public void memoryIsMatchNegative() {

        deck.addCard(chosenCard1);
        deck.addCard(chosenCard2); // 2 cards in deck

        chosenCard1.setBackID(2);
        chosenCard2.setBackID(77);

        memory.isMatched(chosenCard1, chosenCard2, deck);

        Assert.assertFalse(memory.isMatched(chosenCard1, chosenCard2,deck));
    }

    @Test
    public void pairUpIsMatchPositive() {

        deck.addCard(chosenCard1);
        deck.addCard(chosenCard2);

        chosenCard1.setBackID(1);
        chosenCard2.setBackID(1);

        pairUp.isMatched(chosenCard1, chosenCard2, deck);

        Assert.assertTrue(pairUp.isMatched(chosenCard1, chosenCard2,deck));
    }

   @Test
    public void pairUpIsMatchNegative() {

       deck.addCard(chosenCard1);
       deck.addCard(chosenCard2); // 2 cards in deck

       chosenCard1.setBackID(2);
       chosenCard2.setBackID(77);

       pairUp.isMatched(chosenCard1, chosenCard2, deck);

       Assert.assertFalse(pairUp.isMatched(chosenCard1, chosenCard2,deck));
    }

}