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
        import static com.example.secondtest.DatabaseContract.TABLE_LISTS;
        import static com.example.secondtest.DatabaseContract.TABLE_MODIFIER;
        import static com.example.secondtest.DatabaseContract.TABLE_USERS;

public class WishListDAO {
    private Cursor wishlists;
    private DatabaseHelper dbh ;

    public WishListDAO (Context activePage){
        this.dbh = new DatabaseHelper(activePage);
        this.wishlists = dbh.getDb().rawQuery(String.format("SELECT * FROM %s", TABLE_LISTS),null);
    }

    public ArrayList<String> getWishListsNameDb (){
        ArrayList<String> wishListsName = new ArrayList<String>();
        this.wishlists.moveToFirst() ;
        try{
            while (!(this.wishlists.isLast())){
                wishListsName.add(this.wishlists.getString(0));
                this.wishlists.moveToNext();
            }
            wishListsName.add(this.wishlists.getString(0));
            return wishListsName;
        }
        catch (Exception e){
            return wishListsName;
        }
    }

    //pre: recoit un listNb du wishList qui figure deja dans la base de données
    //post: retourne le nom de cette liste
    public String getName(String listNb){
        this.wishlists.moveToFirst() ;
        try{
            while (!(this.wishlists.isLast())){
                if (this.wishlists.getString(2).equals(listNb)){
                    return this.wishlists.getString(0) ;
                }
                this.wishlists.moveToNext();
            }
            return this.wishlists.getString(0) ;
        }
        catch (Exception e){
            return null;
        }
    }

    //pre: recoit un listNb du wishList qui figure deja dans la base de données
    //post: retourne si son accès est public ou non
    public boolean getAccess(String listNb){
        String access = null;
        this.wishlists.moveToFirst() ;
        while (!(this.wishlists.isLast())){
            if (this.wishlists.getString(2).equals(listNb)){
                access = this.wishlists.getString(1) ;
                return access.equals("1");
            }
            this.wishlists.moveToNext();
        }
        access = this.wishlists.getString(1) ;
        return access.equals("1");
    }

    public String getPassword(String login){
        this.wishlists.moveToFirst() ;
        try{
            while (!(this.wishlists.isLast())){
                if (this.wishlists.getString(0).equals(login)){
                    return this.wishlists.getString(6) ;
                }
                this.wishlists.moveToNext();
            }
            return this.wishlists.getString(6) ;
        }
        catch (Exception e){
            return null;
        }
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
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LISTS_NAME, name);
        contentValues.put(COLUMN_LISTS_PUBLIC, access);
        contentValues.put(COLUMN_LISTS_LISTNB, listNb);
        contentValues.put(COLUMN_LISTS_DESCRIPTION, description);
        contentValues.put(COLUMN_LISTS_RECIPIENT, recipient);
        contentValues.put(COLUMN_LISTS_CREATOR, creator);
        long result = this.dbh.getDb().insert(TABLE_LISTS,null ,contentValues);
        return result != -1;
    }

    public Integer deleteList(String name) {
        return this.dbh.getDb().delete(TABLE_LISTS, COLUMN_LISTS_NAME + " = ?",new String[] {name});
    }

}
