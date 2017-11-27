package com.frank.moviebook.search.di;

import com.frank.moviebook.MovieBookModule;
import com.frank.moviebook.libs.LibsModule;
import com.frank.moviebook.movies.di.MainModule;
import com.frank.moviebook.search.ui.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by FRANK on 26/11/2017.
 */

@Singleton
@Component(modules = {LibsModule.class,MovieBookModule.class,SearchModule.class})
public interface SearchComponent {
    void inject(SearchActivity activity);
}

