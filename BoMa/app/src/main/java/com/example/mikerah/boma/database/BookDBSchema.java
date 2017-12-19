package com.example.mikerah.boma.database;

/**
 * Created by Mikerah on 12/5/2017.
 */

public class BookDBSchema {

    public static final class BookTable {
        public static final String NAME = "books";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String AUTHOR = "author";
            public static final String GENRE = "genre";
            public static final String YEAR_PUBLISHED = "year_published";
            public static final String YEAR_ACQUIRED = "year_acquired";
            public static final String PERSONAL_RATING = "personal_rating";
            public static final String TO_KEEP = "to_keep";
        }
    }
}
