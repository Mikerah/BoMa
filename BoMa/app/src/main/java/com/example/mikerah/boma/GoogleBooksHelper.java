package com.example.mikerah.boma;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import com.google.common.base.Joiner;

import java.io.IOException;
import java.security.GeneralSecurityException;


/**
 * Created by Mikerah on 12/18/2017.
 */

public class GoogleBooksHelper {

    private static final String APPLICATION_NAME = "BoMa";

    public static Books GetBooksObj(Context context) {
        Books books = null;
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        try {
            books = new Books.Builder(AndroidHttp.newCompatibleTransport(),
                    jsonFactory, null)
            .setApplicationName(APPLICATION_NAME)
            .setGoogleClientRequestInitializer(new BooksRequestInitializer
                    (context.getString(R.string.google_books_api_key)))
            .build();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return books;
    }

    public static Book getBook(Books booksObj, Barcode barcode){
        Book book = new Book();

        try {
            Books.Volumes.List volumesList = booksObj.volumes().list(barcode.displayValue);
            Volumes volumes = volumesList.execute();
            Volume volume = volumes.getItems().get(0);
            Log.i("GoogleBooksHelper", "Volume is " + volume);
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
            String book_authors = Joiner.on(",").join(volumeInfo.getAuthors());
            String book_genre = Joiner.on(" ").join(volumeInfo.getCategories());
            String book_title = volumeInfo.getTitle();
            String book_year_published = volumeInfo.getPublishedDate();
            book.setGenre(book_genre);
            book.setAuthor(book_authors);
            book.setTitle(book_title);
            book.setYearPublished(book_year_published);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return book;
    }


}
