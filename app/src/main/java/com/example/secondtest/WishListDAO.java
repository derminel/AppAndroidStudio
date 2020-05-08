/*package com.example.secondtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_1;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_3;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_2;
import static com.example.secondtest.DatabaseContract.COLUMN_NAME_TITLE_LISTS_4;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_LISTS;
import static com.example.secondtest.DatabaseContract.TABLE_NAME_USERS;

public class WishListDAO {
    private Cursor wishlist ;
    private DatabaseHelper dbh ;

    public WishListDAO(Context context){
        this.dbh = new DatabaseHelper(context);
        this.wishlist = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_NAME_LISTS),null);
    }

    public boolean updateWishlist(String name,boolean publicc) {
        SQLiteDatabase db =  dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE_LISTS_1,name);
        contentValues.put(COLUMN_NAME_TITLE_LISTS_3,String.valueOf(publicc));


        db.update(TABLE_NAME_LISTS, contentValues, "Name = ?",new String[] {name});
        return true;
    }
}*/

package com.example.secondtest;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteDatabase;
        import android.widget.Toast;

        import java.util.ArrayList;

        import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_LISTNB;
        import static com.example.secondtest.DatabaseContract.COLUMN_CONTENT_PRODUCTNB;
        import static com.example.secondtest.DatabaseContract.COLUMN_FRIENDS_LOGIN1;
        import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_CREATOR;
        import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_DESCRIPTION;
        import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_LISTNB;
        import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_NAME;
        import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_PUBLIC;
        import static com.example.secondtest.DatabaseContract.COLUMN_LISTS_RECIPIENT;
        import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_LISTNB;
        import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_LOGIN;
        import static com.example.secondtest.DatabaseContract.COLUMN_MODIFIER_STATUS;
        import static com.example.secondtest.DatabaseContract.COLUMN_USERS_LOGIN;
        import static com.example.secondtest.DatabaseContract.TABLE_CONTENT;
        import static com.example.secondtest.DatabaseContract.TABLE_FRIENDS;
        import static com.example.secondtest.DatabaseContract.TABLE_LISTS;
        import static com.example.secondtest.DatabaseContract.TABLE_MODIFIER;
        import static com.example.secondtest.DatabaseContract.TABLE_USERS;

public class WishListDAO {
    private Cursor wishlists;
    private DatabaseHelper dbh ;
    private FriendsDAO friendsDAO;

