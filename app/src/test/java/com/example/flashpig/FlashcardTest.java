package com.example.flashpig;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.Model.Flashcard;

import java.util.Random;

public class FlashcardTest {
    Random rand = new Random();
    Deck deck;
    Flashcard flashcard;

    @Before
    public void setup(){
        deck = new Deck("Madematik", rand.nextInt());
        Card card0 = new Card(rand.nextInt(),"Vad betyder bae på danska?",
                "Madde",null,null);
        Card card1 = new Card(rand.nextInt(), "Efter vem uppkom namnet Madematik?",
                "SMÄQ",null,null);
        Card card2 = new Card(rand.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
                "Man kan aldrig vara för smart.",null, null);
        Card card3 = new Card(rand.nextInt(), "Kommer Smäq slakta tentorna?",
                "OM hon kommer", null,null);
        Card card4 = new Card(rand.nextInt(), null, null, null,
                null);
        Card card5 = new Card(rand.nextInt(), "hej", "hej", null,
                null);
        deck.addCard(card0);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);
        flashcard = new Flashcard(deck);
    }

    @Test
    public void checkIfRoundIsOver(){
        Assert.assertFalse(flashcard.roundIsOver());
        flashcard.gameDeck.clear();
        Assert.assertTrue(flashcard.roundIsOver());
    }

    @Test
    public void canChooseDifficulty(){
        flashcard.addEasyCard(deck.cards.get(0));
        Assert.assertEquals(deck.cards.get(0).getDifficulty(), Difficulty.EASY);

        flashcard.addMediumCard(deck.cards.get(0));
        Assert.assertEquals(deck.cards.get(0).getDifficulty(), Difficulty.MEDIUM);

        flashcard.addHardCard(deck.cards.get(0));
        Assert.assertEquals(deck.cards.get(0).getDifficulty(), Difficulty.HARD);
    }

    @Test
    public void cardIsAddedBacKToMain(){
        Card card = new Card(rand.nextInt(), "front", "back", null, null);
        flashcard.addCardToMain(card);
        Assert.assertSame(card, flashcard.gameDeck.get(flashcard.gameDeck.indexOf(card)));
    }
}
