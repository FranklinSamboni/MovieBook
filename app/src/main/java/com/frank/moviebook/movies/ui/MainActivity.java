package com.frank.moviebook.movies.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.frank.moviebook.MovieBookApp;
import com.frank.moviebook.R;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;
import com.frank.moviebook.data.source.MovieRepositoryImpl;
import com.frank.moviebook.databinding.ActivityMainBinding;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.adapters.CategoryAdapter;
import com.frank.moviebook.movies.di.MainComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityView {


    ActivityMainBinding binding;

    @Inject
    MainViewModel mainViewModel;
    @Inject
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setupInjection();

        RecyclerView categoryRecyclerView = binding.categoryReciclerView;
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        mainViewModel.getMovies();
        mainViewModel.getSeries();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void navigateToMovieDetail(int idMovie) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToSerieDetail(int idSerie) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        categoryAdapter.addMovies(
                getResources().getStringArray(R.array.MoviesArray)[movies.get(0).getCategory()],
                movies);
    }

    @Override
    public void showSeries(List<Serie> series) {
        categoryAdapter.addSeries(
                getResources().getStringArray(R.array.SeriesArray)[series.get(0).getCategory()],
                series);
    }

    @Override
    public void showInitialMovie(Movie movie) {
        binding.setInitialmovie(movie);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(binding.mainLinearLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void setupInjection() {
        MovieBookApp app = (MovieBookApp) getApplication();
        MainComponent mainComponent = app.getMainComponent(this);
        mainComponent.inject(this);
    }


}
