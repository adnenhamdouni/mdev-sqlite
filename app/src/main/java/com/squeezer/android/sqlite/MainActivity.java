package com.squeezer.android.sqlite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.squeezer.android.sqlite.adapter.CustomAdapter;
import com.squeezer.android.sqlite.database.MySQLiteDataBaseHelper;
import com.squeezer.android.sqlite.fragment.ItemsFragment;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemsFragment.MyListener{

    public static final String LOG_TAG = "MainActivity";

    MySQLiteDataBaseHelper dbl;

    private List<ItemWrapper> mItemList;
    private ItemWrapper mItemWrapper;

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


        dbl = new MySQLiteDataBaseHelper(this);



        dbl.getItemWrapper(1);

        mItemList = new ArrayList<>();
        new ListItemAsyncTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mItemList.clear();
        new ListItemAsyncTask().execute();
    }


    private class ListItemAsyncTask extends
            AsyncTask<Void, Integer, Void> {

        public ListItemAsyncTask() {
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();



        }

        @Override
        protected Void doInBackground(Void... params) {

            mItemList = dbl.getAllItemWrapper();
            for (ItemWrapper item: mItemList) {

                Log.e("adnen", "onItemClick title ="+item.getTitle());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ItemsFragment fragment = ItemsFragment.newInstance(MainActivity.this, mItemList);
            showFragment(fragment);

        }

    }

    private void showFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
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


    @Override
    public void onItemClickPerform(int position, ItemWrapper item) {

        Toast.makeText(MainActivity.this, "Position Clicked = "+ position + " & Item Title = "+item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
