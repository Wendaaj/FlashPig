package com.example.flashpig.memory;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.Model.Memory;
import com.example.flashpig.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//MEMORY VIEW LOGIK HÄÄÄÄR

/*TODO
- vänd på korten och matcha
    - ta en knapp och vänd och sätt ist frontside på kortet på positio 0-3 och resten backSide
    kolla ismatch och så och JA håll de uppe OM Nej vänd tillbaka med delay

 */

public class MemoryFragmentStart extends Fragment implements View.OnClickListener {

    ArrayList<Card> cards = new ArrayList<>(7);
    ArrayList<ImageButton> buttons = new ArrayList<>(7);
    int selectedButton1;
    int selectedButton2;
    private int isMatched;
    private MemoryViewModel viewModel;
    private RecyclerView cardList;

    public MemoryFragmentStart() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memory_fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClick(view);
    }

    /*private void loadCardstoButtons() {

        //even int to front
        for (int i = 0; i < (cards.size() % 2); i++) {
            ImageButton temp = buttons.get(i);
            //temp.set

        }
        //odd int to back
        for (int i = 0; i < (cards.size() % 2); i++) {

            if (i % 2 != 0) {
                //buttons.set(cards.get(i).isFrontside());
            }
        }
        Collections.shuffle(cards);
    }*/

    @Override
    public void onClick(View v) {

        while (!buttons.isEmpty()) {
            int cardsLeft = buttons.size();


            if (selectedButton1 == selectedButton2) {
                //sparas det att de är matchade
                isMatched = -2;
                cardsLeft = cardsLeft - 1;
            }

            if (selectedButton1 == isMatched || selectedButton2 == isMatched) {
                //om de redan är matchade --> inget händer
                return;
            }

            if (selectedButton1 != selectedButton2) {
                //flip();
                delay();
                //flip();
            }
        }
    }

    private void delay() {
        //set delay before turn when not a match
    }

    public void flip(ImageButton button) {

    }

    private void endGame() {
        Intent intent = new Intent(getActivity(), MemoryFragmentEnd.class);
        startActivity(intent);
    }


}
