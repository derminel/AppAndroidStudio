package com.example.secondtest;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;

import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.TABLE_CONTENT;

public class ContentDAO {

    private Cursor cursor;
    private DatabaseHelper dbh ;

    public ContentDAO(Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.cursor = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_CONTENT),null);
    }

    public Integer removeProduct(String listNb, String productNb) {
        return this.dbh.getDb().delete(TABLE_CONTENT, COLUMN_CONTENT_LISTNB + " = ? AND " + COLUMN_CONTENT_PRODUCTNB + " = ?",new String[] {listNb, productNb});
    }

    public ArrayList<String> getProductsOfAList(String listNb){
        ArrayList<String> products = new ArrayList<String>();
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_CONTENT,
                COLUMN_CONTENT_LISTNB) ,new String[] {listNb});
        cursor.moveToFirst();
        try{
            while (!(cursor.isLast())){
                products.add(cursor.getString(1)) ;
                cursor.moveToNext();
            }
            products.add(cursor.getString(1)) ;
        }
        catch (Exception e){}
        return products;
    }

    public boolean alreadyInList(String listNb, String productNb){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?", TABLE_CONTENT,
                COLUMN_CONTENT_LISTNB, COLUMN_CONTENT_PRODUCTNB) ,new String[] {listNb, productNb});
        return (cursor.getCount() > 0);
    }

    public boolean addProduct(String listNb, String productNb){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CONTENT_LISTNB, listNb);
        contentValues.put(COLUMN_CONTENT_PRODUCTNB, productNb);
        long result = this.dbh.getDb().insert(TABLE_CONTENT,null ,contentValues);
        return result != -1;
    }
}
