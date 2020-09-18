package com.example.flashpig;


import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Difficulties;
import com.example.flashpig.Model.Flashcard;

import org.junit.Test;

public class ModelTest {

    @Test
    public void choosableDifficulty(){
        Deck deck = new Deck("Madematik",1);
        Card card = new Card(1,true,"Vad betyder bae på danska?",
                "Madde",null,null, Difficulties.NOTHING);
        Flashcard flashcard = new Flashcard("Madematik",null, deck);

        flashcard.
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
