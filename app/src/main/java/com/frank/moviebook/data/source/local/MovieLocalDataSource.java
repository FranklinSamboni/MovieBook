package com.frank.moviebook.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieLocalDataSource implements MovieRepository{

    private DataBaseHelper dbHelper;
    private Context context;

    public MovieLocalDataSource(Context context) {
        this.context = context;
        dbHelper = DataBaseHelper.getInstance(context);
    }

    /* PELICULAS
    * */

    @Override
    public long save(Movie movie){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COLUMN_ID, movie.getId());
        values.put(MovieContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MovieContract.MovieEntry.COLUMN_DATE, movie.getDate());
        values.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverView());
        values.put(MovieContract.MovieEntry.COLUMN_VOTECOUNT, movie.getVoteCount());
        values.put(MovieContract.MovieEntry.COLUMN_VOTEAVERAGE, movie.getVoteAverage());
        values.put(MovieContract.MovieEntry.COLUMN_POSTERPATH, movie.getPosterPath());
        values.put(MovieContract.MovieEntry.COLUMN_BACKDROPPATH, movie.getBackdropPath());
        values.put(MovieContract.MovieEntry.COLUMN_CATEGORY, movie.getCategory());

        long id = db.insert(MovieContract.MovieEntry.TABLE_NAME,null,values);
        db.close();
        return id;
    }

    @Override
    public void deleteMovies() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(MovieContract.MovieEntry.TABLE_NAME,null,null);
        db.close();
    }

    @Override
    public void getMovies(final ListMovieCallBack callBack){

        List<Movie> movies = getMovies();
        if(movies != null){
            callBack.onMoviesLoaded(movies);
        }
        else{
            callBack.onError("No fue posible obtener la información de peliculas de la base de datos.");
        }
    }

    @Override
    public Movie getMovieById(int id) {
        String selection = MovieContract.MovieEntry.COLUMN_ID + " = ? ";
        String[] selectionArgs = { String.valueOf(id) };
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query( MovieContract.MovieEntry.TABLE_NAME, null,selection,selectionArgs,null,null,null);
        Movie movie = null;
        try{
            if (cursor.moveToFirst()) {
                movie = cursorToMovie(cursor);
            }

        }catch(Exception e){
            Log.e("getMovieById", e.getMessage());
        }
        finally{
            cursor.close();
        }
        return movie;
    }

    private List<Movie> getMovies(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query( MovieContract.MovieEntry.TABLE_NAME, null,null,null,null,null,null);

        List<Movie> movies = new ArrayList<>();

        try{
            if (cursor.moveToFirst()) {
                do {
                    Movie movie = cursorToMovie(cursor);
                    movies.add(movie);

                } while (cursor.moveToNext());
            }
        }catch(Exception e){
            Log.e("getMoviesByCategory", e.getMessage());
            return null;
        }
        finally{
            cursor.close();
        }
        return movies;
    }


    /* SERIES
    * */
    @Override
    public long save(Serie serie) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MovieContract.SerieEntry.COLUMN_ID, serie.getId());
        values.put(MovieContract.SerieEntry.COLUMN_TITLE, serie.getOriginalName());
        values.put(MovieContract.SerieEntry.COLUMN_DATE, serie.getDate());
        values.put(MovieContract.SerieEntry.COLUMN_OVERVIEW, serie.getOverview());
        values.put(MovieContract.SerieEntry.COLUMN_VOTECOUNT, serie.getVoteCount());
        values.put(MovieContract.SerieEntry.COLUMN_VOTEAVERAGE, serie.getVoteAverage());
        values.put(MovieContract.SerieEntry.COLUMN_POSTERPATH, serie.getPosterPath());
        values.put(MovieContract.SerieEntry.COLUMN_BACKDROPPATH, serie.getBackdropPath());
        values.put(MovieContract.SerieEntry.COLUMN_CATEGORY, serie.getCategory());

        long id = db.insert(MovieContract.SerieEntry.TABLE_NAME,null,values);
        db.close();
        return id;
    }

    @Override
    public void deleteSeries() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(MovieContract.SerieEntry.TABLE_NAME,null,null);
        db.close();
    }

    @Override
    public void getSeries(ListSerieCallBack callBack) {
        List<Serie> series = getSeries();
        if(series != null){
            callBack.onSeriesLoaded(series);
        }
        else{
            callBack.onError("No fue posible obtener la información de las series de la base de datos.");
        }
    }

    private List<Serie> getSeries() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MovieContract.SerieEntry.TABLE_NAME,null,null, null,null, null,null);
        List<Serie> series = new ArrayList<>();

        try{
            if (cursor.moveToFirst()) {
                do {
                    Serie serie = new Serie();
                    serie.setId(cursor.getInt(0));
                    serie.setOriginalName(cursor.getString(1));
                    serie.setDate(cursor.getString(2));
                    serie.setOverview(cursor.getString(3));
                    serie.setVoteCount(cursor.getInt(4));
                    serie.setVoteAverage(cursor.getDouble(5));
                    serie.setPosterPath(cursor.getString(6));
                    serie.setBackdropPath(cursor.getString(7));
                    serie.setCategory(cursor.getInt(8));
                    series.add(serie);

                } while (cursor.moveToNext());
            }
        }catch(Exception e){
            Log.e("getSeriesByCategory", e.getMessage());
            return null;
        }
        finally{
            cursor.close();
        }
        return series;
    }


    private Movie cursorToMovie(Cursor cursor){
        Movie movie = new Movie();
        movie.setId(cursor.getInt(0));
        movie.setTitle(cursor.getString(1));
        movie.setDate(cursor.getString(2));
        movie.setOverView(cursor.getString(3));
        movie.setVoteCount(cursor.getInt(4));
        movie.setVoteAverage(cursor.getDouble(5));
        movie.setPosterPath(cursor.getString(6));
        movie.setBackdropPath(cursor.getString(7));
        movie.setCategory(cursor.getInt(8));
        return movie;
    }

}
