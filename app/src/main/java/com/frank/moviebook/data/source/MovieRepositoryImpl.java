package com.frank.moviebook.data.source;

import android.content.Context;

import com.frank.moviebook.Util.CheckConnection;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.local.MovieLocalDataSource;
import com.frank.moviebook.data.source.remote.MovieRemoteDataSource;
import com.frank.moviebook.libs.MovieClient;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieRepositoryImpl implements MovieRepository {

    private MovieRepository movieLocalDataSource;
    private MovieRepository movieRemoteDataSource;

    private Context context;
    private CompositeDisposable compositeDisposable;

    public MovieRepositoryImpl(Context context, MovieClient movieClient, CompositeDisposable compositeDisposable) {
        this.context = context;
        this.movieLocalDataSource = new MovieLocalDataSource(context);
        this.movieRemoteDataSource = new MovieRemoteDataSource(movieClient, compositeDisposable);
    }

    /*PELICULAS
    * */

    @Override //NO es necesario aqui
    public long save(Movie movie) { return 0; }

    @Override //NO es necesario aqui
    public void deleteMovies() {}

    @Override
    public Movie getMovieById(int id) {
        return movieLocalDataSource.getMovieById(id);
    }

    @Override
    public void getMoviesByName(final String name, final ListMovieCallBack callBack) {
        movieLocalDataSource.getMoviesByName(name, new ListMovieCallBack() {
            @Override
            public void onMoviesLoaded(List<Movie> data) {
                //if(data.size() > 0){
                    callBack.onMoviesLoaded(data);
                /*}
                else {
                    movieRemoteDataSource.getMoviesByName(name, new ListMovieCallBack() {
                        @Override
                        public void onMoviesLoaded(List<Movie> data) {
                            callBack.onMoviesLoaded(data);
                        }

                        @Override
                        public void onError(String message) {
                            callBack.onError(message);
                        }
                    });
                }*/
            }

            @Override
            public void onError(String message) {
                callBack.onError(message);
            }
        });
    }


    @Override
    public void getMovies(final MovieRepository.ListMovieCallBack callBack) {

        if(CheckConnection.isConected(context)){
            movieRemoteDataSource.getMovies(new ListMovieCallBack() {
                @Override
                public void onMoviesLoaded(List<Movie> data) {
                    refreshLocalMovies(data);
                    callBack.onMoviesLoaded(data);
                }

                @Override
                public void onError(String message) {
                    movieLocalDataSource.getMovies(new ListMovieCallBack() {
                        @Override
                        public void onMoviesLoaded(List<Movie> data) {
                            callBack.onMoviesLoaded(data);
                        }

                        @Override
                        public void onError(String message) {
                            callBack.onError(message);
                        }
                    });
                }
            });
        }
        else{
            movieLocalDataSource.getMovies(new ListMovieCallBack() {
                @Override
                public void onMoviesLoaded(List<Movie> data) {
                    callBack.onMoviesLoaded(data);
                }

                @Override
                public void onError(String message) {
                    callBack.onError(message);
                }
            });
        }
    }

    /*SERIES
    * */

    @Override //NO es necesario aqui
    public long save(Serie serie) { return 0; }

    @Override //NO es necesario aqui
    public void deleteSeries() { }

    @Override
    public Serie getSerieById(int idSerie) {
        return movieLocalDataSource.getSerieById(idSerie);
    }

    @Override
    public void getSeriesByName(final String name, final ListSerieCallBack callBack) {
        movieLocalDataSource.getSeriesByName(name, new ListSerieCallBack() {
            @Override
            public void onSeriesLoaded(List<Serie> data) {
                //if(data.size()>0){
                    callBack.onSeriesLoaded(data);
                /*}
                else{
                    movieRemoteDataSource.getSeriesByName(name, new ListSerieCallBack() {
                        @Override
                        public void onSeriesLoaded(List<Serie> data) {
                            callBack.onSeriesLoaded(data);
                        }

                        @Override
                        public void onError(String message) {
                            callBack.onError(message

                            );
                        }
                    });
                }*/
            }

            @Override
            public void onError(String message) {
                callBack.onError(message);
            }
        });
    }

    @Override
    public void getSeries(final ListSerieCallBack callBack) {

        if(CheckConnection.isConected(context)){
            movieRemoteDataSource.getSeries(new ListSerieCallBack() {
                @Override
                public void onSeriesLoaded(List<Serie> data) {
                    refreshLocalSeries(data);
                    callBack.onSeriesLoaded(data);
                }

                @Override
                public void onError(String message) {
                    movieLocalDataSource.getSeries(new ListSerieCallBack() {
                        @Override
                        public void onSeriesLoaded(List<Serie> data) {
                            callBack.onSeriesLoaded(data);
                        }

                        @Override
                        public void onError(String message) {
                            callBack.onError(message);
                        }
                    });
                }
            });
        }
        else{
            movieLocalDataSource.getSeries(new ListSerieCallBack() {
                @Override
                public void onSeriesLoaded(List<Serie> data) {
                    callBack.onSeriesLoaded(data);
                }

                @Override
                public void onError(String message) {
                    callBack.onError(message);
                }
            });
        }
    }



    private void refreshLocalMovies(List<Movie> movies){
        movieLocalDataSource.deleteMovies();
        for (Movie movie : movies) {
            movieLocalDataSource.save(movie);
        }

    }

    private void refreshLocalSeries(List<Serie> series){
        movieLocalDataSource.deleteSeries();
        for (Serie serie : series) {
            movieLocalDataSource.save(serie);
        }
    }
}
