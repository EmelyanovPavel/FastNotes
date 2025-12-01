package com.example.fastnotes;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecorator extends RecyclerView.ItemDecoration {
    private int space;

    public SpacingItemDecorator(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Добавляем отступ сверху только для первого элемента
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        }
    }
}