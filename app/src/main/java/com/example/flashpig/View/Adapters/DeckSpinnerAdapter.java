package com.example.flashpig.View.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.flashpig.R;
import com.example.flashpig.ViewModel.DashboardViewModel;

import java.util.ArrayList;

/**
 * @author Salvija
 * @responsibility An adapter class for the the DeckSpinnerAdapter
 * @version 22-10-20
 */

public class DeckSpinnerAdapter extends ArrayAdapter<Object> {
    private Context context;
    private OnEditItemsClickListener onEditItemClickListener;
    private DashboardViewModel viewModel;

    public DeckSpinnerAdapter(Context context, ArrayList<Object> deckArrayList, OnEditItemsClickListener onEditItemClickListener, DashboardViewModel viewModel){
        super(context, 0, deckArrayList);
        this.context = context;
        this.onEditItemClickListener = onEditItemClickListener;
        this.viewModel = viewModel;
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
                onEditItemClickListener.onRemoveDeckBtnClick(constraintLayout, deckName, amountCards, position);
            }
        });

        viewModel.getChosenDeck().setValue(viewModel.getDeckAtPos(position));
        deckName.setText(viewModel.getDeckName());
        amountCards.setText(Integer.toString(viewModel.getAmountCardsAtPos(position)));
        if (position % 2 == 0) { // we're on an even row
            convertView.setBackgroundColor(context.getResources().getColor(R.color.spinner1));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.spinner2));
        }
        return convertView;
    }

    public void setEditBtnVisibility(View view){ //Sets edit items invisible in spinner
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

    public interface OnEditItemsClickListener { void onRemoveDeckBtnClick(ConstraintLayout c, TextView t1, TextView t2, int pos);}
}
