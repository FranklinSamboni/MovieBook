package com.frank.moviebook.data.source.remote;

import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.remote.Response.MovieDbResponse;
import com.frank.moviebook.data.source.remote.Response.SerieDbResponse;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieRemoteDataSource implements MovieRepository {

    public final String API_KEY = "5f9b575bdd7b895c79917da012169744";

    private MovieService service;

    public MovieRemoteDataSource(Retrofit retrofit) {
        this.service = retrofit.create(MovieService.class);
    }

    /*PELICULAS
    * */

    @Override //No es necesario para la fuente de datos remota
    public long save(Movie movie) {
        return 0;
    }

    @Override //No es necesario para la fuente de datos remota
    public void deleteMoviesByCategory(int category) {
    }

    @Override
    public void getMovies(final int category, final MovieRepository.ListMovieCallBack callBack) {
        String url = Globals.Category.nameCategory[category];
        Call<MovieDbResponse> call = service.getMovies(
                url,
                API_KEY,
                Globals.LANGUAGE,
                Globals.PAGE
        );

        /*single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<MovieDbResponse, List<Movie>>() {
                    @Override
                    public List<Movie> apply(MovieDbResponse movieDbResponse) throws Exception {
                        return movieDbResponse.getResults();
                    }
                })
                .subscribe(new SingleObserver<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Movie> movies) {
                        callBack.onMoviesLoaded(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage());
                    }
                });*/

        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                if (response.code() == 200) {
                    MovieDbResponse dbResponse = response.body();
                    for (Movie movie : dbResponse.getResults()) {
                        movie.setCategory(category);
                    }
                    callBack.onMoviesLoaded(dbResponse.getResults());

                } else {
                    callBack.onError("Error, no fue posible obtener información de las peliculas ");
                }
            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });

    }

    /*SERIES
    * */

    @Override //No es necesario para la fuente de datos remota
    public long save(Serie serie) {
        return 0;
    }

    @Override //No es necesario para la fuente de datos remota
    public void deleteSerieByCategory(int category) {
    }

    @Override
    public void getSerie(final int category, final ListSerieCallBack callBack) {
        Call<SerieDbResponse> call = service.getTVSeries(
                Globals.Category.nameCategory[category],
                API_KEY,
                Globals.LANGUAGE,
                Globals.PAGE
        );

        /*Call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<SerieDbResponse, List<Serie>>() {
                    @Override
                    public List<Serie> apply(SerieDbResponse serieDbResponse) throws Exception {
                        return serieDbResponse.getResults();
                    }
                })
                .subscribe(new SingleObserver<List<Serie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Serie> series) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });*/
        call.enqueue(new Callback<SerieDbResponse>() {
            @Override
            public void onResponse(Call<SerieDbResponse> call, Response<SerieDbResponse> response) {
                if (response.code() == 200) {
                    SerieDbResponse dbResponse = response.body();
                    for (Serie serie : dbResponse.getResults()) {
                        serie.setCategory(category);
                    }
                    callBack.onSeriesLoaded(dbResponse.getResults());

                } else {
                    callBack.onError("Error, no fue posible obtener información de las peliculas ");
                }
            }

            @Override
            public void onFailure(Call<SerieDbResponse> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }

}
