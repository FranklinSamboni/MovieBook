package com.frank.moviebook;

import android.app.Application;

import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.libs.di.LibsModule;
import com.frank.moviebook.moviedatail.di.DaggerDetailComponent;
import com.frank.moviebook.moviedatail.di.DetailComponent;
import com.frank.moviebook.moviedatail.di.DetailModule;
import com.frank.moviebook.movies.di.DaggerMainComponent;
import com.frank.moviebook.movies.di.MainComponent;
import com.frank.moviebook.movies.di.MainModule;
import com.frank.moviebook.movies.ui.ItemClickListener;
import com.frank.moviebook.movies.ui.MainActivityView;
import com.frank.moviebook.search.di.DaggerSearchComponent;
import com.frank.moviebook.search.di.SearchComponent;
import com.frank.moviebook.search.di.SearchModule;
import com.frank.moviebook.search.ui.SearchView;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieBookApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MainComponent getMainComponent(MainActivityView mainActivityView, ItemClickListener itemClickListener){
        return DaggerMainComponent
                .builder()
                .movieBookModule(getMovieBookModule())
                .libsModule(getLibsModule())
                .mainModule(getMainModule(mainActivityView, itemClickListener))
                .build();
    }

    public DetailComponent getDetailComponent(){
        return DaggerDetailComponent
                .builder()
                .movieBookModule(getMovieBookModule())
                .libsModule(getLibsModule())
                .detailModule(getDetailModule())
                .build();

    }

    public SearchComponent getSearchComponent(SearchView searchView, ItemClickListener itemClickListener){
        return DaggerSearchComponent
                .builder()
                .movieBookModule(getMovieBookModule())
                .libsModule(getLibsModule())
                .searchModule(getSearchModule(searchView,itemClickListener))
                .build();

    }

    public MovieBookModule getMovieBookModule() {
        return new MovieBookModule(this);
    }

    public LibsModule getLibsModule() { 
        return new LibsModule();
    }

    public MainModule getMainModule(MainActivityView mainActivityView, ItemClickListener itemClickListener) {
        return new MainModule(mainActivityView, itemClickListener);
    }

    public DetailModule getDetailModule() {
        return new DetailModule();
    }

    public SearchModule getSearchModule(SearchView searchView, ItemClickListener itemClickListener) {
        return new SearchModule(searchView,itemClickListener);
    }
}
