package com.squeezer.android.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.eventbus.EventBusEvents;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import org.greenrobot.eventbus.EventBus;

public class AddNewItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);



        //dbl.addItemWrapper(new ItemWrapper(2, "cc", "hey"));
        //dbl.addItemWrapper();

    }




}
