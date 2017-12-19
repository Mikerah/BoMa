package com.example.mikerah.boma;

import android.content.Context;
import android.util.Log;

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
    }

    public void addBook(Book book){
        mBooks.add(book);
    }

    public Book getBook(UUID id) {
        for (Book book: mBooks){
            if(book.getId().equals(id)){
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
