package com.example.secondtest;


import static com.example.secondtest.DatabaseHelper.DATABASE_NAME;

public class DatabaseContract {
    // Noms (pas requêtes--> DAO) (statique ?)
    public static final int VERSION = 1;

    public static final String TABLE_CONTENT = "Content";
    public static final String COLUMN_CONTENT_LISTNB = "ListNb";
    public static final String COLUMN_CONTENT_PRODUCTNB = "ProductNb";

    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_USERS_LOGIN = "Login";
    public static final String COLUMN_USERS_NAME= "Name";
    public static final String COLUMN_USERS_LASTNAME = "LastName";
    public static final String COLUMN_USERS_ADDRESS = "Address";
    public static final String COLUMN_USERS_PHOTO = "Photo";
    public static final String COLUMN_USERS_PREFERENCES = "Preferences";
    public static final String COLUMN_USERS_PASSWORD = "Password";

    public static final String TABLE_FRIENDS = "Friends";
    public static final String COLUMN_FRIENDS_LOGIN1 = "Login1";
    public static final String COLUMN_FRIENDS_LOGIN2 = "Login2";

    public static final String TABLE_PRODUCTS = "Products";
    public static final String COLUMN_PRODUCTS_PRODUCTNB = "ProductNb";
    public static final String COLUMN_PRODUCTS_NAME = "Name";
    public static final String COLUMN_PRODUCTS_PHOTO = "Photo";
    public static final String COLUMN_PRODUCTS_PRICE = "Price";
    public static final String COLUMN_PRODUCTS_INFO = "Info";
    public static final String COLUMN_PRODUCTS_CATEGORY = "Category";
    public static final String COLUMN_PRODUCTS_WEBSITE = "Website";

    public static final String TABLE_LISTS = "Lists";
    public static final String COLUMN_LISTS_NAME = "Name";
    public static final String COLUMN_LISTS_PUBLIC = "Public";
    public static final String COLUMN_LISTS_LISTNB = "ListNb";
    public static final String COLUMN_LISTS_DESCRIPTION = "Description";
    public static final String COLUMN_LISTS_RECIPIENT = "Recipient";
    public static final String COLUMN_LISTS_CREATOR = "Creator";

    public static final String TABLE_LIKE = "Like";
    public static final String COLUMN_LIKE_LISTNB = "ListNb";
    public static final String COLUMN_LIKE_COUNT = "Count";
    public static final String COLUMN_LIKE_PRODUCTNB = "ProductNb";
    public static final String COLUMN_LIKE_LOGIN = "Login";

    public static final String TABLE_MODIFIER = "Modifier";
    public static final String COLUMN_MODIFIER_STATUS = "Status";
    public static final String COLUMN_MODIFIER_LOGIN = "Login";
    public static final String COLUMN_MODIFIER_LISTNB = "ListNb";

    // Creation de la table 'content'
    public static final String SQL_CREATE_CONTENT =
            "CREATE TABLE " + TABLE_CONTENT + " (" + COLUMN_CONTENT_LISTNB + " TEXT, " + COLUMN_CONTENT_PRODUCTNB + " TEXT)";

    //Creation de la table 'users'
    public static final String SQL_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" + COLUMN_USERS_LOGIN + " TEXT, " + COLUMN_USERS_NAME  + " TEXT, " + COLUMN_USERS_LASTNAME + " TEXT, " + COLUMN_USERS_ADDRESS + " TEXT, " +
                    COLUMN_USERS_PHOTO + " BLOB, " + COLUMN_USERS_PREFERENCES + " TEXT, " + COLUMN_USERS_PASSWORD + " TEXT)";

    //Creastion de la table 'friends'
    public static final String SQL_CREATE_FRIENDS =
            "CREATE TABLE " + TABLE_FRIENDS + " (" + COLUMN_FRIENDS_LOGIN1 + " TEXT, " + COLUMN_FRIENDS_LOGIN2 + " TEXT)";

    //Creation de la table 'products'
    public static final String SQL_CREATE_PRODUCTS =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" + COLUMN_PRODUCTS_PRODUCTNB + " TEXT, " + COLUMN_PRODUCTS_NAME  + " TEXT, " + COLUMN_PRODUCTS_PHOTO + " BLOB, " + COLUMN_PRODUCTS_PRICE + " INT, " +
                                    COLUMN_PRODUCTS_INFO + " TEXT, " + COLUMN_PRODUCTS_CATEGORY + " TEXT, " + COLUMN_PRODUCTS_WEBSITE + " TEXT)";

    //Creation de la table 'lists'
    public static final String SQL_CREATE_LISTS =
            "CREATE TABLE " + TABLE_LISTS + " (" + COLUMN_LISTS_NAME + " TEXT, " + COLUMN_LISTS_PUBLIC  + " BOOL, " + COLUMN_LISTS_LISTNB + " TEXT, " + COLUMN_LISTS_DESCRIPTION + " TEXT, " +
                                    COLUMN_LISTS_RECIPIENT + " TEXT, " + COLUMN_LISTS_CREATOR + " TEXT)";

    //Creation de la table 'likes'
    public static final String SQL_CREATE_LIKE =
            "CREATE TABLE " + TABLE_LIKE + " (" + COLUMN_LIKE_LISTNB + " TEXT, " + COLUMN_LIKE_COUNT  + " TINYINT, " + COLUMN_LIKE_PRODUCTNB + " TEXT, " + COLUMN_LIKE_LOGIN + " TEXT)";

    //Creation de la table 'modifiers'
    public static final String SQL_CREATE_MODIFIER =
            "CREATE TABLE " + TABLE_MODIFIER + " (" + COLUMN_MODIFIER_STATUS + " TEXT, " + COLUMN_MODIFIER_LOGIN  + " TEXT, " + COLUMN_MODIFIER_LISTNB + " TEXT)";


    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DATABASE_NAME;

}
