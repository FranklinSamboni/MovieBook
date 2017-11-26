package com.frank.moviebook.moviedatail.di;

import android.content.Context;

import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.MovieRepositoryImpl;
import com.frank.moviebook.moviedatail.DetailsViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 25/11/2017.
 */
@Module
public class DetailModule {

    public DetailModule() {
    }

    @Singleton
    @Provides
    DetailsViewModel providesDetailsViewModel(MovieRepository movieRepository){
        return new DetailsViewModel(movieRepository);
    }

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(Context context, Retrofit retrofit, CompositeDisposable compositeDisposable){
        return new MovieRepositoryImpl(context, retrofit, compositeDisposable);
    }

}
