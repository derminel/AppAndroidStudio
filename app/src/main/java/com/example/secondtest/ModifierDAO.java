package com.example.secondtest;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import static com.example.secondtest.DatabaseContract.TABLE_MODIFIER;

public class ModifierDAO {
    private DatabaseHelper dbh;//
    private Cursor modifier;//

    public ModifierDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.modifier = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_MODIFIER),null);
    }

    public ArrayList<String> getAdmin(String listNb) {
        this.modifier.moveToFirst();
        ArrayList<String> admins = new ArrayList<String>();
        try{
            while (!(this.modifier.isLast())){
                if (this.modifier.getString(2).equals(listNb)&&this.modifier.getString(0).equals("1")){
                    admins.add(this.modifier.getString(1));
                }
                this.modifier.moveToNext();
            }
            if (this.modifier.getString(2).equals(listNb)&&this.modifier.getString(0).equals("1")){
                admins.add(this.modifier.getString(1));
            }
            return admins;
        }
        catch (Exception e){
            return null;
        }
    }
}
