package com.frank.moviebook.data.source.remote;

import android.util.Log;

import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.remote.Response.MovieDbResponse;
import com.frank.moviebook.data.source.remote.Response.SerieDbResponse;
import com.frank.moviebook.libs.MovieClient;
import com.frank.moviebook.libs.MovieService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieRemoteDataSource implements MovieRepository {



    private MovieService service;
    private CompositeDisposable compositeDisposable;

    public MovieRemoteDataSource(MovieClient movieClient, CompositeDisposable compositeDisposable) {
        this.service = movieClient.getMovieService();
        this.compositeDisposable = compositeDisposable;
    }

    /*PELICULAS
    * */

    @Override //No es necesario para la fuente de datos remota
    public long save(Movie movie) {
        return 0;
    }

    @Override //No es necesario para la fuente de datos remota
    public void deleteMovies() {}
    @Override
    public Movie getMovieById(int id) { return null; }

    @Override
    public void getMoviesByName(String name, final ListMovieCallBack callBack) {

        Observable<List<Movie>> observable = service.searchMovies(Globals.API_KEY, Globals.LANGUAGE, Globals.PAGE, name )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<MovieDbResponse, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MovieDbResponse movieDbResponse) throws Exception {
                        return movieDbResponse.getResults();
                    }
                });
        compositeDisposable.add(observable.subscribe(new Consumer<List<Movie>>() {
            @Override
            public void accept(List<Movie> movies) throws Exception {
                callBack.onMoviesLoaded(movies);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                callBack.onError(throwable.getMessage());
            }
        }));
    }


    @Override
    public void getMovies(final MovieRepository.ListMovieCallBack callBack) {

        Observable<MovieDbResponse> observablePopularMovies = getMovieObservableByCategory(Globals.Category.POPULAR);
        Observable<MovieDbResponse> observableTopRatedMovies = getMovieObservableByCategory(Globals.Category.TOP_RATED);
        Observable<MovieDbResponse> observableUpComingMovies = getMovieObservableByCategory(Globals.Category.UPCOMING);

        Observable<List<Movie>> zipped = Observable.zip(observablePopularMovies, observableTopRatedMovies, observableUpComingMovies,
                new Function3<MovieDbResponse, MovieDbResponse, MovieDbResponse, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MovieDbResponse popularMovies, MovieDbResponse topRatedMovies, MovieDbResponse upcomingMovies) throws Exception {

                        setCategoryMovie(Globals.Category.POPULAR, popularMovies.getResults());
                        setCategoryMovie(Globals.Category.TOP_RATED, topRatedMovies.getResults());
                        setCategoryMovie(Globals.Category.UPCOMING, upcomingMovies.getResults());

                        List<Movie> movies = new ArrayList<>();
                        movies.addAll(popularMovies.getResults());
                        movies.addAll(topRatedMovies.getResults());
                        movies.addAll(upcomingMovies.getResults());

                        return movies;
                    }
                });

        compositeDisposable.add(zipped.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> movies) throws Exception {
                        callBack.onMoviesLoaded(movies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(throwable.getMessage());
                    }
                }));

    }

    /*SERIES
    * */

    @Override //No es necesario para la fuente de datos remota
    public long save(Serie serie) {
        return 0;
    }

    @Override //No es necesario para la fuente de datos remota
    public void deleteSeries() {
    }

    @Override
    public void getSeries(final ListSerieCallBack callBack) {

        Observable<SerieDbResponse> observablePopularSeries = getSerieObservableByCategory(Globals.Category.POPULAR);
        Observable<SerieDbResponse> observableTopRatedSeries = getSerieObservableByCategory(Globals.Category.TOP_RATED);

        Observable<List<Serie>> zipped = Observable.zip(observablePopularSeries, observableTopRatedSeries,
                new BiFunction<SerieDbResponse, SerieDbResponse, List<Serie>>() {
                    @Override
                    public List<Serie> apply(SerieDbResponse popularSeries, SerieDbResponse topRatedSeries) throws Exception {

                        setCategorySerie(Globals.Category.POPULAR, popularSeries.getResults());
                        setCategorySerie(Globals.Category.TOP_RATED, topRatedSeries.getResults());
                        List<Serie> series = new ArrayList<>();
                        series.addAll(popularSeries.getResults());
                        series.addAll(topRatedSeries.getResults());
                        return series;

                    }
                });

        compositeDisposable.add(zipped.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Serie>>() {
                    @Override
                    public void accept(List<Serie> series) throws Exception {
                        callBack.onSeriesLoaded(series);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(throwable.getMessage());
                    }
                }));

    }

    @Override
    public Serie getSerieById(int idSerie) {return null;}

    @Override
    public void getSeriesByName(String name, final ListSerieCallBack callBack) {

        Observable<List<Serie>> observable = service.searchSeries(Globals.API_KEY, Globals.LANGUAGE, Globals.PAGE, name )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<SerieDbResponse, List<Serie>>() {
                    @Override
                    public List<Serie> apply(SerieDbResponse serieDbResponse) throws Exception {
                        return serieDbResponse.getResults();
                    }
                });

        compositeDisposable.add(observable.subscribe(new Consumer<List<Serie>>() {
            @Override
            public void accept(List<Serie> series) throws Exception {
                callBack.onSeriesLoaded(series);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("errrorrrr",throwable.getMessage());
                callBack.onError(throwable.getMessage());
            }
        }));
    }

    private void setCategorySerie(int category, List<Serie> series) {
        for (Serie serie : series) {
            serie.setCategory(category);
        }
    }

    private void setCategoryMovie(int category, List<Movie> movies) {
        for (Movie movie : movies) {
            movie.setCategory(category);
        }
    }

    private Observable<SerieDbResponse> getSerieObservableByCategory(int category) {
        return service.getTVSeries(Globals.Category.nameCategory[category], Globals.API_KEY, Globals.LANGUAGE, Globals.PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, SerieDbResponse>() {
                    @Override
                    public SerieDbResponse apply(Throwable throwable) throws Exception {
                        return new SerieDbResponse();
                    }
                });
    }

    private Observable<MovieDbResponse> getMovieObservableByCategory(int category) {
        return service.getMovies(Globals.Category.nameCategory[category], Globals.API_KEY, Globals.LANGUAGE, Globals.PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, MovieDbResponse>() {
                    @Override
                    public MovieDbResponse apply(Throwable throwable) throws Exception {
                        return new MovieDbResponse();
                    }
                });
    }


}
