package com.frank.moviebook.libs;

import com.frank.moviebook.MovieBookModule;

import dagger.Component;

/**
 * Created by FRANK on 22/11/2017.
 */
@Component(modules = {LibsModule.class,MovieBookModule.class})
public interface LibsComponent {
}
