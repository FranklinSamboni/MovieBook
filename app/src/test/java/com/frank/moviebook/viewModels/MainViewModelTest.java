package com.frank.moviebook.viewModels;

import android.content.Context;

import com.frank.moviebook.BaseTest;
import com.frank.moviebook.R;
import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.MovieRepositoryImpl;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.ui.MainActivityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.ApplicationTestUtil;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

import static org.mockito.Mockito.verify;

/**
 * Created by FRANK on 27/11/2017.
 */

public class MainViewModelTest extends BaseTest{

    @Mock
    private MovieRepositoryImpl repository;
    @Mock
    private MainActivityView view;
    @Mock
    private Context context;

    private CompositeDisposable compositeDisposable;

    private MainViewModel viewModel;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        compositeDisposable = new CompositeDisposable();
        viewModel = new MainViewModel(repository,view,context,compositeDisposable);
    }

    @Test
    public void testGetMovies(){
        viewModel.getMovies();
        verify(repository).getMovies(viewModel);
    }

    @Test
    public void testGetSeries(){
        viewModel.getSeries();
        verify(repository).getSeries(viewModel);
    }

    @Test
    public void testNotDataMoviesLoaded(){
        viewModel.onMoviesLoaded(new ArrayList<Movie>());
        verify(view).showError(null);
    }

    @Test
    public void testNotDataSerieLoaded(){
        viewModel.onSeriesLoaded(new ArrayList<Serie>());
        verify(view).showError(null);
    }

    @Test
    public void testOnError(){
        viewModel.onError(null);
        verify(view).showError(null);
    }
}
