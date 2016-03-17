package com.squeezer.android.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.squeezer.android.sqlite.adapter.CustomAdapter;
import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.database.SqliteDataBaseHelper;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "ADNEN";


    private ListView mListView;
    private List<ItemWrapper> objectItemsList;
    private CustomAdapter mCustomAdapter;

    SqliteDataBaseHelper mSqliteDataBaseHelper;
    MySQLiteDataBaseHelper dbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddNewItemActivity.class);
                startActivity(intent);
            }
        });

        mSqliteDataBaseHelper = SqliteDataBaseHelper
                .getInstance(getApplicationContext());

        dbl = new MySQLiteDataBaseHelper(this);

        //dbl.addItemWrapper(new ItemWrapper(2, "cc", "hey"));
        dbl.getItemWrapper(1);

        initView();

        objectItemsList = dbl.getAllItemWrapper();


        //initListValues(10);

    }

    @Override
    protected void onResume() {
        super.onResume();
        objectItemsList = dbl.getAllItemWrapper();
        ShowList();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.list_view);

    }

    private void initListValues(int numberOfObject) {

        objectItemsList = new ArrayList<ItemWrapper>();
        for (int i = 0; i < numberOfObject; i++) {
            ItemWrapper wrapper = new ItemWrapper(1, "item n° " + i, "item n° "
                    + i + " description");
            objectItemsList.add(wrapper);

        }

    }

    private void ShowList() {

        mCustomAdapter = new CustomAdapter(getApplicationContext(),
                objectItemsList);

        mListView.setAdapter(mCustomAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
