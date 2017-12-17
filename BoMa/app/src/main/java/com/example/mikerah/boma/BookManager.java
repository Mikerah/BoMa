package com.example.mikerah.boma;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Mikerah on 11/27/2017.
 */

public class BookManager {
    private static BookManager sBookManager;

    private List<Book> mBooks;

    public static BookManager get(Context context) {
        if (sBookManager == null){
            sBookManager = new BookManager(context);
        }
        return sBookManager;
    }

    private BookManager(Context context){
        mBooks = new ArrayList<>();
        for (int i=0; i<10;i++){
            Book book = new Book();
            book.setTitle("Book #" + i);
            book.setAuthor("Me #" + i);
            book.setGenre("Genre #" + i);
            book.setYearPublished(i);
            mBooks.add(book);
        }
    }

    public Book getBook(UUID id) {
        for (Book book: mBooks){
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooks() {
        return mBooks;
    }

    public void setBooks(List<Book> mBooks) {
        this.mBooks = mBooks;
    }
}
