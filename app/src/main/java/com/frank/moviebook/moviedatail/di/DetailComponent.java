package com.frank.moviebook.moviedatail.di;

import com.frank.moviebook.MovieBookModule;
import com.frank.moviebook.libs.LibsModule;
import com.frank.moviebook.moviedatail.ui.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by FRANK on 25/11/2017.
 */

@Singleton
@Component(modules = {LibsModule.class,MovieBookModule.class, DetailModule.class})
public interface DetailComponent {
    void inject(MovieDetailActivity activity);
}
