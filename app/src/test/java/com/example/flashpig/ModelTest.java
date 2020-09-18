package com.example.flashpig;

import com.example.flashpig.Model.*;

import org.junit.Assert;
import org.junit.Test;

public class ModelTest {

    @Test
    public void chooseEasy(){
        Deck deck = new Deck("Madematik",1);
        Card card = new Card(1,true,"Vad betyder bae på danska?",
                "Madde",null,null, Difficulties.NOTHING);
        Flashcard flashcard = new Flashcard(deck.getDeckName(),null, deck);

        flashcard.addEasyCard(card);
        Assert.assertEquals(Difficulties.EASY, card.getDifficulty());
    }
}
