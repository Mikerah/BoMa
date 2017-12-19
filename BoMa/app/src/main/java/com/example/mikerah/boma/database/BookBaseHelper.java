package com.example.mikerah.boma.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mikerah on 12/5/2017.
 */

public class BookBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "bookManager.db";

    public BookBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BookDBSchema.BookTable.NAME + "(" + " " +
                "_id integer primary key autoincrement, " + BookDBSchema
                .BookTable.Cols.UUID + ", " + BookDBSchema.BookTable.Cols
                .TITLE + ", " + BookDBSchema.BookTable.Cols.AUTHOR + ", " +
                BookDBSchema.BookTable.Cols.GENRE + ", " + BookDBSchema
                .BookTable.Cols.YEAR_PUBLISHED + ", " + BookDBSchema
                .BookTable.Cols.YEAR_ACQUIRED + ", " + BookDBSchema.BookTable
                .Cols.PERSONAL_RATING + ", " + BookDBSchema.BookTable.Cols
                .TO_KEEP + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
