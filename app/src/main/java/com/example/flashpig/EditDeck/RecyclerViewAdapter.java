package com.example.flashpig.EditDeck;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.R;

import java.util.List;

/**
 * An adapter class for the recyclerView
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EditCardViewHolder> {
    private List<Card> cardsList;
    private ItemClickListener mClickListener;

    public Card getCard(int position) {
        return cardsList.get(position);
    }

    public RecyclerViewAdapter(Context context, List<Card> cardsList) {
        this.cardsList = cardsList;
    }

    /**
     * Used when a new card is added to the recyclerView.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public EditCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card, parent, false);
        return new EditCardViewHolder(itemView);
    }

    /**
     * Adds content on each card.
     * Checks if the front or backside of the cards is showing.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull EditCardViewHolder holder, int position) {
        Card card = cardsList.get(position);
        if(card.isFrontside()){
            holder.frontSideTextView.setText(card.getFrontsideStr());
        }else{
            holder.backSideTextView.setText(card.getBacksideStr());
        }
        //set the back and front imageviews also.
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    /**
     * Class for the viewHolder that holds each card in the recyclerView.
     */
    public class EditCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView frontSideTextView, backSideTextView;
        ImageView frontImageView, backImageView;

        /**
         * Class constructor.
         * Adds all required content on the card, when a new viewHolder is created.
         *
         * @param itemView
         */
        EditCardViewHolder(View itemView) {
            super(itemView);
            frontSideTextView = itemView.findViewById(R.id.frontCardTextView);
            backSideTextView = itemView.findViewById(R.id.backCardTextView);
            frontImageView = itemView.findViewById(R.id.frontCardImageView);
            backImageView = itemView.findViewById(R.id.backCardImageView);
        }

       //in progress...

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }


        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }

