package com.frank.moviebook.remoterepository;

import android.support.constraint.solver.Goal;

import com.frank.moviebook.BaseTest;
import com.frank.moviebook.BuildConfig;
import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.remote.MovieRemoteDataSource;
import com.frank.moviebook.data.source.remote.Response.MovieDbResponse;
import com.frank.moviebook.data.source.remote.Response.SerieDbResponse;
import com.frank.moviebook.libs.MovieClient;
import com.frank.moviebook.libs.MovieService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

/**
 * Created by FRANK on 27/11/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class MovieRemoteDataSourceTest extends BaseTest{

    private MovieService movieService;

    private MovieRemoteDataSource remoteDataSource;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        MovieClient client = new MovieClient();
        movieService = client.getMovieService();

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        remoteDataSource = new MovieRemoteDataSource(client,compositeDisposable);

    }

    @Test
    public void testMovieService() throws Exception {
        int category = Globals.Category.POPULAR;
        Observable<MovieDbResponse> observable = movieService.getMovies(
                Globals.Category.nameCategory[category],
                Globals.API_KEY, Globals.LANGUAGE,
                Globals.PAGE);

        TestObserver<MovieDbResponse> observer = observable.test();

        observer.awaitTerminalEvent();
        observer.assertNoErrors();

        List<MovieDbResponse> reponse = observer.values();
        Assert.assertNotNull(reponse.get(0));

        List<Movie> movies = reponse.get(0).getResults();
        Assert.assertEquals(movies.size(),20);

        Assert.assertNotNull(movies.get(0));

    }

    @Test
    public void testSerieService() throws Exception {
        int category = Globals.Category.POPULAR;
        Observable<SerieDbResponse> observable = movieService.getTVSeries(
                Globals.Category.nameCategory[category],
                Globals.API_KEY, Globals.LANGUAGE,
                Globals.PAGE);

        TestObserver<SerieDbResponse> observer = observable.test();

        observer.awaitTerminalEvent();
        observer.assertNoErrors();

        List<SerieDbResponse> reponse = observer.values();
        Assert.assertNotNull(reponse.get(0));

        List<Serie> series = reponse.get(0).getResults();
        Assert.assertEquals(series.size(),20);

        Assert.assertNotNull(series.get(0));

    }

    @Test
    public void testSearchService() throws Exception {
        String query = "it";
        Observable<SerieDbResponse> observable = movieService.searchSeries(
                Globals.API_KEY,
                Globals.LANGUAGE,
                Globals.PAGE,
                query);

        TestObserver<SerieDbResponse> observer = observable.test();

        observer.awaitTerminalEvent();
        observer.assertNoErrors();

        List<SerieDbResponse> reponse = observer.values();
        Assert.assertNotNull(reponse.get(0));

        List<Serie> series = reponse.get(0).getResults();
        Assert.assertNotNull(series);

        Assert.assertNotNull(series.get(0));

    }


    @Test
    public void testMoviesRemoteRepository(){

        final MovieRepository.ListMovieCallBack callBack = mock(MovieRepository.ListMovieCallBack.class);
        remoteDataSource.getMovies(callBack);
        verify(callBack);


    }

}
