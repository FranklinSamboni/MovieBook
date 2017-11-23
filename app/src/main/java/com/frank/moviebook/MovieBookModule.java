package com.frank.moviebook;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by FRANK on 22/11/2017.
 */
@Module
public class MovieBookModule {

    Application application;

    public MovieBookModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return this.application;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return this.application.getApplicationContext();
    }
}
