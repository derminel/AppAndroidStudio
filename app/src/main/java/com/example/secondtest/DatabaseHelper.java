
package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_1;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_2;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_3;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_4;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_5;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_6;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_7;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_USERS;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WishlistApp.db";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DatabaseContract.VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.SQL_CREATE_CONTENT);
        db.execSQL(DatabaseContract.SQL_CREATE_USERS);
        db.execSQL(DatabaseContract.SQL_CREATE_FRIENDS);
        db.execSQL(DatabaseContract.SQL_CREATE_PRODUCTS);
        db.execSQL(DatabaseContract.SQL_CREATE_LISTS);
        db.execSQL(DatabaseContract.SQL_CREATE_LIKE);
        db.execSQL(DatabaseContract.SQL_CREATE_MODIFIER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public SQLiteDatabase getDb() {
        return this.db ;
    }

    public boolean insertData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE_USERS_1,"derminel");
        contentValues.put(COLUMN_NAME_TITLE_USERS_2,"derminel");
        contentValues.put(COLUMN_NAME_TITLE_USERS_3,"derminel");
        contentValues.put(COLUMN_NAME_TITLE_USERS_4,"derminel");
        contentValues.put(COLUMN_NAME_TITLE_USERS_5,"derminel");
        contentValues.put(COLUMN_NAME_TITLE_USERS_6,"derminel");
        contentValues.put(COLUMN_NAME_TITLE_USERS_7,"derminel");
        long result = db.insert(TABLE_NAME_USERS,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
