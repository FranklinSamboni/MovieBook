package com.frank.moviebook.movies.di;

import android.content.Context;

import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.MovieRepositoryImpl;
import com.frank.moviebook.libs.MovieClient;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.adapters.CategoryAdapter;
import com.frank.moviebook.movies.ui.ItemClickListener;
import com.frank.moviebook.movies.ui.MainActivityView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 22/11/2017.
 */
@Module
public class MainModule {

    private MainActivityView mainActivityView;
    private ItemClickListener itemClickListener;

    public MainModule(MainActivityView mainActivityView, ItemClickListener itemClickListener) {
        this.mainActivityView = mainActivityView;
        this.itemClickListener = itemClickListener;
    }

    @Singleton
    @Provides
    CategoryAdapter providesCategoryAdapter(Context context, List<Map<String,List>> categories, ItemClickListener itemClickListener){
        return new CategoryAdapter(context,  categories, itemClickListener);
    }

    @Singleton
    @Provides
    List<Map<String,List>> providesCategoryList(){
        return new ArrayList<>();
    }

    @Singleton
    @Provides
    MainViewModel providesMainViewModel(MovieRepository repository, MainActivityView view, Context context, CompositeDisposable compositeDisposable){
        return new MainViewModel(repository, view, context, compositeDisposable);
    }

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(Context context, MovieClient movieClient, CompositeDisposable compositeDisposable){
        return new MovieRepositoryImpl(context, movieClient, compositeDisposable);
    }

    @Singleton
    @Provides
    MainActivityView providesMainActivityView(){
        return this.mainActivityView;
    }

    @Singleton
    @Provides
    ItemClickListener providesItemClickListener(){
        return this.itemClickListener;
    }

}

