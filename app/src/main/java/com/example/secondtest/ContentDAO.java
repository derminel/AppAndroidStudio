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

    private Cursor productsOfAList;
    private DatabaseHelper dbh ;

    public ContentDAO(Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.productsOfAList = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_CONTENT),null);
    }

    public Integer removeProduct(String listNb, String productNb) {
        return this.dbh.getDb().delete(TABLE_CONTENT, COLUMN_CONTENT_LISTNB + " = ? AND " + COLUMN_CONTENT_PRODUCTNB + " = ?",new String[] {listNb, productNb});
    }

    public ArrayList<String> getProductsOfAList(String listNb){
        ArrayList<String> products = new ArrayList<String>();
        this.productsOfAList.moveToFirst();
        try{
            while (!(this.productsOfAList.isLast())){
                if (this.productsOfAList.getString(0).equals(listNb)){
                    products.add(this.productsOfAList.getString(1)) ;
                }
                this.productsOfAList.moveToNext();
            }
            if (this.productsOfAList.getString(0).equals(listNb)){
                products.add(this.productsOfAList.getString(1)) ;
            }
        }
        catch (Exception e){}
        return products;
    }

    public boolean alreadyInList(String listNb, String productNb){
        Cursor cursor = this.dbh.getDb().rawQuery("SELECT * FROM " + TABLE_CONTENT + " WHERE " + COLUMN_CONTENT_LISTNB + " = ? AND "
                + COLUMN_CONTENT_PRODUCTNB + " = ?",new String[] {listNb, productNb});
        return (cursor.getCount() > 0);
    }
}
