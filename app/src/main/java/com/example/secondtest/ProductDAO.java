package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;


import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_LIKE_COUNT;
import static com.example.secondtest.DatabaseContract.COLUMN_LIKE_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_LIKE_LOGIN;
import static com.example.secondtest.DatabaseContract.COLUMN_LIKE_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_CATEGORY;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_INFO;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_NAME;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_PRICE;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_PRODUCTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_PRODUCTS_WEBSITE;
import static com.example.secondtest.DatabaseContract.TABLE_CONTENT;
import static com.example.secondtest.DatabaseContract.TABLE_LIKE;
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

    public boolean addProduct(String login, String wishListNb, String productNb, String name, int price, String info, String category, String website){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCTS_PRODUCTNB, productNb);
        contentValues.put(COLUMN_PRODUCTS_NAME, name);
        contentValues.put(COLUMN_PRODUCTS_PRICE, price);
        contentValues.put(COLUMN_PRODUCTS_INFO, info);
        contentValues.put(COLUMN_PRODUCTS_CATEGORY, category);
        contentValues.put(COLUMN_PRODUCTS_WEBSITE, website);
        long result = this.dbh.getDb().insert(TABLE_PRODUCTS,null ,contentValues);
        ContentValues contentValuesLike = new ContentValues();
        contentValuesLike.put(COLUMN_LIKE_COUNT, 0.0); //initialisé à 0
        contentValuesLike.put(COLUMN_LIKE_LISTNB, wishListNb);
        contentValuesLike.put(COLUMN_LIKE_PRODUCTNB, productNb);
        contentValuesLike.put(COLUMN_LIKE_LOGIN,login);
        this.dbh.getDb().insert(TABLE_LIKE,null,contentValuesLike);
        return result != -1;
    }

    public boolean updateLike(String login, String wishListNb, String productNb, byte likeValue){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LIKE_COUNT, likeValue); //initialisé à 0
        long result = this.dbh.getDb().update(TABLE_LIKE,contentValues,COLUMN_LIKE_LOGIN + " = ? AND " + COLUMN_LIKE_LISTNB
                + " = ? AND " + COLUMN_LIKE_PRODUCTNB + " = ?",new String[] {login, wishListNb, productNb});
        return result != -1;
    }

    public String getLikeStatus(String login, String wishListNb, String productNb){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ? AND %s = ? AND %s = ?",TABLE_LIKE, COLUMN_LIKE_LOGIN, COLUMN_LIKE_LISTNB,
                COLUMN_LIKE_PRODUCTNB),new String[] {login, wishListNb, productNb});
        cursor.moveToFirst();
        return cursor.getString(1);
    }

    public Cursor getAllColumn(String productNb){
        return this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_PRODUCTS,
                COLUMN_PRODUCTS_PRODUCTNB) ,new String[] {productNb});
    }

    public String getProductNumber(String name){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_PRODUCTS,
                COLUMN_PRODUCTS_NAME) ,new String[] {name});
        if (cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToFirst();
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
