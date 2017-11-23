package com.frank.moviebook.movies.di;

import android.content.Context;

import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.MovieRepositoryImpl;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.adapters.CategoryAdapter;
import com.frank.moviebook.movies.ui.MainActivityView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 22/11/2017.
 */
@Module
public class MainModule {

    private MainActivityView mainActivityView;

    public MainModule(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    @Singleton
    @Provides
    CategoryAdapter providesCategoryAdapter(Context context, List<Map<String,List>> categories){
        return new CategoryAdapter(context,  categories);
    }

    @Singleton
    @Provides
    List<Map<String,List>> providesCategoryList(){
        return new ArrayList<Map<String,List>>();
    }

    @Singleton
    @Provides
    MainViewModel providesMainViewModel(MovieRepository repository, MainActivityView view){
        return new MainViewModel(repository, view);
    }

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(Context context, Retrofit retrofit){
        return new MovieRepositoryImpl(context, retrofit);
    }

    @Singleton
    @Provides
    MainActivityView providesMainActivityView(){
        return this.mainActivityView;
    }
}

