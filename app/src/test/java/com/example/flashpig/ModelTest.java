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

    @Test
    public void canShowCardsAfterDifficulty() {
        Card card1 = new Card(rand.nextInt(),true, "Efter vem uppkom namnet Madematik?", "SMÄQ",null,null, Difficulties.EASY);
        Card card2 = new Card(rand.nextInt(),true, "Lever Smäq upp till sitt namn Madematik?", "Man kan aldrig vara för smart.",null,null, Difficulties.EASY);
        Card card3 = new Card(rand.nextInt(),true, "Kommer Smäq slakta tentorna?", "OM hon kommer",null,null, Difficulties.HARD);
        deck.addCard(card1,rand.nextInt());
        deck.addCard(card2,rand.nextInt());
        deck.addCard(card3,rand.nextInt());
        List<Card> actualList = new ArrayList<>();
        actualList.add(card1);
        actualList.add(card2);
        FlashcardProgress flashcardProgress = new FlashcardProgress(deck);
        List<Card> expectedList = flashcardProgress.showCards(Difficulties.EASY);
        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void isMatch() {
        Card chosenCard1 = new Card(2,true, "Vem är Zorri?",
                "Kungen", null, null, Difficulties.EASY);
        Card chosenCard2 = new Card(2,true, "Vem är Zorri?",
                "Kungen", null, null, Difficulties.EASY);
        Deck deck = new Deck("Legender", 2);

        if (chosenCard1.getId() == chosenCard2.getId()) {
            assert (true);
        }
    }

}
