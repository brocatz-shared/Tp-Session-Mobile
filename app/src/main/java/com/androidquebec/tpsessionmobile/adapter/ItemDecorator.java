package com.androidquebec.tpsessionmobile.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ItemDecorator extends RecyclerView.ItemDecoration {

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State  state) {
        outRect.bottom = 20;
        outRect.left = 20;
        outRect.right = 20;
    }
}
