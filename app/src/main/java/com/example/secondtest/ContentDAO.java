package com.example.secondtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_CONTENT_1;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_CONTENT_2;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_CONTENT;

public class ContentDAO {

    private SQLiteDatabase contents ;
    private DatabaseHelper dbh ;
    private Cursor content ;

    public ContentDAO(Context activePage){
        this.contents = SQLiteDatabase.openOrCreateDatabase("Population Content", null,null);
        this.dbh = new DatabaseHelper(activePage);
        this.content = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_CONTENT),null);
    }

    public void addProduct(String pNb, String ListNb){
        SQLiteDatabase db =  dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE_CONTENT_1, ListNb);
        contentValues.put(COLUMN_NAME_TITLE_CONTENT_2, pNb);
        db.insert(TABLE_NAME_CONTENT,null ,contentValues);
    }

    public void delProd(String ListNb, String pNb){
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.delete(TABLE_NAME_CONTENT, String.format("%s = ?", COLUMN_NAME_TITLE_CONTENT_2),new String[] {pNb});
    }



}
