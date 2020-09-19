package com.example.flashpig;


import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulties;
import com.example.flashpig.Model.Flashcard;
import com.example.flashpig.Model.FlashcardProgress;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModelTest {
    Random rand = new Random();
    Deck deck = new Deck("Madematik",1);

    @Test
    public void canChooseDifficulty(){
        Flashcard flashcard = new Flashcard("Madematik",null, deck);
        Card card = new Card(1,true,"Vad betyder bae på danska?",
                "Madde",null,null, Difficulties.NOTHING);
        flashcard.addEasyCard(card);
        Assert.assertEquals(card.getDifficulty(), Difficulties.EASY);

        flashcard.addMediumCard(card);
        Assert.assertEquals(card.getDifficulty(), Difficulties.MEDIUM);

        flashcard.addHardCard(card);
        Assert.assertEquals(card.getDifficulty(), Difficulties.HARD);
    }


}
