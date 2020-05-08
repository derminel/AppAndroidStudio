package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_LISTNB;
import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_LOGIN;
import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_STATUS;
import static com.example.secondtest.DatabaseContract.TABLE_MODIFIER;

public class ModifierDAO {
    private DatabaseHelper dbh;
    private Cursor modifier;

    // Constructeur
    public ModifierDAO(Context activePage) {
        this.dbh = new DatabaseHelper(activePage);
        this.modifier = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_MODIFIER), null);
    }

    /* PRÉ: La Listnumber (Id de la liste) d'une liste
       POST: Un Array de String contenant tous les admins de cette liste
     */
    public ArrayList<String> getAdmin(String listNb) {
        try {
            this.modifier.moveToFirst();
            ArrayList<String> admins = new ArrayList<>();
            while (!(this.modifier.isLast()) && !IsTableEmpty(this.modifier)) {
                if (this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Admin")) {
                    admins.add(this.modifier.getString(1));
                }
                this.modifier.moveToNext();
            }
            if (!IsTableEmpty(this.modifier) && this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Admin")) {
                admins.add(this.modifier.getString(1));
            }
            return admins;
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }

    /* PRÉ: La Listnumber (Id de la liste) d'une liste
       POST: Un Array de String contenant tous les Readers de cette liste
     */
    public ArrayList<String> getVisible(String listNb) {
        try {
            this.modifier.moveToFirst();
            ArrayList<String> admins = new ArrayList<>();
            while (!IsTableEmpty(this.modifier) &&!(this.modifier.isLast())) {
                if (this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Reader")) {
                    admins.add(this.modifier.getString(1));
                }
                this.modifier.moveToNext();
            }
            if (!IsTableEmpty(this.modifier) && this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Reader")) {
                admins.add(this.modifier.getString(1));
            }
            return admins;
        } catch (Exception e) {return new ArrayList<String>();}

    }

    // Vérifie si la table est vide
    public boolean IsTableEmpty(Cursor cursor) {
        return !(cursor.getCount() > 0);
    }

    /* PRE: La listnumber d'une liste, un login et le context d'une page
       POST: Met le statut du login au titre d'Admin pour la liste avec la listnumber donnée.
       Il renvoit:
               * 0 en cas de succès
               * -1 si le login est déjà Admin de cette liste
               * -2 si le login n'existe pas
     */
    public int updateAdmin(String listnb, String login, Context context) {
        if (alreadyAdmin(listnb, login)) {
            return -1;
        }
        if (alreadyVisible(listnb, login)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_MODIFIER_STATUS, "Admin");
            this.dbh.getDb().update(TABLE_MODIFIER,  contentValues, "LOGIN =? AND LISTNB=?", new String[] {login, listnb});
            return 0;
        }
        UserDAO userDAO = new UserDAO(context);
        if (!userDAO.exist(login)) {return -2;}
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODIFIER_STATUS, "Admin");
        contentValues.put(COLUMN_MODIFIER_LISTNB, listnb);
        contentValues.put(COLUMN_MODIFIER_LOGIN, login);
        this.dbh.getDb().insert(TABLE_MODIFIER, null, contentValues);
        return 0;
    }

    /* PRE: La listnumber d'une liste, un login et le context d'une page
          POST: Met le statut du login au titre de Reader pour la liste avec la listnumber donnée.
           Il renvoit:
               * 0 en cas de succès
               * -1 si le login est déjà Admin de cette liste
               * -2 si le login n'existe pas

        */
    public int updateVisible(String listnb, String login, Context context) {
        if (alreadyAdmin(listnb, login)) {
            return -1;
        }
        UserDAO userDAO = new UserDAO(context);
        if (!userDAO.exist(login)) {return -2;}
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODIFIER_STATUS, "Reader");
        contentValues.put(COLUMN_MODIFIER_LISTNB, listnb);
        contentValues.put(COLUMN_MODIFIER_LOGIN, login);
        this.dbh.getDb().insert(TABLE_MODIFIER, null, contentValues);
        return 0;
    }

    // Chèque si un login est admin d'une certaine liste
    public boolean alreadyAdmin(String listNb, String login) {
        try {
            this.modifier.moveToFirst();
            while (!(this.modifier.isLast()) && !IsTableEmpty(this.modifier)) {
                if (this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Admin") && this.modifier.getString(1) .equals(login)) {
                    return true;
                }
                this.modifier.moveToNext();
            }
            return this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Admin") && this.modifier.getString(1).equals(login);
        } catch (Exception e) { return false;}

    }

    // Chèque si un login est Reader d'une certaine liste
    public boolean alreadyVisible(String listNb, String login) {
        try {
            this.modifier.moveToFirst();
            while (!(this.modifier.isLast()) && !IsTableEmpty(this.modifier)) {
                if (this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Reader") && this.modifier.getString(1) .equals(login)) {
                    return true;
                }
                this.modifier.moveToNext();
            }
            return this.modifier.getString(2).equals(listNb) && this.modifier.getString(0).equals("Reader") && this.modifier.getString(1).equals(login);
        } catch (Exception e) {return false;}

    }

    /* PRE: Une listnumber listNb, un login et le contexte d'une page
       POST: Met le statut du login a Reader pour une certaine liste.
       IL renvoit 0 en cas de succès et -3 si on essaye d'enlever le créateur des admins.
     */
    public int deleteAdmin(String listNb, String login, Context context) {
        WishListDAO wishListDAO = new WishListDAO(context);
        if (login.equals(wishListDAO.getCreator(listNb))) {return -3;}
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODIFIER_STATUS, "Reader");
        dbh.getDb().update(TABLE_MODIFIER, contentValues, "LOGIN = ? AND LISTNB = ?", new String[] {login, listNb});
        return 0;
    }

    // Empeche le login de voir une certaine liste de souhait
    public void setInvisible(String listNb, String login) {
        dbh.getDb().delete(TABLE_MODIFIER, "LOGIN = ? AND LISTNB= ?", new String[] {login, listNb});
    }


}
