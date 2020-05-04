package com.example.secondtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import static com.example.secondtest.DatabaseContract.TABLE_NAME_USERS;

public class UserDAO {
    private Cursor users;
    private DatabaseHelper dbh ;

    public UserDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.users = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_USERS),null);
    }

    public boolean exist(String login){

        this.users.moveToFirst() ;
        while (!(this.users.isLast())){
            try {
                if (this.users.getString(0).equals(login)){
                    return true ;
                }
                this.users.moveToNext();
            }
            catch (Exception e){
                return false;
            }
        }
        return (this.users.getString(0).equals(login));
    }

    public String getPassword(String login){
        this.users.moveToFirst() ;
        while (!(this.users.isLast())){
            if (this.users.getString(0).equals(login)){
                return this.users.getString(6) ;
            }
            this.users.moveToNext();
        }
        return null;
    }
}

