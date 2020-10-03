package com.example.flashpig.pairup;


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
public class pairupRecyclerViewAdapter extends RecyclerView.Adapter<pairupRecyclerViewAdapter.EditCardViewHolder> {
    private List<Card> cardsList;

    public pairupRecyclerViewAdapter(Context context, List<Card> cardsList) {
        this.cardsList = cardsList;
    }

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
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.pairupcard, parent, false);
        return new EditCardViewHolder(itemView);
    }

    /**
     * Adds the content we want to show on each card.
     * The method checks also if the front or backside of the cards is showing.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull EditCardViewHolder holder, int position) {
        Card card = cardsList.get(position);

        holder.frontSideTextView.setText(card.getFrontsideStr());
        holder.backSideTextView.setText(card.getBacksideStr());

        //set the back and front imageviews also.
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    /**
     * An inner class for the viewHolder that holds each card in the recyclerView.
     */
    public class EditCardViewHolder extends RecyclerView.ViewHolder {
        TextView frontSideTextView, backSideTextView;
        ImageView frontImageView, backImageView;

        /**
         * The constructor adds all required content on the card, when a new viewHolder is created.
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
    }
}
