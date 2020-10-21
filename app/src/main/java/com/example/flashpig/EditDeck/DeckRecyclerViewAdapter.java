package com.example.flashpig.EditDeck;


import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.R;
import com.example.flashpig.ViewModel.DashboardViewModel;

import java.util.List;

/**
 * An adapter class for the recyclerView
 */
public class DeckRecyclerViewAdapter extends RecyclerView.Adapter<DeckRecyclerViewAdapter.EditCardViewHolder> {
    private List<Card> cardsList;
    private ItemClickListener clickListener;
    private DashboardViewModel viewModel;

    public DeckRecyclerViewAdapter(Context context, List<Card> cardsList, ViewModel viewmodel) {
        this.cardsList = cardsList;
        this.viewModel = (DashboardViewModel) viewmodel;
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull EditCardViewHolder holder, int position) {
        viewModel.setCard(cardsList.get(position));
        setCardsValues(holder);

        holder.deleteCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onRemoveCardClick(viewModel.getCard().getValue(), cardsList);
            }
        });

        holder.editCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickListener.onEditItemBtnClick(cardsList.get(position));
            }
        });
    }

    private void changeConstraints(EditCardViewHolder holder){
        ConstraintLayout.LayoutParams txtParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT/2);
        ConstraintLayout.LayoutParams imgParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT/2);
        txtParams.bottomToTop = R.id.editCardBtn;
        imgParams.bottomToTop = R.id.cardTextView;
        holder.cardTextView.setLayoutParams(txtParams);
        holder.cardImageView.setLayoutParams(imgParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setCardsValues(EditCardViewHolder holder){
        if (viewModel.getCard().getValue().isFrontside()) {
            if (viewModel.checkHasFrontTxtAndImg()) {
                holder.cardTextView.setText(viewModel.getCard().getValue().getFrontsideStr());
                holder.cardImageView.setImageBitmap(viewModel.getCard().getValue().getFrontImg());
                changeConstraints(holder);
            }
            else if (viewModel.checkHasFrontTxtOnly()) {
                holder.cardTextView.setText(viewModel.getCard().getValue().getFrontsideStr());
                holder.cardTextView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            else {
                holder.cardImageView.setImageBitmap(viewModel.getCard().getValue().getFrontImg());
                holder.cardImageView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
        else {
            if (viewModel.checkHasBackTxtAndImg()) {
                holder.cardTextView.setText(viewModel.getCard().getValue().getBacksideStr());
                holder.cardImageView.setImageBitmap(viewModel.getCard().getValue().getBackImg());
                changeConstraints(holder);
            }
            else if (viewModel.checkHasBackTxtOnly()) {
                holder.cardTextView.setText(viewModel.getCard().getValue().getBacksideStr());
                holder.cardTextView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            else {
                holder.cardImageView.setImageBitmap(viewModel.getCard().getValue().getBackImg());
                holder.cardImageView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            }
        }
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
            void onRemoveCardClick(Card card, List<Card> cardsList);
            void onEditItemBtnClick(Card card);

        }
}


