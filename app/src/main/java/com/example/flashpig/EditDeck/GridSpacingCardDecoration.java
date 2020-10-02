package com.example.flashpig.EditDeck;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingCardDecoration extends RecyclerView.ItemDecoration {
    private int col;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingCardDecoration(int col, int spacing, boolean includeEdge) {
        this.col = col;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int column = position % col;
        if(includeEdge){
            outRect.left= spacing - column * spacing / col;
            outRect.right= (column + 1) * spacing / col;
            if (position < col) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / col; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / col; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= col) {
                outRect.top = spacing; // item top
            }
        }
    }
}
