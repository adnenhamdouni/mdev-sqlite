package com.squeezer.android.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

public class AddNewItemActivity extends AppCompatActivity implements OnClickListener{

    private EditText mEditTextTitle, mEditTextInfo;
    private Button mBtnInsert;
    private String mTitle, mInfo;

    MySQLiteDataBaseHelper dbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        dbl = new MySQLiteDataBaseHelper(this);

        //dbl.addItemWrapper(new ItemWrapper(2, "cc", "hey"));
        //dbl.addItemWrapper();

        initView();
    }

    private void initView() {
        mEditTextTitle = (EditText) findViewById(R.id.edittext_title);
        mEditTextInfo = (EditText) findViewById(R.id.edittext_info);

        mBtnInsert = (Button) findViewById(R.id.btn_create_item);
        mBtnInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_create_item) {
            mTitle = mEditTextTitle.getText().toString();
            mInfo = mEditTextInfo.getText().toString();

            ItemWrapper item = new ItemWrapper(0, mTitle, mInfo);
            dbl.addItemWrapper(item);

            finish();
        }

    }
}
