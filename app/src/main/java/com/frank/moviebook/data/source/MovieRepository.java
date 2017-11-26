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
    void deleteMovies();
    void getMovies(final MovieRepository.ListMovieCallBack callBack);
    Movie getMovieById(int id);

    long save(Serie serie);
    void deleteSeries();
    void getSeries(final MovieRepository.ListSerieCallBack callBack);

}
