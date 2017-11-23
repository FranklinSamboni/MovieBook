package com.frank.moviebook.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by FRANK on 19/11/2017.
 */

public final class MovieContract {

    private MovieContract() {
    }

    public static class MovieEntry {
        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_VOTECOUNT = "voteCount";
        public static final String COLUMN_VOTEAVERAGE = "voteAverage";
        public static final String COLUMN_POSTERPATH = "posterPath";
        public static final String COLUMN_BACKDROPPATH = "backdropPath";
        public static final String COLUMN_CATEGORY = "category";
    }

    public static class SerieEntry {
        public static final String TABLE_NAME = "serie";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "originalName";
        public static final String COLUMN_DATE = "firstAirDate";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_VOTECOUNT = "voteCount";
        public static final String COLUMN_VOTEAVERAGE = "voteAverage";
        public static final String COLUMN_POSTERPATH = "posterPath";
        public static final String COLUMN_BACKDROPPATH = "backdropPath";
        public static final String COLUMN_CATEGORY = "category";
    }



}
