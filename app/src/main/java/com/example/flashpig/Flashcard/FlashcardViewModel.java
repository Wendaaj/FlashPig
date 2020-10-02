package com.example.flashpig.Flashcard;


import androidx.lifecycle.ViewModel;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Flashcard;

import java.util.Random;

public class FlashcardViewModel extends ViewModel {
    Random rand = new Random();
    Flashcard flashcard;

    public FlashcardViewModel() {
        Deck deck = fillDeck();
        flashcard = new Flashcard(deck.getDeckName(), deck);

    }

    private Deck fillDeck() {
        Deck deck = new Deck("Madematik", rand.nextInt());
        deck.addCard(new Card(rand.nextInt(),"Vad betyder bae på danska?",
                "Madde",null,null));
        deck.addCard(new Card(rand.nextInt(), "Efter vem uppkom namnet Madematik?",
                "SMÄQ",null,null));
        deck.addCard(new Card(rand.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
                "Man kan aldrig vara för smart.",null, null));
        deck.addCard(new Card(rand.nextInt(), "Kommer Smäq slakta tentorna?",
                "OM hon kommer", null,null));
        return deck;
    }
}
