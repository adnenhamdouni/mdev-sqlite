package com.squeezer.android.sqlite.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squeezer.android.sqlite.R;
import com.squeezer.android.sqlite.adapter.holder.ItemTouchHelperViewHolder;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adnen on 3/17/16.
 */
public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private LayoutInflater mInflater;
    private List<ItemWrapper> mObjectsList = new ArrayList<ItemWrapper>();


    public CustomAdapter(List<ItemWrapper> itemsList) {

        mObjectsList = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_layout, parent, false);

        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItemTitle.setText(mObjectsList.get(position).getTitle());
        holder.mItemInfo.setText(mObjectsList.get(position).getInfo());

        // Start a drag whenever the handle view is touched
        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("touch", "RecyclerListAdapter:handleviewOnTouchListener:Action = " + event.getAction());
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    Log.v("slim", "RecyclerListAdapter:handleviewOnTouchListener:ActionDown");
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mObjectsList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public TextView mItemTitle;
        public TextView mItemInfo;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            mItemTitle = (TextView) view.findViewById(R.id.title_layout);
            mItemInfo = (TextView) view.findViewById(R.id.info_layout);
            imageView = (ImageView) itemView.findViewById(R.id.image_layout);

        }

        @Override
        public void onItemSelected() {
            //itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            //itemView.setBackgroundColor(0);
        }
    }

}
