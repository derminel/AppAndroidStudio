package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_1;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_3;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_2;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_4;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_LISTS;

public class WishListDAO {
    private Cursor wishlist ;
    private DatabaseHelper dbh ;

    public WishListDAO(Context context){
        this.dbh = new DatabaseHelper(context);
        this.wishlist = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_LISTS),null);
    }








    public boolean updateWishlist(String name,boolean publicc) {
        SQLiteDatabase db =  dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE_LISTS_1,name);
        contentValues.put(COLUMN_NAME_TITLE_LISTS_3,String.valueOf(publicc));


        db.update(TABLE_NAME_LISTS, contentValues, "Name = ?",new String[] {name});
        return true;
    }
}
