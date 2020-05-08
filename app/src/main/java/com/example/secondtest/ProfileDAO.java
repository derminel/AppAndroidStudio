package com.example.secondtest;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import static com.example.secondtest.DatabaseContract.COLUMN_USERS_ADDRESS;
import static com.example.secondtest.DatabaseContract.COLUMN_USERS_LASTNAME;
import static com.example.secondtest.DatabaseContract.COLUMN_USERS_NAME;
import static com.example.secondtest.DatabaseContract.COLUMN_USERS_PREFERENCES;
import static com.example.secondtest.DatabaseContract.TABLE_USERS;

public class ProfileDAO {

    private Cursor profile ;
    private DatabaseHelper dbh ;

    //Constructeur
    public ProfileDAO(Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.profile = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_USERS),null);
    }

    // Renvoie le prénom d'un certain login
    public String findName (String login) {
        this.profile.moveToFirst() ;
        try{
            while (!(this.profile.isLast())){
                if (this.profile.getString(0).equals(login)){
                    return this.profile.getString(1) ;
                }
                this.profile.moveToNext();
            }
            if (this.profile.getString(0).equals(login)) {
                return this.profile.getString(1) ;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    // Renvoie le nom de famille d'un certain login
    public String findLastName (String login) {
        this.profile.moveToFirst() ;
        try{
            while (!(this.profile.isLast())){
                if (this.profile.getString(0).equals(login)){
                    return this.profile.getString(2) ;
                }
                this.profile.moveToNext();
            }
            if (this.profile.getString(0).equals(login)) {
                return this.profile.getString(2) ;
            }
            return null;
        }
        catch (Exception e){
            return null;
        }

    }

    // Renvoie l'adresse d'un certain login
    public String findAddress (String login) {
        this.profile.moveToFirst() ;
        try{
            while (!(this.profile.isLast())){
                if (this.profile.getString(0).equals(login)){
                    return this.profile.getString(3) ;
                }
                this.profile.moveToNext();
            }
            if (this.profile.getString(0).equals(login)) {
                return this.profile.getString(3) ;
            }
            return null;
        }
        catch (Exception e){
            return null;
        }
    }
    // Renvoie les préférences d'un certain login
    public String findPreference (String login) {
        this.profile.moveToFirst() ;
        try{
            while (!(this.profile.isLast())){
                if (this.profile.getString(0).equals(login)){
                    return this.profile.getString(4) ;
                }
                this.profile.moveToNext();
            }
            if (this.profile.getString(0).equals(login)) {
                return this.profile.getString(4) ;
            }
            return null;
        }
        catch (Exception e){
            return null;
        }
    }

}
