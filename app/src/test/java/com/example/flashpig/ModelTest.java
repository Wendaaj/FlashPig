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
}
