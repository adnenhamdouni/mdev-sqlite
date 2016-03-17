package com.squeezer.android.sqlite.adapter;

import android.content.Context;
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
public class CustomAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ItemWrapper> mObjectsList = new ArrayList<ItemWrapper>();

    public CustomAdapter(Context context, List<ItemWrapper> objectItemsList) {

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mObjectsList = objectItemsList;
    }

    @Override
    public int getCount() {

        return mObjectsList.size();
    }

    @Override
    public Object getItem(int position) {

        return mObjectsList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder itemViewHolder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.list_item_layout, parent,
                    false);

            itemViewHolder = new ItemViewHolder();
            itemViewHolder.mItemTitle = (TextView) convertView
                    .findViewById(R.id.title_layout);
            itemViewHolder.mItemInfo = (TextView) convertView
                    .findViewById(R.id.info_layout);

            convertView.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) convertView.getTag();
        }

        itemViewHolder.mItemTitle
                .setText(mObjectsList.get(position).getTitle());
        itemViewHolder.mItemInfo.setText(mObjectsList.get(position).getInfo());


        return convertView;
    }

    private static class ItemViewHolder {

        TextView mItemTitle;
        TextView mItemInfo;

    }

}
