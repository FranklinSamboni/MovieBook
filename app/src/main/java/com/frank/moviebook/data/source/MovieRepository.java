package com.frank.moviebook.data.source;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;

import java.util.List;

/**
 * Created by FRANK on 19/11/2017.
 */

public interface MovieRepository {

    interface ListMovieCallBack{
        void onMoviesLoaded(List<Movie> data);
        void onError(String message);
    }

    interface ListSerieCallBack{
        void onSeriesLoaded(List<Serie> data);
        void onError(String message);
    }

    long save(Movie movie);
    void deleteMoviesByCategory(int category);
    void getMovies(int category, final MovieRepository.ListMovieCallBack callBack);

    long save(Serie serie);
    void deleteSerieByCategory(int category);
    void getSerie(int category, final MovieRepository.ListSerieCallBack callBack);

}
