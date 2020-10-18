package com.example.flashpig.EditDeck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;

import org.parceler.Parcels;

import java.lang.reflect.Method;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class EditDeckFragment extends Fragment implements DeckRecyclerViewAdapter.ItemClickListener, DeckSpinnerAdapter.OnEditItemsClickListener {
    private DeckRecyclerViewAdapter deckRecyclerViewAdapter;
    private DeckSpinnerAdapter spinnerAdapter;
    private Deck deck;
    private RecyclerView cardList;
    private TextView deckName, amountCards;
    private Spinner deckSpinner;
    private Button saveDeck, changeSideButton, yesBtn, noBtn, yesBtnDeck, noBtnDeck;
    private ImageButton addCardButton;
    private ConstraintLayout deleteCard, deleteDeck;
    private CheckBox checkBox, checkBoxDeck;
    private FakeDataBase db = new FakeDataBase();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editdeck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        deck = Parcels.unwrap(getArguments().getParcelable("deck"));
        spinnerAdapter = new DeckSpinnerAdapter(getContext(), db.getDeckList(), this);
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setSelection(db.getDeckList().indexOf(deck));
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deck = (Deck) parent.getItemAtPosition(position);
                Toast.makeText(getContext(), deck.getDeckName() + " selected", Toast.LENGTH_SHORT).show();
                setAmountTxt();
                spinnerAdapter.setEditBtnVisibility(view);
                cardListGrid(deck);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        changeSideButton.setText(R.string.front_to_back);
        changeSideButton.setOnClickListener(v -> changeSideButton(deck));
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(EditDeckFragment.this)
                        .navigate(R.id.action_editDeckFragment_to_cardFragment);
            }
        });
    }

    public void setAmountTxt() {
        if(deck.getAmountCards() == 0){
            amountCards.setText("No cards oink!");
        }else if(deck.getAmountCards()>1){
            amountCards.setText(Integer.toString(deck.getAmountCards()) + " cards");
        }else{
            amountCards.setText(Integer.toString(deck.getAmountCards()) + " card");
        }
    }

    private void cardListGrid(Deck deck){
        cardList.addItemDecoration(new GridSpacingCardDecoration(2, 30));
        cardList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        deckRecyclerViewAdapter = new DeckRecyclerViewAdapter(getContext(), deck.cards);
        cardList.setAdapter(deckRecyclerViewAdapter);
        deckRecyclerViewAdapter.setClickListener(this);
    }
    private void changeSideButton(Deck deck) {
        if (deck.isFrontside) {
            deck.setIsFrontside(false);
            changeSideButton.setText(R.string.back_to_front);
        } else {
            deck.setIsFrontside(true);
            changeSideButton.setText(R.string.front_to_back);
        }
        deckRecyclerViewAdapter.notifyDataSetChanged();
    }
    private void findViews(View view) {
        cardList = view.findViewById(R.id.cardRecyclerView);
        deckName = view.findViewById(R.id.deckNameTextView);
        amountCards = view.findViewById(R.id.ammountCardsTextView);
        deckSpinner = view.findViewById(R.id.chooseDeckSpinner);
        saveDeck = view.findViewById(R.id.saveDeckButton);
        changeSideButton = view.findViewById(R.id.changeSideButton);
        addCardButton = view.findViewById(R.id.addButton);
        deleteCard = view.findViewById(R.id.deleteCard);
        checkBox = view.findViewById(R.id.checkBox1);
        yesBtn = view.findViewById(R.id.yesBtn);
        noBtn = view.findViewById(R.id.noBtn);
        yesBtnDeck = view.findViewById(R.id.yesBtnDeck);
        noBtnDeck = view.findViewById(R.id.noBtnDeck);
        checkBoxDeck  = view.findViewById(R.id.checkBoxDeck);
        deleteDeck = view.findViewById(R.id.deleteDeck);
    }
    public void savePreferencesCard(){
        SharedPreferences sharedPreferences =  getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state", checkBox.isChecked());
        editor.apply();
    }
    public void loadPreferencesCard(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("state", false);
        checkBox.setChecked(state);
    }
    public void savePreferencesDeck(){
        SharedPreferences sharedPreferences =  getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("state", checkBoxDeck.isChecked());
        editor.apply();
    }
    public void loadPreferencesDeck(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        boolean state = sharedPreferences.getBoolean("state", false);
        checkBoxDeck.setChecked(state);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onRemoveCardClick(Card card, List<Card> cardsList) {
        deleteCard.setVisibility(View.VISIBLE);
        if(!checkBox.isChecked()){
            setYesNoBtn(card, cardsList);
        }else{
            removeCard(card, cardsList);
            savePreferencesCard();
            loadPreferencesCard();
            deleteCard.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onRemoveDeckBtnClick(ConstraintLayout constraintLayout, TextView deckName, TextView amountCards, int position) {
        deleteDeck.setVisibility(View.VISIBLE);
        if(!checkBoxDeck.isChecked()){
            hideSpinnerDropDown(deckSpinner);
            spinnerAdapter.manageVisibility(constraintLayout, deckName, amountCards);
            setYesNoDeckBtn(position);
            cardListGrid(deck);
        }else{
            removeDeck(position);
            spinnerAdapter.manageVisibility(constraintLayout, deckName, amountCards);
            savePreferencesDeck();
            loadPreferencesDeck();
            deleteDeck.setVisibility(View.INVISIBLE);
        }

        if(db.getDeckList().size() == 0){
            NavHostFragment.findNavController(this)
                            .navigate(R.id.action_editDeckFragment_to_FirstFragment);
        }

        if((db.getDeckList().size()-1)==position){//size is 1 bigger because position 0 is included
            deckSpinner.setSelection(0);
        }else if(position == 0){
            deckSpinner.setSelection(position+1);
        }
        else{
            deckSpinner.setSelection(position-1);
        }
    }
    private void setYesNoDeckBtn(int position) {
            yesBtnDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDeck(position);
                deleteDeck.setVisibility(View.INVISIBLE);
            }
        });
        noBtnDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDeck.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void setYesNoBtn(Card card, List<Card> cardsList){
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCard(card, cardsList);
                deleteCard.setVisibility(View.INVISIBLE);
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCard.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void removeCard(Card card, List<Card> cardsList){
        cardsList.remove(card);
        deckRecyclerViewAdapter.notifyDataSetChanged();
        setAmountTxt();
    }
    private void removeDeck(int position){
        deckSpinner.setSelection(position);
        db.getDeckList().remove(deckSpinner.getSelectedItem());
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditItemBtnClick() {
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_editDeckFragment_to_cardFragment);

    }
    public static void hideSpinnerDropDown(Spinner spinner) {
        try {
            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
            method.setAccessible(true);
            method.invoke(spinner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}