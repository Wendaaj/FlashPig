package com.example.flashpig.View.Adapters;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Salvija
 * @responsibility Fixes the layout for the RecyclerView
 * @version 22-10-20
 */

public class GridSpacingCardDecoration extends RecyclerView.ItemDecoration {
    private int col;
    private int spacing;

    public GridSpacingCardDecoration(int col, int spacing) {
        this.col = col;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int column = position % col;
            outRect.left = column * spacing / col;
            outRect.right = spacing - (column + 1) * spacing / col;
            if (position >= col) {
                outRect.top = spacing; // item top
            }
        }
    }


