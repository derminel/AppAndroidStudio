package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;


import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.TABLE_CONTENT;
import static com.example.secondtest.DatabaseContract.TABLE_PRODUCTS;

public class ProductDAO {

    private Cursor products;
    private DatabaseHelper dbh ;

    public ProductDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.products = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_PRODUCTS),null);
    }

    public String lineCounter (){
        return String.valueOf(DatabaseUtils.queryNumEntries(this.dbh.getDb(), TABLE_PRODUCTS));
    }

    public boolean addProduct(String listNb, Product p){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CONTENT_LISTNB, listNb);
        contentValues.put(COLUMN_CONTENT_PRODUCTNB, p.getProductNb());
        long result = this.dbh.getDb().insert(TABLE_CONTENT,null ,contentValues);
        return result != -1;
    }
}
