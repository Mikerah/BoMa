package com.example.mikerah.boma.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.mikerah.boma.Book;

import java.util.UUID;

/**
 * Created by Mikerah on 12/18/2017.
 */

public class BookCursorWrapper extends CursorWrapper{
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public BookCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Book getBook() {
        String uuidString = getString(getColumnIndex(BookDBSchema.BookTable
                .Cols.UUID));
        String title = getString(getColumnIndex(BookDBSchema.BookTable.Cols
                .TITLE));
        String author = getString(getColumnIndex(BookDBSchema.BookTable.Cols
                .AUTHOR));
        String genre = getString(getColumnIndex(BookDBSchema.BookTable.Cols
                .GENRE));
        String yearPublished = getString(getColumnIndex(BookDBSchema
                .BookTable.Cols.YEAR_PUBLISHED));
        String yearAcquired = getString(getColumnIndex(BookDBSchema
                .BookTable.Cols.YEAR_ACQUIRED));
        String personalRating = getString(getColumnIndex(BookDBSchema
                .BookTable.Cols.PERSONAL_RATING));
        int toKeep = getInt(getColumnIndex(BookDBSchema.BookTable.Cols
                .TO_KEEP));

        Book book = new Book(UUID.fromString(uuidString));
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setYearPublished(yearPublished);
        book.setYearAcquired(yearAcquired);
        book.setPersonalRating(personalRating);
        book.setToKeep(toKeep != 0);
        
        return book;
    }
}
