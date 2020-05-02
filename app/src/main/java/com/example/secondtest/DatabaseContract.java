package com.example.secondtest;


import static com.example.secondtest.DatabaseHelper.DATABASE_NAME;

public class DatabaseContract {
    // Noms (pas requêtes--> DAO) (statique ?)
    public static final int VERSION = 1;

    public static final String TABLE_NAME_CONTENT = "Content";
    public static final String COLUMN_NAME_TITLE_CONTENT_1 = "ListNb";
    public static final String COLUMN_NAME_TITLE_CONTENT_2 = "ProductNb";

    public static final String TABLE_NAME_USERS = "Users";
    public static final String COLUMN_NAME_TITLE_USERS_1 = "Login";
    public static final String COLUMN_NAME_TITLE_USERS_2 = "Name";
    public static final String COLUMN_NAME_TITLE_USERS_3 = "LastName";
    public static final String COLUMN_NAME_TITLE_USERS_4 = "Address";
    public static final String COLUMN_NAME_TITLE_USERS_5 = "Preferences";
    public static final String COLUMN_NAME_TITLE_USERS_6 = "Photo";
    public static final String COLUMN_NAME_TITLE_USERS_7 = "Password";

    public static final String TABLE_NAME_FRIENDS = "Friends";
    public static final String COLUMN_NAME_TITLE_FRIENDS_1 = "Login1";
    public static final String COLUMN_NAME_TITLE_FRIENDS_2 = "Login2";

    public static final String TABLE_NAME_PRODUCTS = "Products";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_1 = "ProductNb";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_2 = "Name";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_3 = "Photo";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_4 = "Price";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_5 = "Info";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_6 = "Category";
    public static final String COLUMN_NAME_TITLE_PRODUCTS_7 = "Website";

    public static final String TABLE_NAME_LISTS = "Lists";
    public static final String COLUMN_NAME_TITLE_LISTS_1 = "Name";
    public static final String COLUMN_NAME_TITLE_LISTS_2 = "Name";
    public static final String COLUMN_NAME_TITLE_LISTS_3 = "Name";
    public static final String COLUMN_NAME_TITLE_LISTS_4 = "Name";
    public static final String COLUMN_NAME_TITLE_LISTS_5 = "Name";
    public static final String COLUMN_NAME_TITLE_LISTS_6 = "Name";

    public static final String TABLE_NAME_LIKE = "Like";
    public static final String COLUMN_NAME_TITLE_LIKE_1 = "ProductNb";

    public static final String TABLE_NAME_MODIFIER = "Modifier";


    public static final String SQL_CREATE_CONTENT =
            "CREATE TABLE Content (ListNb TEXT, ProductNb TEXT)";

    public static final String SQL_CREATE_USERS =
            "CREATE TABLE Users (Login TEXT NOT NULL, Name TEXT NOT NULL, LastName TEXT NOT NULL, Address TEXT NOT NULL, Preferences TEXT, Photo TEXT, Password TEXT NOT NULL)";

    public static final String SQL_CREATE_FRIENDS =
            "CREATE TABLE Friends (Login1 TEXT, Login2 TEXT)";

    public static final String SQL_CREATE_PRODUCTS =
            "CREATE TABLE Products (Login1 TEXT, Login2 TEXT)";

    public static final String SQL_CREATE_LISTS =
            "CREATE TABLE Lists (Login1 TEXT, Login2 TEXT)";

    public static final String SQL_CREATE_MODIFIER =
            "CREATE TABLE Modifier (Status TEXT, Login TEXT, ListNb TEXT)";

    public static final String SQL_CREATE_LIKE =
            "CREATE TABLE Like (Status TEXT, Login TEXT, ListNb TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DATABASE_NAME;

}
