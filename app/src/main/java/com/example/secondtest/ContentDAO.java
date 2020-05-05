package com.example.secondtest;


import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class ContentDAO {

    private SQLiteDatabase content ;
    private DatabaseHelper dbh ;

    public ContentDAO(Context activePage){
        this.content = SQLiteDatabase.openOrCreateDatabase("Population Content", null,null);
        this.dbh = new DatabaseHelper(activePage);
    }
}
