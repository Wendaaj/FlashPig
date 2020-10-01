package com.example.flashpig.memory;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.flashpig.Flashcard.FlashcardViewModel;
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

public class MemoryFragmentStart extends Fragment implements View.OnClickListener{

    ArrayList<Card> cards = new ArrayList<>(7);
    ArrayList <ImageButton> buttons = new ArrayList<>(7);
    Card selectedCard1;
    Card selectedCard2;
    private boolean isBusy;
    Random rand;
    Deck deck;
    Card card;
    private MemoryViewModel viewModel;
    private TextView titleCard, txtBack, txtFront;
    private ProgressBar progressBar;
    private FrameLayout cardFront, cardBack;
    private Button btnEasy, btnMedium, btnHard;
    private int currentQuestion = 0;
    private AnimatorSet setRightOut;
    private AnimatorSet setLeftIn;
    private boolean isBackVisible = false;
    TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memory_fragment_start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onClick(view);

        ImageButton backButton;
        backButton = (ImageButton) view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        /*loadCards(deck);
        Collections.shuffle(cards);
        loadButtons();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setImageDrawable(getResources().getDrawable(R.drawable.pig));
        }*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton) {
            Intent intent = new Intent(getActivity(), MemoryFragmentStart.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.button1) {
            flipCard();
        }
    }

    public void flipCard() {


    }

    private ArrayList loadCards (Deck deck) {

        deck.addCard(new Card(rand.nextInt(),"Vad betyder bae på danska?",
                "Madde",null,null));
        deck.addCard(new Card(rand.nextInt(), "Efter vem uppkom namnet Madematik?",
                "SMÄQ",null,null));
        deck.addCard(new Card(rand.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
                "Man kan aldrig vara för smart.",null, null));
        deck.addCard(new Card(rand.nextInt(), "Kommer Smäq slakta tentorna?",
                "OM hon kommer", null,null));
        deck.addCard(new Card(rand.nextInt(),"Vad betyder bae på danska?",
                "Madde",null,null));
        deck.addCard(new Card(rand.nextInt(), "Efter vem uppkom namnet Madematik?",
                "SMÄQ",null,null));
        deck.addCard(new Card(rand.nextInt(), "Lever Smäq upp till sitt namn Madematik?",
                "Man kan aldrig vara för smart.",null, null));
        deck.addCard(new Card(rand.nextInt(), "Kommer Smäq slakta tentorna?",
                "OM hon kommer", null,null));

        return cards;
    }

    private ArrayList loadButtons () {

        ImageButton button1 = (ImageButton) getView().findViewById(R.id.button1);
        ImageButton button2 = (ImageButton) getView().findViewById(R.id.button2);
        ImageButton button3 = (ImageButton) getView().findViewById(R.id.button3);
        ImageButton button4 = (ImageButton) getView().findViewById(R.id.button4);
        ImageButton button5 = (ImageButton) getView().findViewById(R.id.button5);
        ImageButton button6 = (ImageButton) getView().findViewById(R.id.button6);
        ImageButton button7 = (ImageButton) getView().findViewById(R.id.button7);
        ImageButton button8 = (ImageButton) getView().findViewById(R.id.button8);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);

        return buttons;
    }


}