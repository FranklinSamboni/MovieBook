package com.frank.moviebook.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by FRANK on 19/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "moviebook.db";

    private static final String SQL_CREATE_MOVIE_ENTRY =
            "CREATE TABLE IF NOT EXISTS " + MovieContract.MovieEntry.TABLE_NAME + " ( "
                    +  MovieContract.MovieEntry.COLUMN_ID + " INTEGER PRYMARY KEY, "
                    +  MovieContract.MovieEntry.COLUMN_TITLE + " TEXT, "
                    +  MovieContract.MovieEntry.COLUMN_DATE + " TEXT, "
                    +  MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, "
                    +  MovieContract.MovieEntry.COLUMN_VOTECOUNT + " INTEGER, "
                    +  MovieContract.MovieEntry.COLUMN_VOTEAVERAGE + " DOUBLE, "
                    +  MovieContract.MovieEntry.COLUMN_POSTERPATH + " TEXT, "
                    +  MovieContract.MovieEntry.COLUMN_BACKDROPPATH + " TEXT, "
                    +  MovieContract.MovieEntry.COLUMN_CATEGORY + " INTEGER )";

    private static final String SQL_CREATE_SERIE_ENTRY =
            "CREATE TABLE IF NOT EXISTS " + MovieContract.SerieEntry.TABLE_NAME + " ( "
                    +  MovieContract.SerieEntry.COLUMN_ID + " INTEGER PRYMARY KEY, "
                    +  MovieContract.SerieEntry.COLUMN_TITLE + " TEXT, "
                    +  MovieContract.SerieEntry.COLUMN_DATE + " TEXT, "
                    +  MovieContract.SerieEntry.COLUMN_OVERVIEW + " TEXT, "
                    +  MovieContract.SerieEntry.COLUMN_VOTECOUNT + " INTEGER, "
                    +  MovieContract.SerieEntry.COLUMN_VOTEAVERAGE + " DOUBLE, "
                    +  MovieContract.SerieEntry.COLUMN_POSTERPATH + " TEXT, "
                    +  MovieContract.SerieEntry.COLUMN_BACKDROPPATH + " TEXT, "
                    +  MovieContract.SerieEntry.COLUMN_CATEGORY + " INTEGER )";

    private static final String SQL_DELETE_MOVIE_ENTRY =
            "DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME;

    private static final String SQL_DELETE_SERIE_ENTRY =
            "DROP TABLE IF EXISTS " + MovieContract.SerieEntry.TABLE_NAME;


    private static DataBaseHelper INSTANCE;

    public static DataBaseHelper getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new DataBaseHelper(context);
        }
        return INSTANCE;
    }

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_ENTRY);
        sqLiteDatabase.execSQL(SQL_CREATE_SERIE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_MOVIE_ENTRY);
        sqLiteDatabase.execSQL(SQL_DELETE_SERIE_ENTRY);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
