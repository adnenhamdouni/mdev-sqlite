package com.squeezer.android.sqlite.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.squeezer.android.sqlite.MainActivity;
import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by adnen on 3/17/16.
 */
public final class SqliteDataBaseHelper extends ContentProvider {

    private static final String KEY_CATEGORY_ID = "idc";
    private static final String KEY_ITEM_OBJECT_ID = "id";
    private static final String KEY_ITEM_OBJECT_TITLE = "title";
    private static final String KEY_ITEM_OBJECT_INFO = "info";

    SqliteDataBaseHelper mSqliteDataBaseHelper;


    // used for the UriMacher
    private static final int ITEMS = 10;
    private static final int ITEM_ID = 20;

    private static final String AUTHORITY = "com.leadertun.android.sqlitedatabase.test";

    private static final String BASE_PATH = "itemObject";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/items";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/todo";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, ITEMS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ITEM_ID);
    }

    private Context mContext;

    private static volatile SqliteDataBaseHelper mInstance = null;

    private LinkedHashMap<Integer, ItemWrapper> mItemsMapById = null;

    public static SqliteDataBaseHelper getInstance(Context context) {

        synchronized (SqliteDataBaseHelper.class) {
            if (mInstance == null) {
                mInstance = new SqliteDataBaseHelper(context);
            }
        }
        return mInstance;
    }

    /**
     * private constructor
     *
     * @param context
     */
    private SqliteDataBaseHelper(Context context) {

        mContext = context;
        mItemsMapById = new LinkedHashMap<Integer, ItemWrapper>();

    }

    public ItemWrapper getCalendarWrapperById(long id) {
        return mItemsMapById.get(id);
    }

    public void addItemWrapper(ItemWrapper itemWrapper) {
        int id = itemWrapper.getId();
        if (!mItemsMapById.containsKey(id)) {

            mItemsMapById.put(id, itemWrapper);
        } else {
            updateItemWrapper(id, itemWrapper);
        }
    }

    public void updateItemWrapper(long id, ItemWrapper newItem) {

        ItemWrapper oldItemWrapper = mItemsMapById.get(id);
        if (oldItemWrapper != null) {
            oldItemWrapper.setItemWrapper(newItem);
        }
    }

    public void removeItemWrapper(long id) {
        mItemsMapById.remove(id);
    }

    public void fillItems(Cursor managedCursor) {

        if (managedCursor != null && managedCursor.moveToFirst()) {

            do {

                ItemWrapper itemWrapper = new ItemWrapper();

                itemWrapper.setId(managedCursor.getInt(managedCursor
                        .getColumnIndex(KEY_ITEM_OBJECT_ID)));
                itemWrapper.setIdCategory(managedCursor.getInt(managedCursor
                        .getColumnIndex(KEY_CATEGORY_ID)));
                itemWrapper.setTitle(managedCursor.getString(managedCursor
                        .getColumnIndex(KEY_ITEM_OBJECT_TITLE)));
                itemWrapper.setInfo(managedCursor.getString(managedCursor
                        .getColumnIndex(KEY_ITEM_OBJECT_INFO)));

                addItemWrapper(itemWrapper);

            } while (managedCursor.moveToNext());
        } else {
            Log.i("adnen", "No items");
        }

    }

    public void getCalendars(ArrayList<ItemWrapper> result) {
        if (result != null) {
            result.clear();

            for (ItemWrapper itemWrapper : mItemsMapById.values()) {
                result.add(itemWrapper);
            }
        }
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
