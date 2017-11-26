package com.frank.moviebook.moviedatail.di;

import android.app.Activity;

import com.frank.moviebook.MovieBookModule;
import com.frank.moviebook.libs.LibsModule;
import com.frank.moviebook.moviedatail.ui.MovieDetailActivity;
import com.frank.moviebook.moviedatail.ui.SerieDetailActivity;
import com.frank.moviebook.movies.di.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by FRANK on 25/11/2017.
 */

@Singleton
@Component(modules = {LibsModule.class,MovieBookModule.class, DetailModule.class})
public interface DetailComponent {
    void inject(MovieDetailActivity activity);
    void inject(SerieDetailActivity activity);
}
