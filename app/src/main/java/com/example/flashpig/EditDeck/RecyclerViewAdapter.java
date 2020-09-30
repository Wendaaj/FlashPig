package com.example.flashpig.EditDeck;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashpig.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EditCardViewHolder> {
    private String[] data;
    private LayoutInflater inflator;
    private ItemClickListener itemClickListener;

    public RecyclerViewAdapter(Context context, String[] data) {
        this.data = data;
        this.inflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EditCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.card, parent, false);
        return new EditCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditCardViewHolder holder, int position) {
        holder.TextView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    // stores and recycles views as they are scrolled off screen
    public class EditCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView TextView;

        EditCardViewHolder(View itemView) {
            super(itemView);
            TextView = itemView.findViewById(R.id.cardTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return data[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