    public WishListDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.wishlists = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_LISTS),null);
        this.friendsDAO = new FriendsDAO(activePage);
    }

    //pre: recoit un listNb du wishList qui figure deja dans la base de données
    //post: retourne le nom de cette liste
    public String getName(String listNb){
         Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_LISTS,
                COLUMN_LISTS_LISTNB) ,new String[] {listNb});
        if (cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    //pre: recoit un listNb du wishList qui figure deja dans la base de données
    //post: retourne si son accès est public ou non
    public boolean getAccess(String listNb){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_LISTS,
                COLUMN_LISTS_LISTNB) ,new String[] {listNb});
        if (cursor.getCount() <= 0){
            return false;
        }
        cursor.moveToFirst();
        return (cursor.getString(1).equals("1"));
    }

    public String getCreator(String listNb){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_LISTS,
                COLUMN_LISTS_LISTNB) ,new String[] {listNb});
        if (cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToFirst();
        return cursor.getString(5);
    }

    public String getRecipient(String listNb){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_LISTS,
                COLUMN_LISTS_LISTNB) ,new String[] {listNb});
        if (cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToFirst();
        return cursor.getString(4);
    }

    public boolean addStatus (String status, String login, String listNb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MODIFIER_STATUS, status);
        contentValues.put(COLUMN_MODIFIER_LOGIN, login);
        contentValues.put(COLUMN_MODIFIER_LISTNB, listNb);
        long result = this.dbh.getDb().insert(TABLE_MODIFIER,null ,contentValues);
        return result != -1;
    }

    public String lineCounter (){
        return String.valueOf(DatabaseUtils.queryNumEntries(this.dbh.getDb(), TABLE_LISTS));
    }

    public boolean addList(String name, boolean access, String listNb, String description, String recipient, String creator){
        //Ajouter à la table listes
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LISTS_NAME, name);
        contentValues.put(COLUMN_LISTS_PUBLIC, access);
        contentValues.put(COLUMN_LISTS_LISTNB, listNb);
        contentValues.put(COLUMN_LISTS_DESCRIPTION, description);
        contentValues.put(COLUMN_LISTS_RECIPIENT, recipient);
        contentValues.put(COLUMN_LISTS_CREATOR, creator);
        long result = this.dbh.getDb().insert(TABLE_LISTS,null ,contentValues);

        //Ajouter le créateur à la table modifier
        ContentValues contentValuesModifier = new ContentValues();
        contentValuesModifier.put(COLUMN_MODIFIER_LISTNB, listNb);
        contentValuesModifier.put(COLUMN_MODIFIER_LOGIN, creator);
        contentValuesModifier.put(COLUMN_MODIFIER_STATUS, "Admin");
        this.dbh.getDb().insert(TABLE_MODIFIER,null ,contentValuesModifier);

        //récupère les amis de créateur
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_FRIENDS,
                COLUMN_FRIENDS_LOGIN1) ,new String[] {creator});

        //récupère la wishlist de
        if ((cursor.getCount()) > 0 && (access)){ //ajoute tous les amis du créateur comme reader de la liste si elle est publique
            cursor.moveToFirst();
            while (!(cursor.isLast())){
                if(friendsDAO.areFriends(creator,cursor.getString(1))){
                    contentValuesModifier = new ContentValues();
                    contentValuesModifier.put(COLUMN_MODIFIER_LISTNB, listNb);
                    contentValuesModifier.put(COLUMN_MODIFIER_LOGIN, cursor.getString(1));
                    contentValuesModifier.put(COLUMN_MODIFIER_STATUS, "Reader");
                    this.dbh.getDb().insert(TABLE_MODIFIER,null ,contentValuesModifier);
                }
                cursor.moveToNext();
            }
            if(friendsDAO.areFriends(creator,cursor.getString(1))){
                contentValuesModifier = new ContentValues();
                contentValuesModifier.put(COLUMN_MODIFIER_LISTNB, listNb);
                contentValuesModifier.put(COLUMN_MODIFIER_LOGIN, cursor.getString(1));
                contentValuesModifier.put(COLUMN_MODIFIER_STATUS, "Reader");
                this.dbh.getDb().insert(TABLE_MODIFIER,null ,contentValuesModifier);
            }
        }
        return result != -1;
    }

    public Integer deleteList(String name) {
        return this.dbh.getDb().delete(TABLE_LISTS, COLUMN_LISTS_NAME + " = ?",new String[] {name});
    }

    public boolean updateWishlist(String listnb,String name,String Recipient, boolean PublicList) {
        SQLiteDatabase db =  dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LISTS_NAME,name);
        contentValues.put(COLUMN_LISTS_RECIPIENT,Recipient);
        contentValues.put(COLUMN_LISTS_PUBLIC, PublicList);
        db.update(TABLE_LISTS, contentValues, "LISTNB = ?",new String[] {listnb});
        return true;
    }

    public ArrayList<String> getWishLists(String login){
        Cursor cursor = this.dbh.getDb().rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_MODIFIER,
                COLUMN_MODIFIER_LOGIN) ,new String[] {login});
        ArrayList<String> wishlists = new ArrayList<String>();
        if (cursor.getCount() <= 0){
            return wishlists;
        }
        cursor.moveToFirst();
        while (!(cursor.isLast())){
            if (!(cursor.getString(0).equals("Invisible"))){
                wishlists.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        if (!(cursor.getString(0).equals("Invisible"))){
            wishlists.add(cursor.getString(2));
        }
        return wishlists;
    }
}
