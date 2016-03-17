package com.squeezer.android.sqlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squeezer.android.sqlite.R;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adnen on 3/17/16.
 */
public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.ViewHolder> {

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItemTitle.setText(mObjectsList.get(position).getTitle());
        holder.mItemInfo.setText(mObjectsList.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return mObjectsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemTitle;
        public TextView mItemInfo;

        public ViewHolder(View view) {
            super(view);
            mItemTitle = (TextView) view.findViewById(R.id.title_layout);
            mItemInfo = (TextView) view.findViewById(R.id.info_layout);

        }
    }

}
