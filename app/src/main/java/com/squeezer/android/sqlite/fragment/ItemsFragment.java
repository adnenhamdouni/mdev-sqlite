package com.squeezer.android.sqlite.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squeezer.android.sqlite.R;
import com.squeezer.android.sqlite.adapter.CustomAdapter;
import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.database.SqliteDataBaseHelper;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adnen on 3/17/16.
 */
public class ItemsFragment extends Fragment {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<CustomAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ItemWrapper> mItemList;
    private ItemWrapper mItemWrapper;

    SqliteDataBaseHelper mSqliteDataBaseHelper;
    MySQLiteDataBaseHelper dbl;

    public static ItemsFragment newInstance() {
        ItemsFragment fragment = new ItemsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity().getApplicationContext();

        mSqliteDataBaseHelper = SqliteDataBaseHelper
                .getInstance(mContext);

        dbl = new MySQLiteDataBaseHelper(mContext);

        //dbl.addItemWrapper(new ItemWrapper(2, "cc", "hey"));
        dbl.getItemWrapper(1);


        mItemList = dbl.getAllItemWrapper();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_items,
                container, false);

        initView(view);

        initRecyclerView();


        return view;

    }

    private void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_list);

    }

    private void initRecyclerView() {

        mItemWrapper = new ItemWrapper();
        mItemList = dbl.getAllItemWrapper();

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(mItemList);
        mRecyclerView.setAdapter(mAdapter);
    }


}
