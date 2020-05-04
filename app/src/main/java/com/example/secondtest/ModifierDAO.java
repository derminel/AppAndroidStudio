package com.example.secondtest;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import static com.example.secondtest.DatabaseContract.TABLE_NAME_MODIFIER;

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
}
