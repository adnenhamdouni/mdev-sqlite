package com.squeezer.android.sqlite.adapter;

/**
 * Created by adnen on 3/17/16.
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
