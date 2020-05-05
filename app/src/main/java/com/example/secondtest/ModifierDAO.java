package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.secondtest.DatabaseContract.TABLE_NAME_MODIFIER;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_MODIFIER_1;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_MODIFIER_2;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_MODIFIER_3;


public class ModifierDAO {
    private Cursor modifier;
    private DatabaseHelper dbh ;

    public ModifierDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.modifier = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_MODIFIER),null);
    }

    public ArrayList<String> usersThatAreStatus(String ListNb,String statut){
        //Arraylist<String> oui= dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_MODIFIER),null);
        ArrayList<String> admins = new ArrayList<String>() ;
        this.modifier.moveToFirst();
        while (!(this.modifier.isLast())){
            if (this.modifier.getString(2).equals(ListNb) && this.modifier.getString(0).equals(statut)){
                admins.add(this.modifier.getString(1));
            }
            this.modifier.moveToNext();
        }
        return admins;
    }

    public void addStatus(String Login, String ListNb, String Statut){
        SQLiteDatabase db =  dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE_MODIFIER_1, ListNb);
        contentValues.put(COLUMN_NAME_TITLE_MODIFIER_2, Login);
        contentValues.put(COLUMN_NAME_TITLE_MODIFIER_3, Statut);
        db.insert(TABLE_NAME_MODIFIER,null ,contentValues);
    }

    public void delStatus(String ListNb, String Login){
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.delete(TABLE_NAME_MODIFIER, String.format("%s = ?", COLUMN_NAME_TITLE_MODIFIER_2),new String[] {Login});

    }
}
