package com.example.flashpig.Model;

import java.util.List;

/**
 * Class for MemoryPairUpLogic.
 *
 * @author Madeleine
 * @version 2020-09-16
 */

public interface MemoryPairUpLogic {


        void isMatch(Card chosenCard1, Card chosenCard2, Deck deck);

        void ifMatch();
    }

   
