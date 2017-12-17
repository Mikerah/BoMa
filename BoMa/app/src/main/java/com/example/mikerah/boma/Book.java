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
    private int mYearPublished;
    private int mYearAquired;
    private int mPersonalRating;
    private boolean mToKeep;

    public Book(){
        mId = UUID.randomUUID();

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

    public int getYearPublished() {
        return mYearPublished;
    }

    public void setYearPublished(int mYearPublsihed) {
        this.mYearPublished = mYearPublsihed;
    }

    public int getYearAquired() {
        return mYearAquired;
    }

    public void setYearAquired(int mYearAquired) {
        this.mYearAquired = mYearAquired;
    }

    public int getPersonalRating() {
        return mPersonalRating;
    }

    public void setPersonalRating(int mPersonalRating) {
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
