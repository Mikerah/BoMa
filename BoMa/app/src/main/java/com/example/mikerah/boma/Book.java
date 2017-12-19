package com.example.mikerah.boma;

import java.util.UUID;

/**
 * Created by Mikerah on 12/5/2017.
 */

public class Book {

    private UUID mId;
    private String mTitle;
    private String mAuthor;
    private String mGenre;
    private String mYearPublished;
    private String mYearAcquired;
    private String mPersonalRating;
    private boolean mToKeep;

    public Book(){
        this(UUID.randomUUID());

    }

    public Book(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getYearPublished() {
        return mYearPublished;
    }

    public void setYearPublished(String mYearPublsihed) {
        this.mYearPublished = mYearPublsihed;
    }

    public String getYearAcquired() {
        return mYearAcquired;
    }

    public void setYearAcquired(String mYearAcquired) {
        this.mYearAcquired = mYearAcquired;
    }

    public String getPersonalRating() {
        return mPersonalRating;
    }

    public void setPersonalRating(String mPersonalRating) {
        this.mPersonalRating = mPersonalRating;
    }

    public boolean isToKeep() {
        return mToKeep;
    }

    public void setToKeep(boolean mToKeep) {
        this.mToKeep = mToKeep;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String mGenre) {
        this.mGenre = mGenre;
    }

}
