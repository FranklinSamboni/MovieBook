package com.frank.moviebook.search.di;

import android.content.Context;

import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.MovieRepositoryImpl;
import com.frank.moviebook.movies.adapters.CategoryAdapter;
import com.frank.moviebook.movies.ui.ItemClickListener;
import com.frank.moviebook.search.SearchViewModel;
import com.frank.moviebook.search.adapters.SearchCategoryAdapter;
import com.frank.moviebook.search.ui.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 26/11/2017.
 */
@Module
public class SearchModule {

    private SearchView searchView;
    private ItemClickListener itemClickListener;

    public SearchModule(SearchView searchView, ItemClickListener itemClickListener) {
        this.searchView = searchView;
        this.itemClickListener = itemClickListener;
    }

    @Singleton
    @Provides
    SearchCategoryAdapter providesCategoryAdapter(Context context, List<Map<String,List>> categories, ItemClickListener itemClickListener){
        return new SearchCategoryAdapter(context,  categories, itemClickListener);
    }

    @Singleton
    @Provides
    List<Map<String,List>> providesCategoryList(){
        return new ArrayList<>();
    }

    @Singleton
    @Provides
    ItemClickListener providesItemClickListener(){
        return this.itemClickListener;
    }

    @Singleton
    @Provides
    SearchView providesSearchView(){
        return this.searchView;
    }

    @Singleton
    @Provides
    SearchViewModel providesSearchViewModel(MovieRepository movieRepository , SearchView searchView, Context context, CompositeDisposable compositeDisposable){
        return new SearchViewModel(movieRepository,searchView,context, compositeDisposable);
    }

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(Context context, Retrofit retrofit, CompositeDisposable compositeDisposable){
        return new MovieRepositoryImpl(context,retrofit,compositeDisposable);
    }


}
