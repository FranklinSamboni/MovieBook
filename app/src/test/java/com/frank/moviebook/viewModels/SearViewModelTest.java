package com.frank.moviebook.viewModels;

import android.content.Context;

import com.frank.moviebook.BaseTest;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.search.SearchViewModel;
import com.frank.moviebook.search.ui.SearchView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

import static org.mockito.Mockito.verify;

/**
 * Created by FRANK on 28/11/2017.
 */

public class SearViewModelTest extends BaseTest{

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private SearchView searchView;
    @Mock
    private Context context;

    private CompositeDisposable compositeDisposable;

    private SearchViewModel searchViewModel;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        compositeDisposable = new CompositeDisposable();
        searchViewModel = new SearchViewModel(movieRepository,searchView,context,compositeDisposable);
    }

    @Test
    public void testGetSerieByName(){
        String name = "The Walking";
        searchViewModel.getSerieByName(name);
        verify(movieRepository).getSeriesByName(name,searchViewModel);
    }


    @Test
    public void testGetMOvieByName(){
        String name = "it";
        searchViewModel.getMovieByName(name);
        verify(movieRepository).getMoviesByName(name,searchViewModel);
    }

    @Test
    public void testNotDataMoviesLoaded(){
        searchViewModel.onMoviesLoaded(new ArrayList<Movie>());
        verify(searchView).showError("No se encontraron resultados");
    }

    @Test
    public void testNotDataSerieLoaded(){
        searchViewModel.onSeriesLoaded(new ArrayList<Serie>());
        verify(searchView).showError("No se encontraron resultados");
    }

    @Test
    public void testOnError(){
        searchViewModel.onError(null);
        verify(searchView).showError(null);
    }

}
