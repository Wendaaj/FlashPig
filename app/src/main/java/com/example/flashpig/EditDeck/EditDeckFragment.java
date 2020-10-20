package com.example.flashpig.EditDeck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashpig.DataBase.FakeDataBase;
import com.example.flashpig.Model.Card;
import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;
import com.example.flashpig.View.CreateDeckFragment;
import com.example.flashpig.ViewModel.CardViewModel;
import com.example.flashpig.ViewModel.DashboardViewModel;
import com.example.flashpig.ViewModel.EditDeckViewModel;
import com.google.android.material.textfield.TextInputLayout;

import org.parceler.Parcels;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class EditDeckFragment extends Fragment implements DeckRecyclerViewAdapter.ItemClickListener, DeckSpinnerAdapter.OnEditItemsClickListener {
    private DeckRecyclerViewAdapter deckRecyclerViewAdapter;
    private DeckSpinnerAdapter spinnerAdapter;
    private RecyclerView cardList;
    private TextView amountCards;
    private TextInputLayout deckName;
    private EditText deckNameEditText;
    private Spinner deckSpinner;
    private Button saveDeck, changeSideButton, yesBtn, noBtn, yesBtnDeck, noBtnDeck;
    private ImageButton addCardButton;
    private ConstraintLayout deleteCard, deleteDeck;
    private CheckBox checkBox, checkBoxDeck;
    private String deckNameInput;
    private DashboardViewModel viewModel;

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
        viewModel = new ViewModelProvider(getActivity()).get(DashboardViewModel.class);
        viewModel.setChosenDeck(Parcels.unwrap(getArguments().getParcelable("deck")));

        configSpinner();

        changeSideButton.setText(R.string.front_to_back);
        changeSideButton.setOnClickListener(v -> changeSideButton(viewModel.getChosenDeck().getValue()));

        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("deck", Parcels.wrap(viewModel.getChosenDeck().getValue()));
                NavHostFragment.findNavController(EditDeckFragment.this)
                        .navigate(R.id.action_editDeckFragment_to_cardFragment2, bundle);
            }
        });

        saveDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deckNameInput = deckName.getEditText().getText().toString();
                if (!deckNameInput.isEmpty()) {
                    viewModel.getChosenDeck().getValue().setDeckName(deckName.getEditText().getText().toString());
                    deckName.getEditText().getText().clear();
                } else{
                    Toast.makeText(getActivity(), "Please input deck name! OINK! OINK!",
                            Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(), viewModel.getChosenDeck().getValue().getDeckName() + " is saved", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putParcelable("deck", Parcels.wrap(viewModel.getChosenDeck().getValue()));
                NavHostFragment.findNavController(EditDeckFragment.this)
                        .navigate(R.id.action_editDeckFragment_to_mainActivity3, bundle);
            }
        });
    }

    private void configSpinner(){
        spinnerAdapter = new DeckSpinnerAdapter(getContext(), viewModel.getDecks().getValue(), this);
        deckSpinner.setAdapter(spinnerAdapter);
        deckSpinner.setSelection(viewModel.getChosenDeckPos());
        deckSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setChosenDeck((Deck) parent.getItemAtPosition(position));
                view.setBackgroundResource(R.drawable.card_background);
                notifySelected();
                setAmountTxt();
                deckNameEditText.setText(viewModel.getChosenDeck().getValue().getDeckName());
                spinnerAdapter.setEditBtnVisibility(view);
                cardListGrid(viewModel.getChosenDeck().getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void notifySelected() {
        viewModel.getChosenDeck().observe(getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                if (deck != null) {
                    Toast.makeText(getActivity(), deck.getDeckName() + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setAmountTxt() {
        if(viewModel.getChosenDeck().getValue().getAmountCards() == 0){
            amountCards.setText("No cards oink!");
        }else if(viewModel.getChosenDeck().getValue().getAmountCards()>1){
            amountCards.setText(Integer.toString(viewModel.getChosenDeck().getValue().getAmountCards()) + " cards");
        }else{
            amountCards.setText(Integer.toString(viewModel.getChosenDeck().getValue().getAmountCards()) + " card");
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
        deckSpinner = view.findViewById(R.id.chooseDeckSpinner);
        cardList = view.findViewById(R.id.cardRecyclerView);
        deckName = view.findViewById(R.id.deckNameLayout);
        amountCards = view.findViewById(R.id.ammountCardsTextView);
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
        deckNameEditText = view.findViewById(R.id.deckNameEditText);
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
        if (viewModel.getDecks().getValue().size() != 1){ //If not the last deck
            removeDeckCheckboxHandler(constraintLayout, deckName, amountCards, position);
            setSpinnerSelection(position);
        }else {
            viewModel.removeDeck(viewModel.getDecks().getValue().get(position)); //Remove the last deck before navigate to home page
            viewModel.setChosenDeck(null);
            NavHostFragment.findNavController(EditDeckFragment.this)
                    .navigate(R.id.action_editDeckFragment_to_FirstFragment);
        }
    }

    private void removeDeckCheckboxHandler(ConstraintLayout constraintLayout, TextView deckName, TextView amountCards, int position){
        loadPreferencesDeck();
        if (!checkBoxDeck.isChecked()) {
            deleteDeck.setVisibility(View.VISIBLE);
            spinnerAdapter.manageVisibility(constraintLayout, deckName, amountCards);
            hideSpinnerDropDown(deckSpinner);
            setYesNoDeckBtn(position);
            cardListGrid(viewModel.getChosenDeck().getValue());
        } else {
            spinnerAdapter.manageVisibility(constraintLayout, deckName, amountCards);
            viewModel.removeDeck(viewModel.getDecks().getValue().get(position));
            deleteDeck.setVisibility(View.INVISIBLE);
        }
        savePreferencesDeck();
    }

    private void setYesNoDeckBtn(int position) {
        yesBtnDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeDeck(viewModel.getDecks().getValue().get(position));
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

    private void setSpinnerSelection(int position){
        if((viewModel.getDecks().getValue().size()-1)==position){//size is 1 bigger because position 0 is included
            deckSpinner.setSelection(0);
        }else if(position == 0){
            deckSpinner.setSelection(position+1);
        }
        else{
            deckSpinner.setSelection(position-1);
        }
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

    @Override
    public void onEditItemBtnClick(Card card) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("deck", Parcels.wrap(viewModel.getChosenDeck().getValue()));
        bundle.putParcelable("card", Parcels.wrap(card));
        NavHostFragment.findNavController(EditDeckFragment.this)
                .navigate(R.id.action_editDeckFragment_to_cardFragment2, bundle);

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