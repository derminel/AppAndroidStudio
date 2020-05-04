package com.example.secondtest;

import android.content.Context;
import android.database.Cursor;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_LISTS;

public class WishlistDAO {
    private Cursor wishlists;
    private DatabaseHelper dbh ;

    public WishlistDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.wishlists = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_LISTS),null);
    }
}
