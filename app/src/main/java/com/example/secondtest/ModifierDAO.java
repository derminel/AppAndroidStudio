package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_LOGIN;
import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_STATUS;
import static com.example.secondtest.DatabaseContract.TABLE_MODIFIER;

public class ModifierDAO {
    private DatabaseHelper dbh;//
    private Cursor modifier;//

    public ModifierDAO(Context activePage) {
        this.dbh = new DatabaseHelper(activePage);
        this.modifier = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_MODIFIER), null);
    }

    public ArrayList<String> getAdmin(String listNb) {
        this.modifier.moveToFirst();
        ArrayList<String> admins = new ArrayList<String>();
        while (!(this.modifier.isLast()) && !IsTableEmpty(this.modifier)) {
            if (this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("1")) {
                admins.add(this.modifier.getString(1));
            }
            this.modifier.moveToNext();
        }
        if (!IsTableEmpty(this.modifier) && this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("1")) {
            admins.add(this.modifier.getString(1));
        }
        return admins;

    }

    public boolean IsTableEmpty(Cursor cursor) {
        return !(cursor.getCount() > 0);
    }

    public boolean updateAdmin(String listnb, String login) {
        if (IsTableEmpty(modifier)||alreadyAdmin(listnb, login)) {
            return false;
        }
        //UserDAO userDAO = new UserDAO(context);
        //if (userDAO.isALogin(login)) {return false;}
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODIFIER_STATUS, "1");
        contentValues.put(COLUMN_MODIFIER_LISTNB, listnb);
        contentValues.put(COLUMN_MODIFIER_LOGIN, login);
        this.dbh.getDb().insert(TABLE_MODIFIER, null, contentValues);
        return true;
    }

    public boolean alreadyAdmin(String listNb, String login) {
        this.modifier.moveToFirst();
        while (!(this.modifier.isLast()) && !IsTableEmpty(this.modifier)) {
            if (this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("1") && this.modifier.getString(1) .equals(login)) {
                return true;
            }
            this.modifier.moveToNext();
        }
        return this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("1") && this.modifier.getString(1).equals(login);
    }

}
