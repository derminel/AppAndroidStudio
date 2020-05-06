package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;


import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_CATEGORY;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_INFO;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_NAME;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_PRICE;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_WEBSITE;
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

    public boolean addProduct(String productNb, String name, int price, String info, String category, String website){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCTS_PRODUCTNB, productNb);
        contentValues.put(COLUMN_PRODUCTS_NAME, name);
        contentValues.put(COLUMN_PRODUCTS_PRICE, price);
        contentValues.put(COLUMN_PRODUCTS_INFO, info);
        contentValues.put(COLUMN_PRODUCTS_CATEGORY, category);
        contentValues.put(COLUMN_PRODUCTS_WEBSITE, website);
        long result = this.dbh.getDb().insert(TABLE_PRODUCTS,null ,contentValues);
        return result != -1;
    }

    public String getProductNumber(String name){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_PRODUCTS,
                COLUMN_PRODUCTS_NAME) ,new String[] {name});
        if (cursor.getCount() <= 0){
            return null;
        }
        return cursor.getString(0);
    }

    public String getProductName(String productNb){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_PRODUCTS,
                COLUMN_PRODUCTS_PRODUCTNB) ,new String[] {productNb});
        if (cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToFirst();
        return cursor.getString(1);
    }
}
