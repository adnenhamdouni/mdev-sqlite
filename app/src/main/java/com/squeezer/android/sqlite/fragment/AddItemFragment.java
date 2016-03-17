package com.squeezer.android.sqlite.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.squeezer.android.sqlite.R;
import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.database.SqliteDataBaseHelper;
import com.squeezer.android.sqlite.eventbus.EventBusEvents;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by adnen on 3/17/16.
 */
public class AddItemFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private EditText mEditTextTitle, mEditTextInfo;
    private Button mBtnInsert;
    private String mTitle, mInfo;

    MySQLiteDataBaseHelper dbl;

    public static AddItemFragment getInstance() {
        AddItemFragment fragment = new AddItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity().getApplicationContext();
        dbl = new MySQLiteDataBaseHelper(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_add_item,
                container, false);

        initView(view);

        return view;

    }

    private void initView(View view) {
        mEditTextTitle = (EditText) view.findViewById(R.id.edittext_title);
        mEditTextInfo = (EditText) view.findViewById(R.id.edittext_info);

        mBtnInsert = (Button) view.findViewById(R.id.btn_create_item);
        mBtnInsert.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_create_item) {
            mTitle = mEditTextTitle.getText().toString();
            mInfo = mEditTextInfo.getText().toString();

            ItemWrapper item = new ItemWrapper(0, mTitle, mInfo);
            dbl.addItemWrapper(item);
            EventBus.getDefault().post(new EventBusEvents.ItemEvent("insert"));
        }
    }
}
