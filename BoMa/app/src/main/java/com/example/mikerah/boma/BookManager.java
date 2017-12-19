package com.example.mikerah.boma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mikerah.boma.database.BookBaseHelper;
import com.example.mikerah.boma.database.BookDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mikerah on 11/27/2017.
 */

public class BookManager {
    private static BookManager sBookManager;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static BookManager get(Context context) {
        if (sBookManager == null){
            sBookManager = new BookManager(context);
        }
        return sBookManager;
    }

    private BookManager(Context context){
        mContext= context.getApplicationContext();
        mDatabase = new BookBaseHelper(mContext).getWritableDatabase();
    }

    public void addBook(Book book){
        ContentValues values = getContentValues(book);
        mDatabase.insert(BookDBSchema.BookTable.NAME, null, values);
    }

    public Book getBook(UUID id) {

        return null;
    }

    public void updateBook(Book book){
        String uuidString = book.getId().toString();
        ContentValues values = getContentValues(book);

        mDatabase.update(BookDBSchema.BookTable.NAME, values, BookDBSchema
                .BookTable.Cols.UUID + " = ?", new String[]{ uuidString });
    }

    private static ContentValues getContentValues(Book book){
        ContentValues values = new ContentValues();
        values.put(BookDBSchema.BookTable.Cols.UUID, book.getId().toString());
        values.put(BookDBSchema.BookTable.Cols.TITLE, book.getTitle());
        values.put(BookDBSchema.BookTable.Cols.AUTHOR, book.getAuthor());
        values.put(BookDBSchema.BookTable.Cols.GENRE, book.getGenre());
        values.put(BookDBSchema.BookTable.Cols.YEAR_PUBLISHED, book.getYearPublished());
        values.put(BookDBSchema.BookTable.Cols.YEAR_ACQUIRED, book.getYearAcquired());
        values.put(BookDBSchema.BookTable.Cols.PERSONAL_RATING, book.getPersonalRating());
        values.put(BookDBSchema.BookTable.Cols.TO_KEEP, book.isToKeep() ? 1 :
                0);

        return values;
    }

    private Cursor queryBooks(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                BookDBSchema.BookTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return cursor;
    }

    public List<Book> getBooks() {
        return new ArrayList<>();
    }

    public void setBooks(List<Book> mBooks) {

    }
}
