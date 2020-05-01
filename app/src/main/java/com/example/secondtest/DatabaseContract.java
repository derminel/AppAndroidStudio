package com.example.secondtest;


public class DatabaseContract {
    // Noms (pas requêtes--> DAO) (statique ?)
    public static final String TABLE_NAME = "User";
    public static final int VERSION = 1;
    public static final String COLUMN_NAME_TITLE = "Login";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.TABLE_NAME + " (" +
                    DatabaseContract.COLUMN_NAME_TITLE + " TEXT )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME;
}
