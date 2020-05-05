package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import static com.example.secondtest.DatabaseContract.COLUMN_FRIENDS_LOGIN1;
import static com.example.secondtest.DatabaseContract.COLUMN_FRIENDS_LOGIN2;
import static com.example.secondtest.DatabaseContract.TABLE_FRIENDS;

public class FriendsDAO {
    private Cursor friends ;
    private DatabaseHelper dbh ;

    public FriendsDAO(Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.friends = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_FRIENDS),null);
    }

    //Il faut que les 2 soient amis l'un avec l'autre pour qu'ils soient réellement amis c'est ce que vérifie cette fonction
    public boolean areFriends(String login1, String login2){
        return (areFriendsHelp(login1, login2) && areFriendsHelp(login2,login1));
    }

    private boolean areFriendsHelp(String login1, String login2){
        this.friends.moveToFirst() ;
        try {
            while (!(this.friends.isLast())){
                if ((this.friends.getString(0).equals(login1)) && (this.friends.getString(2).equals(login2))){
                    return true ;
                }
                this.friends.moveToNext();
            }
            return ((this.friends.getString(0).equals(login1)) && (this.friends.getString(2).equals(login2)));
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean addFriends(String login1, String login2){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FRIENDS_LOGIN1, login1);
        contentValues.put(COLUMN_FRIENDS_LOGIN2, login2);
        long result = this.dbh.getDb().insert(TABLE_FRIENDS,null ,contentValues);
        return result != -1;
    }
}
