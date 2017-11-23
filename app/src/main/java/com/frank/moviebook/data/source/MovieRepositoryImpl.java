package com.frank.moviebook.data.source;

import android.content.Context;

import com.frank.moviebook.Util.CheckConnection;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.local.MovieLocalDataSource;
import com.frank.moviebook.data.source.remote.MovieRemoteDataSource;

import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieRepositoryImpl implements MovieRepository {

    private MovieRepository movieLocalDataSource;
    private MovieRepository movieRemoteDataSource;

    private Context context;

    public MovieRepositoryImpl(Context context, Retrofit retrofit) {
        this.context = context;
        this.movieLocalDataSource = new MovieLocalDataSource(context);
        this.movieRemoteDataSource = new MovieRemoteDataSource(retrofit);
    }

    /*PELICULAS
    * */

    @Override //NO es necesario aqui
    public long save(Movie movie) { return 0; }

    @Override //NO es necesario aqui
    public void deleteMoviesByCategory(int category) {}

    @Override
    public void getMovies(final int category, final MovieRepository.ListMovieCallBack callBack) {

        if(CheckConnection.isConected(context)){
            movieRemoteDataSource.getMovies(category,new ListMovieCallBack() {
                @Override
                public void onMoviesLoaded(List<Movie> data) {
                    refreshLocalMovies(category,data);
                    callBack.onMoviesLoaded(data);
                }

                @Override
                public void onError(String message) {
                    movieLocalDataSource.getMovies(category,new ListMovieCallBack() {
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
            movieLocalDataSource.getMovies(category,new ListMovieCallBack() {
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
    public void deleteSerieByCategory(int category) { }

    @Override
    public void getSerie(final int category, final ListSerieCallBack callBack) {

        if(CheckConnection.isConected(context)){
            movieRemoteDataSource.getSerie(category, new ListSerieCallBack() {
                @Override
                public void onSeriesLoaded(List<Serie> data) {
                    refreshLocalSeries(category,data);
                    callBack.onSeriesLoaded(data);
                }

                @Override
                public void onError(String message) {
                    movieLocalDataSource.getSerie(category, new ListSerieCallBack() {
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
            movieLocalDataSource.getSerie(category, new ListSerieCallBack() {
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

    private void refreshLocalMovies(int category, List<Movie> movies){
        movieLocalDataSource.deleteMoviesByCategory(category);
        for (Movie movie : movies) {
            movieLocalDataSource.save(movie);
        }

    }

    private void refreshLocalSeries(int category, List<Serie> series){
        movieLocalDataSource.deleteSerieByCategory(category);
        for (Serie serie : series) {
            movieLocalDataSource.save(serie);
        }
    }
}
