package com.example.flashpig.EditDeck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;

import java.util.ArrayList;

public class DeckSpinnerAdapter extends ArrayAdapter<Deck> {
    ArrayList<Deck> deckArrayList;

    public DeckSpinnerAdapter(Context context, ArrayList<Deck> deckArrayList){
        super(context, 0, deckArrayList);
        this.deckArrayList = deckArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.editdeck_spinner_item, parent, false
            );
        }
        TextView deckName = convertView.findViewById(R.id.deckNameSpinner);
        TextView amountCards = convertView.findViewById(R.id.amountCardsSpinner);
        Button editSpinnerItemBtn = convertView.findViewById(R.id.spinnerEditButton);
        ConstraintLayout constraintLayout = convertView.findViewById(R.id.spinnerConstraintLayout);
        Button removeSpinnerItemBtn = convertView.findViewById(R.id.removeSpinnerItemBtn);



        editSpinnerItemBtn.setOnClickListener(v-> manageVisibility(constraintLayout, deckName, amountCards));
        removeSpinnerItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deckArrayList.remove(position);
                notifyDataSetChanged();
                manageVisibility(constraintLayout, deckName, amountCards);
            }
        });

        Deck currentDeck = getItem(position);
        if(currentDeck != null){
            deckName.setText(currentDeck.getDeckName());
            amountCards.setText(Integer.toString(currentDeck.getAmountCards()));
            if (position % 2 == 0) { // we're on an even row
                convertView.setBackgroundColor(0xBFEFFFFF);
            } else {
                convertView.setBackgroundColor(0x87CEEBFF);
            }
        }
        return convertView;
    }

    public void setVisibility(View view){
        Button editSpinnerItemBtn = view.findViewById(R.id.spinnerEditButton);
        TextView amountCard = view.findViewById(R.id.amountCardsSpinner);
        editSpinnerItemBtn.setVisibility(View.INVISIBLE);
        amountCard.setVisibility(View.INVISIBLE);

    }
    public void manageVisibility(View constraintLayout, View deckName, View amountCards){
        if(constraintLayout.getVisibility() == View.INVISIBLE){
            constraintLayout.setVisibility(View.VISIBLE);
            deckName.setVisibility(View.INVISIBLE);
            amountCards.setVisibility(View.INVISIBLE);
        }else{
            constraintLayout.setVisibility(View.INVISIBLE);
            deckName.setVisibility(View.VISIBLE);
            amountCards.setVisibility(View.VISIBLE);
        }

    }
    public void update(){

    }
}
