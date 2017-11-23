package com.frank.moviebook.movies.di;

import com.frank.moviebook.MovieBookModule;
import com.frank.moviebook.libs.LibsModule;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by FRANK on 22/11/2017.
 */
@Singleton
@Component(modules = {LibsModule.class,MovieBookModule.class,MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
