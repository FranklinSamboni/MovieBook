package com.frank.moviebook.libs;

import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.data.Movie;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FRANK on 27/11/2017.
 */

public class MovieClient {

    private Retrofit retrofit;

    public MovieClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public MovieService getMovieService() {
        return retrofit.create(MovieService.class);
    }
}
