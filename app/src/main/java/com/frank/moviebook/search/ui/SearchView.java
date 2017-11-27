package com.frank.moviebook.search.ui;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;

import java.util.List;

/**
 * Created by FRANK on 26/11/2017.
 */

public interface SearchView {

    void clear();
    void showMovies(String title, List<Movie> movies);
    void showSeries(String title,List<Serie> series);

    void showError(String message);
}
