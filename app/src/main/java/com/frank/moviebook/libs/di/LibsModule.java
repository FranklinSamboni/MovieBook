package com.frank.moviebook.libs.di;

import android.app.Application;

import com.frank.moviebook.Util.Globals;
import com.frank.moviebook.libs.MovieClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FRANK on 22/11/2017.
 */
@Module
public class LibsModule {

    public LibsModule() {

    }

    @Singleton
    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    MovieClient provideMovieClient(){
        return new MovieClient();
    }


    /*
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client){
        return null;
    }


    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .cache(cache)
                .build();
        return client;
    }*/


}
