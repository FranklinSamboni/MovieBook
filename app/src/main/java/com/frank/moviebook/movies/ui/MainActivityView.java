package com.frank.moviebook.movies.ui;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;

import java.util.List;

/**
 * Created by FRANK on 20/11/2017.
 */

public interface MainActivityView {

    void navigateToMovieDetail(int idMovie);
    void navigateToSerieDetail(int idSerie);

    void showMovies(List<Movie> movies);
    void showSeries(List<Serie> series);
    void showInitialMovie(Movie movie);

    void showError(String message);
}
