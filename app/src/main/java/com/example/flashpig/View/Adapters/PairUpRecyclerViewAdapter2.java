package com.example.flashpig.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.R;
import com.example.flashpig.ViewModel.PairUpViewModel;

import java.io.Serializable;


/**
 *
 * @author Madeleine
 * @responsibility Displays the cards in Pair Up game as a list with a RecyclerView
 * @version 2020-10-16
 */


public class PairUpRecyclerViewAdapter2 extends RecyclerView.Adapter<PairUpRecyclerViewAdapter2.ViewHolder> implements Serializable {

    private ItemClickListener mClickListener;
    public Context mContext;
    private boolean reload = false;
    private int reloadCounter = 3;
    private PairUpViewModel viewModel;

    /**
     * The Pair Up RecyclerViewAdapter constructor
     * @param context the current context
     */
    public PairUpRecyclerViewAdapter2(Context context, ViewModel viewModel) {
        this.mContext = context;
        this.viewModel = (PairUpViewModel) viewModel;
    }

    /**
     * Used when a new card is added to the recyclerView.
     *
     * @param parent the parent
     * @param viewType the viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.pairup_card, parent, false);
        return new ViewHolder(itemView, mClickListener);
    }

    /**
     * Adds the content we want to show on each card.
     * The method checks also if the front or backside of the cards is showing.
     *
     * @param holder the current viewHolder
     * @param position the current position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.frontSideTextView.setText(viewModel.getItem(position).getFrontsideStr());
        holder.frontSideTextView.setTag(position);
        holder.backSideTextView.setText(viewModel.getItem(position).getBacksideStr());
        holder.itemView.setClickable(true);

        if (reload && reloadCounter != 0) {
            holder.itemView.setBackgroundResource(R.drawable.frame_default);
            reloadCounter--;
        }else {
            reload = false;
        }

        if(viewModel.getItem(position).isFrontside()){
            holder.frontSideTextView.setVisibility(View.INVISIBLE);
            holder.frontImageView.setVisibility(View.INVISIBLE);
            holder.backImageView.setVisibility(View.VISIBLE);
            holder.backSideTextView.setVisibility(View.VISIBLE);
        } else {
            holder.frontSideTextView.setVisibility(View.VISIBLE);
            holder.frontImageView.setVisibility(View.VISIBLE);
            holder.backImageView.setVisibility(View.INVISIBLE);
            holder.backSideTextView.setVisibility(View.INVISIBLE);
        }
    }

   /**
     * Gets the items current id
     *
     * @param position the items position
     * @return the items id
     */

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Gives the amount of rows the recycler view should have
     *
     * @return the amount of rows on the game board
     */

    @Override
    public int getItemCount() {
        return 3;
    }

    /**
     * Decides if a card is reloaded
     * @param reload tells if a card has been reloaded
     */

    public void setReload(boolean reload) {
        this.reload = reload;
        notifyDataSetChanged();
    }

    /**
     * Viewholder class that stores and recycles views as they are scrolled off screen
     */

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView frontSideTextView, backSideTextView;
        ImageView frontImageView, backImageView;
        private ItemClickListener mClickListener;
        CardView memorycard;

        /**
         * ViewHolder constructor
         *
         * @param itemView the current itemView
         * @param mClickListener a listener to clicks from the user
         */

        ViewHolder(View itemView, ItemClickListener mClickListener) {
            super(itemView);
            frontSideTextView = itemView.findViewById(R.id.frontCardTextView);
            backSideTextView = itemView.findViewById(R.id.backCardTextView);
            frontImageView = itemView.findViewById(R.id.frontCardImageView);
            backImageView = itemView.findViewById(R.id.backCardImageView);
            memorycard = itemView.findViewById(R.id.memorycard);
            this.mClickListener = mClickListener;
            itemView.setOnClickListener(this);
        }

        /**
         * Handles to click events
         *
         * @param view the clicked view
         */

        @Override
        public void onClick(View view) {
            if (mClickListener != null){
                try {
                    mClickListener.onItemClick(view, getAdapterPosition());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets the item viewType
     *
     * @param position the position of the view
     *
     * @return the position of the view
     */

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Decides if the item should remain the same after a notifyDataSetchanged() call
     *
     * @param hasStableIds boolean is is should remain the same
     */

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    /**
     * Getting data at click position
     *
     * @param position the position of the view
     * @return a card on the specific position
     */



    /**
     * Allows clicks events to be caught
     *
     * @param itemClickListener a listener to clicks from the user
     */

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    /**
     * Parent activity will implement this method to respond to click events
     *
     */

    public interface ItemClickListener {
        void onItemClick(View view, int position) throws InterruptedException;
    }
}
