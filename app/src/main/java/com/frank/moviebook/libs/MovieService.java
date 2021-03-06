package com.frank.moviebook.libs;

import com.frank.moviebook.data.source.remote.Response.MovieDbResponse;
import com.frank.moviebook.data.source.remote.Response.SerieDbResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by FRANK on 19/11/2017.
 */

public interface MovieService {

    @GET("movie/{category}")
    Observable<MovieDbResponse> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page
            );

    @GET("tv/{category}")
    Observable<SerieDbResponse> getTVSeries(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("search/movie")
    Observable<MovieDbResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page,
            @Query("query") String text
    );

    @GET("search/tv")
    Observable<SerieDbResponse> searchSeries(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page,
            @Query("query") String text
    );

}
