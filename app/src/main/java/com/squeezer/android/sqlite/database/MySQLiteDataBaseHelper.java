package com.squeezer.android.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squeezer.android.sqlite.wrapper.ItemWrapper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by adnen on 3/17/16.
 */
public class MySQLiteDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "test.db";

    private static final String TABLE_ITEM_OBJECT = "itemObject";
    private static final String TABLE_CATEGORY = "itemCategory";

    private static final String KEY_CATEGORY_ID = "idc";
    private static final String KEY_CATEGORY_NAME = "name";

    private static final String KEY_ITEM_OBJECT_ID = "id";
    private static final String KEY_ITEM_OBJECT_TITLE = "title";
    private static final String KEY_ITEM_OBJECT_INFO = "info";

    private static final String[] COLUMNS_ITEM_OBJECT = { KEY_ITEM_OBJECT_ID,
            KEY_CATEGORY_ID, KEY_ITEM_OBJECT_TITLE, KEY_ITEM_OBJECT_INFO };
    private static final String[] COLUMNS_ITEM_CATEGORY = { KEY_CATEGORY_ID,
            KEY_CATEGORY_NAME };

    public MySQLiteDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createTableItemObject(db);
        createTableCategory(db);

        ContentValues contentvaluesItemWrapper = new ContentValues();
        contentvaluesItemWrapper.put(KEY_ITEM_OBJECT_TITLE, "first title");
        contentvaluesItemWrapper.put(KEY_ITEM_OBJECT_INFO, "first info");

        db.insert(TABLE_ITEM_OBJECT, null, contentvaluesItemWrapper);

        ContentValues contentvaluesItemWrapper2 = new ContentValues();
        contentvaluesItemWrapper2.put(KEY_ITEM_OBJECT_TITLE, "first title2");
        contentvaluesItemWrapper2.put(KEY_ITEM_OBJECT_INFO, "first info2");

        db.insert(TABLE_ITEM_OBJECT, null, contentvaluesItemWrapper2);

        ContentValues contentvaluesCategory = new ContentValues();
        contentvaluesCategory.put(KEY_CATEGORY_NAME, "job");

        db.insert(TABLE_CATEGORY, null, contentvaluesCategory);

    }

    private void createTableCategory(SQLiteDatabase db) {
        String CREATE_ITEM_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY
                + "( " + KEY_CATEGORY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CATEGORY_NAME
                + " TEXT)";

        db.execSQL(CREATE_ITEM_CATEGORY_TABLE);

    }

    private void createTableItemObject(SQLiteDatabase db) {
        String CREATE_ITEM_OBJECT_TABLE = "CREATE TABLE " + TABLE_ITEM_OBJECT
                + "( " + KEY_ITEM_OBJECT_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CATEGORY_ID
                + " INTEGER, " + KEY_ITEM_OBJECT_TITLE + " TEXT, "
                + KEY_ITEM_OBJECT_INFO + " TEXT)";

        db.execSQL(CREATE_ITEM_OBJECT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_OBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);

        this.onCreate(db);
    }

    public void addItemWrapper(ItemWrapper item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_OBJECT_TITLE, item.getTitle());
        values.put(KEY_ITEM_OBJECT_INFO, item.getInfo());

        db.insert(TABLE_ITEM_OBJECT, null, // nullColumnHack
                values); // key/value -> keys = column names/ values = column
        // values

        db.close();
    }

    public int updateItemWrapper(ItemWrapper item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_OBJECT_TITLE, item.getTitle());
        values.put(KEY_ITEM_OBJECT_INFO, item.getInfo());

        // 3. updating row
        int i = db.update(TABLE_ITEM_OBJECT, // table
                values, // column/value
                KEY_ITEM_OBJECT_ID + " = ?", // selections
                new String[] { String.valueOf(item.getId()) }); // selection
        // args

        // 4. close
        db.close();

        return i;

    }

    public ItemWrapper getItemWrapper(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ITEM_OBJECT, // a. table
                COLUMNS_ITEM_OBJECT, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        ItemWrapper item = new ItemWrapper();
        item.setId(Integer.parseInt(cursor.getString(0)));
        item.setTitle(cursor.getString(2));
        item.setInfo(cursor.getString(3));

        // 5. return book
        return item;
    }

    public void deleteItemWrapper(ItemWrapper item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ITEM_OBJECT, // table name
                KEY_ITEM_OBJECT_ID + " = ?", // selections
                new String[] { String.valueOf(item.getId()) }); // selections
        // args

        // 3. close

    }

    public List<ItemWrapper> getAllItemWrapper() {

        List<ItemWrapper> listItemWrapper = new LinkedList<ItemWrapper>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ITEM_OBJECT;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ItemWrapper item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new ItemWrapper();

                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setTitle(cursor.getString(2));
                item.setInfo(cursor.getString(3));

                listItemWrapper.add(item);

            } while (cursor.moveToNext());
        }

        // return books
        return listItemWrapper;

    }

    public void deleteAllItemWrapper() {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ITEM_OBJECT, null, null);
        // 3. close

    }

    public List<ItemWrapper> getItemWrapperByCategory(int idCategory) {

        List<ItemWrapper> listItemWrapper = new LinkedList<ItemWrapper>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ITEM_OBJECT + " WHERE "
                + KEY_CATEGORY_ID + " = " + idCategory;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ItemWrapper item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new ItemWrapper();

                item = new ItemWrapper();

                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setTitle(cursor.getString(1));
                item.setInfo(cursor.getString(2));

                listItemWrapper.add(item);

            } while (cursor.moveToNext());
        }

        // return books
        return listItemWrapper;

    }

    public void resetCounterTableItemWrapper() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_OBJECT);

        createTableItemObject(db);

    }

}
