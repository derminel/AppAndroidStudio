package com.example.secondtest;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_2;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_3;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_4;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_USERS_6;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_USERS;

public class ProfileDAO {

    private Cursor profile ;
    private DatabaseHelper dbh ;

    public ProfileDAO(Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.profile = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_USERS),null);
    }

    public String findName (String login) {
        this.profile.moveToFirst() ;
        while (!(this.profile.isLast())){
            if (this.profile.getString(0).equals(login)){
                return this.profile.getString(1) ;
            }
            this.profile.moveToNext();
        }
        return null;
    }

    public String findLastName (String login) {
        this.profile.moveToFirst() ;
        while (!(this.profile.isLast())){
            if (this.profile.getString(0).equals(login)){
                return this.profile.getString(2) ;
            }
            this.profile.moveToNext();
        }
        return null;
    }

    public String findAddress (String login) {
        this.profile.moveToFirst() ;
        while (!(this.profile.isLast())){
            if (this.profile.getString(0).equals(login)){
                return this.profile.getString(3) ;
            }
            this.profile.moveToNext();
        }
        return null;
    }

    public String findPreference (String login) {
        this.profile.moveToFirst() ;
        while (!(this.profile.isLast())){
            if (this.profile.getString(0).equals(login)){
                return this.profile.getString(4) ;
            }
            this.profile.moveToNext();
        }
        return null;
    }

    public boolean updateProfile(String login,String name,String lastName,String address, String preferences) {
        SQLiteDatabase db =  dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE_USERS_2,name);
        contentValues.put(COLUMN_NAME_TITLE_USERS_3,lastName);
        contentValues.put(COLUMN_NAME_TITLE_USERS_4, address);
        contentValues.put(COLUMN_NAME_TITLE_USERS_6, preferences);
        db.update(TABLE_NAME_USERS, contentValues, "LOGIN = ?",new String[] {login});
        return true;
    }
    /*public void updateProfile(String login, String Name, String LastName, String Address, String Preferences) {
        dbh.db.execSQL("UPDATE " + TABLE_NAME_USERS +
                "\nSET " + COLUMN_NAME_TITLE_USERS_2 + " = " + Name +
                "\nWHERE LOGIN = " + login);

        dbh.db.execSQL("UPDATE " + TABLE_NAME_USERS +
                "\nSET " + COLUMN_NAME_TITLE_USERS_3 + " = " + LastName +
                "\nWHERE LOGIN = " + login);
        dbh.db.execSQL("UPDATE " + TABLE_NAME_USERS +
                "\nSET " + COLUMN_NAME_TITLE_USERS_4 + " = " + Address +
                "\nWHERE LOGIN = " + login);
        dbh.db.execSQL("UPDATE " + TABLE_NAME_USERS +
                "\nSET " + COLUMN_NAME_TITLE_USERS_6 + " = " + Preferences +
                "\nWHERE LOGIN = " + login);
    }*/
}
