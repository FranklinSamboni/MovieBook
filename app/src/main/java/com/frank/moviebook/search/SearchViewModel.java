package com.frank.moviebook.search;

import android.content.Context;

import com.frank.moviebook.R;
import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.search.ui.SearchView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by FRANK on 26/11/2017.
 */

public class SearchViewModel implements MovieRepository.ListSerieCallBack, MovieRepository.ListMovieCallBack {

    private MovieRepository movieRepository;
    private SearchView searchView;
    private Context context;
    private CompositeDisposable compositeDisposable;

    public SearchViewModel(MovieRepository movieRepository , SearchView searchView, Context context, CompositeDisposable compositeDisposable) {
        this.movieRepository = movieRepository;
        this.searchView = searchView;
        this.context = context;
        this.compositeDisposable = compositeDisposable;
    }

    public void onDestroy(){
        compositeDisposable.clear();
    }

    public void getSerieByName(String name){
        movieRepository.getSeriesByName(name, this);
    }

    public void getMovieByName(String name){
        movieRepository.getMoviesByName(name, this);
    }


    @Override
    public void onMoviesLoaded(List<Movie> data) {
        if(data.size()>0){
            String [] titles = context.getResources().getStringArray(R.array.MoviesArray);
            List<Movie> movies = getMovieByCategory(Globals.Category.POPULAR,data);
            if(movies.size() > 0){
                searchView.showMovies(titles[Globals.Category.POPULAR], movies);
            }
            movies = getMovieByCategory(Globals.Category.TOP_RATED,data);
            if(movies.size() > 0){
                searchView.showMovies(titles[Globals.Category.TOP_RATED], movies);
            }
            movies = getMovieByCategory(Globals.Category.UPCOMING,data);
            if(movies.size() > 0){
                searchView.showMovies(titles[Globals.Category.UPCOMING], movies);
            }
        }
        else{
            searchView.showError("No se encontraron resultados");
        }

    }

    @Override
    public void onSeriesLoaded(List<Serie> data) {
        if(data.size()>0){
            String [] titles = context.getResources().getStringArray(R.array.SeriesArray);
            List<Serie> series = getSerieByCategory(Globals.Category.POPULAR,data);
            if(series.size() > 0){
                searchView.showSeries(titles[Globals.Category.POPULAR], series);
            }
            series = getSerieByCategory(Globals.Category.TOP_RATED,data);
            if(series.size() > 0){
                searchView.showSeries(titles[Globals.Category.TOP_RATED], series);
            }
        }
        else {
            searchView.showError("No se encontraron resultados");
        }
    }

    @Override
    public void onError(String message) {
        searchView.showError(message);
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
