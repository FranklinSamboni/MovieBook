package com.frank.moviebook.movies.ui;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;

import java.util.List;

/**
 * Created by FRANK on 20/11/2017.
 */

public interface MainActivityView {

    void navigateToSerieDetail(int idSerie);

    void showMovies(String title,List<Movie> movies);
    void showSeries(String title,List<Serie> series);
    void showInitialMovie(Movie movie);

    void showError(String message);
}
