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
import android.widget.Toast;

import com.squeezer.android.sqlite.adapter.CustomAdapter;
import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.database.SqliteDataBaseHelper;
import com.squeezer.android.sqlite.eventbus.EventBusEvents;
import com.squeezer.android.sqlite.fragment.AddItemFragment;
import com.squeezer.android.sqlite.fragment.ItemsFragment;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "ADNEN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            ItemsFragment fragment = new ItemsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(MainActivity.this, AddNewItemActivity.class);
//                startActivity(intent);

                AddItemFragment fragment = AddItemFragment.getInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, fragment)
                        .commit();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(EventBusEvents.ItemEvent event) {

        Toast.makeText(this, "message = "+event.getMessage(), Toast.LENGTH_LONG).show();

        if(event.getMessage().equals("insert")){


            ItemsFragment fragment = ItemsFragment.getInstance(event.getMessage());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        } else if (event.getMessage().equals("item clicked")) {
        }

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
