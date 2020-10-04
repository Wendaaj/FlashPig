package com.example.flashpig;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulty;
import com.example.flashpig.Model.Flashcard;

import java.util.Random;

public class FlashcardTest {
    Random rand = new Random();
    Deck deck = new Deck("Madematik", rand.nextInt());
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

    //Tests for Flashcard
    @Test
    public void canChooseDifficulty(){
        Flashcard flashcard = new Flashcard("Madematik", deck);
        flashcard.addEasyCard(card0);
        Assert.assertEquals(card0.getDifficulty(), Difficulty.EASY);

        flashcard.addMediumCard(card0);
        Assert.assertEquals(card0.getDifficulty(), Difficulty.MEDIUM);

        flashcard.addHardCard(card0);
        Assert.assertEquals(card0.getDifficulty(), Difficulty.HARD);
    }

    @Test
    public void canTurnOverCard() {
        Flashcard flashcard = new Flashcard("Madematik", deck);
        flashcard.turnOver(card3);
        Assert.assertEquals("OM hon kommer", card3.getBacksideStr());
    }
}
