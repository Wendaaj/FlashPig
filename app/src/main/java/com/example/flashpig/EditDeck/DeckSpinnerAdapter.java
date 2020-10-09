package com.example.flashpig.EditDeck;

import android.content.Context;
import android.graphics.drawable.Drawable;
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


    public DeckSpinnerAdapter(Context context, ArrayList<Deck> deckArrayList){
        super(context, 0, deckArrayList);

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
        editSpinnerItemBtn.setVisibility(View.INVISIBLE);
    }
    public void setConstraintVisibility(View view){
        ConstraintLayout constraintLayout = view.findViewById(R.id.spinnerConstraintLayout);
        constraintLayout.setVisibility(View.VISIBLE);
    }
}
