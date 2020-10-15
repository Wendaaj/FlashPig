package com.example.flashpig.memory;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.Model.Card;
import com.example.flashpig.R;

import java.io.Serializable;
import java.util.List;

/**
 * An adapter class for the recyclerView
 */
public class memoryRecyclerViewAdapter extends
        RecyclerView.Adapter<memoryRecyclerViewAdapter.ViewHolder>  {

    private List<Card> cardsList;
    private ItemClickListener mClickListener;
    public Context mContext;
    int amountRows = 3;

    public memoryRecyclerViewAdapter(Context context, List<Card> cardsList) {
        this.mContext = context;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.memorycard, parent, false);
        return new ViewHolder(itemView, mClickListener);
    }

    /**
     * Adds the content we want to show on each card.
     * The method checks also if the front or backside of the cards is showing.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cardsList.get(position);
        holder.frontSideTextView.setText(card.getFrontsideStr());
        holder.frontSideTextView.setTag(position);
        holder.backSideTextView.setText(card.getBacksideStr());
        holder.itemView.setBackgroundResource(R.drawable.frame_default);
        holder.itemView.setClickable(true);

        if(!card.isFrontside()){
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() { return amountRows; }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView frontSideTextView, backSideTextView;
        ImageView frontImageView, backImageView;
        private ItemClickListener mClickListener;

        ViewHolder(View itemView, ItemClickListener mClickListener) {
            super(itemView);
            frontSideTextView = itemView.findViewById(R.id.frontCardTextView);
            backSideTextView = itemView.findViewById(R.id.backCardTextView);
            frontImageView = itemView.findViewById(R.id.frontCardImageView);
            backImageView = itemView.findViewById(R.id.backCardImageView);
            this.mClickListener = mClickListener;
            itemView.setOnClickListener(this);
        }


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

    public void flip(View view, int position) {
        if(!cardsList.get(position).isFrontside()){ //set frontside
            view.findViewById(R.id.frontCardTextView).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.frontCardImageView).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.backCardTextView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.backCardImageView).setVisibility(View.VISIBLE);
        }
        else { //sets backside
            view.findViewById(R.id.frontCardTextView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.frontCardImageView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.backCardTextView).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.backCardImageView).setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    // convenience method for getting data at click position
    public Card getItem(int position) {
        return cardsList.get(position);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position) throws InterruptedException;
    }
}