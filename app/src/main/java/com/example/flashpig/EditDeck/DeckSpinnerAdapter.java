package com.example.flashpig.EditDeck;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.flashpig.Model.Deck;
import com.example.flashpig.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class DeckSpinnerAdapter extends ArrayAdapter<Deck> {
    ArrayList<Deck> deckArrayList;
    Context context;
    private OnEditItemsClickListener onEditItemClickListener;

    public DeckSpinnerAdapter(Context context, ArrayList<Deck> deckArrayList, OnEditItemsClickListener onEditItemClickListener){
        super(context, 0, deckArrayList);
        this.deckArrayList = deckArrayList;
        this.context = context;
        this.onEditItemClickListener = onEditItemClickListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        editSpinnerItemBtn.setOnClickListener(v->
                manageVisibility(constraintLayout, deckName, amountCards));

        removeSpinnerItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditItemClickListener.onRemoveBtnClick(constraintLayout, deckName, amountCards, position);
            }
        });

        int defaultPosition = 0;
        Deck currentDeck = getItem(position);
        if(currentDeck != null && position != defaultPosition){
            deckName.setText(currentDeck.getDeckName());
            amountCards.setText(Integer.toString(currentDeck.getAmountCards()));
            if (position % 2 == 0) { // we're on an even row
                convertView.setBackgroundColor(context.getResources().getColor(R.color.spinner1));
            } else {
                convertView.setBackgroundColor(context.getResources().getColor(R.color.spinner2));
            }
        }else{
            deckName.setText(context.getResources().getText(R.string.choose_deck));
            convertView.setFocusable(View.NOT_FOCUSABLE);
            editSpinnerItemBtn.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    public void setVisibility(View view){ //Sets edit items invisible in spinner
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

    public interface OnEditItemsClickListener
    {
        void onRemoveBtnClick(ConstraintLayout c, TextView t1, TextView t2, int pos);
    }
}
