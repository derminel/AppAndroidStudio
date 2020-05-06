package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

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
                if ((this.friends.getString(0).equals(login1)) && (this.friends.getString(1).equals(login2))){
                    return true ;
                }
                this.friends.moveToNext();
            }
            return ((this.friends.getString(0).equals(login1)) && (this.friends.getString(1).equals(login2)));
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

    public Integer deleteFriend(String login1, String login2) {
        Integer firstDelete = this.dbh.getDb().delete(TABLE_FRIENDS, COLUMN_FRIENDS_LOGIN1 + " = ? AND "
                + COLUMN_FRIENDS_LOGIN2 + " = ?",new String[] {login1, login2});
        Integer secondDelete = this.dbh.getDb().delete(TABLE_FRIENDS, COLUMN_FRIENDS_LOGIN1 + " = ? AND "
                + COLUMN_FRIENDS_LOGIN2 + " = ?",new String[] {login2, login1});
        return firstDelete + secondDelete;
    }

    public Integer refuseFriend(String login1, String login2){
        Integer refuse = this.dbh.getDb().delete(TABLE_FRIENDS, COLUMN_FRIENDS_LOGIN1 + " = ? AND "
                + COLUMN_FRIENDS_LOGIN2 + " = ?",new String[] {login2, login1});
        return refuse;
    }

    public boolean acceptFriend(String login1, String login2){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FRIENDS_LOGIN1, login1);
        contentValues.put(COLUMN_FRIENDS_LOGIN2, login2);
        long result = this.dbh.getDb().insert(TABLE_FRIENDS,null ,contentValues);
        return result != -1;
    }

    //Cette fonction renvoie les demandes d'ami envoyées durant l'absence de l'utilisateur
    public ArrayList<String> getRequests(String login){
        ArrayList<String> requests = getRequestsHelp(login);
        ArrayList<String> cleanRequests = new ArrayList<String>();
        for (String request : requests){
            boolean canAdded = true;
            this.friends.moveToFirst() ;
            try {
                while (!(this.friends.isLast())){
                    if ((this.friends.getString(0).equals(login)) && (this.friends.getString(1).equals(request))){
                        canAdded = false;
                        break;
                    }
                    this.friends.moveToNext();
                }
                if ((this.friends.getString(0).equals(login)) && (this.friends.getString(1).equals(request))){
                    canAdded = false;
                }
                if (canAdded){
                    cleanRequests.add(request);
                }
            }
            catch (Exception e){
            }
        }
        return cleanRequests;
    }

    private ArrayList<String> getRequestsHelp(String login){
        ArrayList<String> requestsHelp = new ArrayList<String>();
        this.friends.moveToFirst() ;
        try {
            while (!(this.friends.isLast())){
                if ((this.friends.getString(1).equals(login))){
                    requestsHelp.add(this.friends.getString(0));
                }
                this.friends.moveToNext();
            }
            if ((this.friends.getString(1).equals(login))) {
                requestsHelp.add(this.friends.getString(0));
            }
        }
        catch (Exception e){
        }
        return requestsHelp;
    }

    public ArrayList<String> getFriends(String login){
        ArrayList<String> friends = new ArrayList<String>();
        this.friends.moveToFirst() ;
        try {
            while (!(this.friends.isLast())) {
                if (this.friends.getString(0).equals(login)) {
                    int postitionBeforeCondition = this.friends.getPosition();
                    if (areFriends(login, this.friends.getString(1))) {
                        this.friends.moveToPosition(postitionBeforeCondition);
                        friends.add(this.friends.getString(1));
                    }
                }
                this.friends.moveToNext();
            }
            if (this.friends.getString(0).equals(login)) {
                int postitionBeforeCondition = this.friends.getPosition();
                if (areFriends(login, this.friends.getString(1))) {
                    this.friends.moveToPosition(postitionBeforeCondition);
                    friends.add(this.friends.getString(1));
                }
            }
        }
        catch (Exception e){
        }
        return friends;
    }
}
