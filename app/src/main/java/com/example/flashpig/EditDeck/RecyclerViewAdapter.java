package com.example.flashpig.EditDeck;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.FirstFragment;
import com.example.flashpig.MainActivity;
import com.example.flashpig.Model.Card;
import com.example.flashpig.R;
import com.example.flashpig.createcard.CardFragment;
import com.example.flashpig.pairup.activity_pairup;

import java.util.List;

/**
 * An adapter class for the recyclerView
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EditCardViewHolder> {
    private List<Card> cardsList;
    private ItemClickListener clickListener;
    private EditDeckActivity fragment;

    public RecyclerViewAdapter(Context context, EditDeckActivity fragment, List<Card> cardsList) {
        this.cardsList = cardsList;
        this.fragment = fragment;
    }

    //public Card getCard(int position) { return cardsList.get(position); }

    /**
     * Used when a new card is added to the recyclerView.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public EditCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new EditCardViewHolder(cardView);
    }

    /**
     * Adds content on each card.
     * Checks if the front or backside of the cards is showing.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull EditCardViewHolder holder, int position) {

        Card card = cardsList.get(position);
        if (card.isFrontside()) {
            holder.cardTextView.setText(card.getFrontsideStr());
        } else {
            holder.cardTextView.setText(card.getBacksideStr());
        }

        holder.deleteCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.getDeleteCard().setVisibility(View.VISIBLE);
                if(!fragment.getCheckBox().isChecked()){
                    setYesNoBtn(card);
                }else{
                    removeCard(card);
                    fragment.savePreferences();
                    fragment.loadPreferences();
                    fragment.getDeleteCard().setVisibility(View.INVISIBLE);
                }
            }
        });

        holder.editCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_editDeckActivity_to_cardFragment);
            }
        });
        //set the back and front imageviews also.
    }
        private void removeCard(Card card){
            cardsList.remove(card);
            notifyDataSetChanged();
            fragment.setAmountTxt();
        }
        private void setYesNoBtn(Card card){
            fragment.getYesBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeCard(card);
                    fragment.getDeleteCard().setVisibility(View.INVISIBLE);
                }
            });
            fragment.getNoBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.getDeleteCard().setVisibility(View.INVISIBLE);
                }
            });
        }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    /**
     * ViewHolder class that holds each card in the recyclerView.
     */
    public class EditCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cardTextView;
        ImageView cardImageView;
        Button deleteCardBtn, editCardBtn;


        /**
         * Class constructor.
         * Adds all required content on the card, when a new viewHolder is created.
         *
         * @param itemView
         */
        EditCardViewHolder(View itemView) {
            super(itemView);
            cardTextView = itemView.findViewById(R.id.cardTextView);
            cardImageView = itemView.findViewById(R.id.cardImageView);
            deleteCardBtn = itemView.findViewById(R.id.deleteCardBtn);
            editCardBtn = itemView.findViewById(R.id.editCardBtn);
        }


        //in progress...

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());

        }
    }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }


        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
}


