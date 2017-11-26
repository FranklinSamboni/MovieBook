package com.frank.moviebook.movies;

import android.content.Context;

import com.frank.moviebook.R;
import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.movies.ui.MainActivityView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by FRANK on 20/11/2017.
 */

public class MainViewModel implements MovieRepository.ListSerieCallBack, MovieRepository.ListMovieCallBack {

    private MovieRepository repository;
    private MainActivityView view;

    private Context context;
    private CompositeDisposable compositeDisposable;

    public MainViewModel(MovieRepository repository, MainActivityView view, Context context, CompositeDisposable compositeDisposable) {
        this.view = view;
        this.repository = repository;
        this.context = context;
        this.compositeDisposable = compositeDisposable;
    }

    public void onDestroy(){
        compositeDisposable.clear();
    }

    public void getMovies(){
        repository.getMovies(this);
    }

    public void getSeries(){
        repository.getSeries(this);
    }

    @Override
    public void onMoviesLoaded(List<Movie> data) {
        String [] titles = context.getResources().getStringArray(R.array.MoviesArray);
        view.showMovies(titles[Globals.Category.POPULAR], getMovieByCategory(Globals.Category.POPULAR,data));
        view.showMovies(titles[Globals.Category.TOP_RATED], getMovieByCategory(Globals.Category.TOP_RATED,data));
        view.showMovies(titles[Globals.Category.UPCOMING], getMovieByCategory(Globals.Category.UPCOMING,data));
        view.showInitialMovie(data.get(0));
    }

    @Override
    public void onSeriesLoaded(List<Serie> data) {
        String [] titles = context.getResources().getStringArray(R.array.SeriesArray);
        view.showSeries(titles[Globals.Category.POPULAR], getSerieByCategory(Globals.Category.POPULAR,data));
        view.showSeries(titles[Globals.Category.TOP_RATED], getSerieByCategory(Globals.Category.TOP_RATED,data));
    }

    @Override
    public void onError(String message) {
        view.showError(message);
    }

    private List<Movie>  getMovieByCategory (int category, List<Movie> data){
        List<Movie> movies = new ArrayList<>();
        for(Movie movie : data){
            if(movie.getCategory() == category){
                movies.add(movie);
            }
        }
        return movies;
    }

    private List<Serie>  getSerieByCategory(int category,List<Serie> data){
        List<Serie> series = new ArrayList<>();
        for(Serie serie : data){
            if(serie.getCategory() == category){
                series.add(serie);
            }
        }
        return series;
    }
}
