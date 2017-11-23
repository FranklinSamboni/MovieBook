package com.frank.moviebook.movies;

import android.content.Context;

import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.movies.ui.MainActivityView;

import java.util.List;

/**
 * Created by FRANK on 20/11/2017.
 */

public class MainViewModel implements MovieRepository.ListSerieCallBack, MovieRepository.ListMovieCallBack {

    private MovieRepository repository;
    private MainActivityView view;

    public MainViewModel(MovieRepository repository, MainActivityView view) {
        this.view = view;
        this.repository = repository;
    }

    public void getMovies(){
        repository.getMovies(Globals.Category.POPULAR,this);
        repository.getMovies(Globals.Category.TOP_RATED,this);
        repository.getMovies(Globals.Category.UPCOMING,this);
    }

    public void getSeries(){
        repository.getSerie(Globals.Category.POPULAR,this);
        repository.getSerie(Globals.Category.TOP_RATED,this);
    }

    @Override
    public void onMoviesLoaded(List<Movie> data) {
        view.showMovies(data);
        view.showInitialMovie(data.get(0));
    }

    @Override
    public void onSeriesLoaded(List<Serie> data) {
        view.showSeries(data);
    }

    @Override
    public void onError(String message) {
        view.showError(message);
    }
}
