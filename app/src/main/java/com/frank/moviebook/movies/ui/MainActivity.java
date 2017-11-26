package com.frank.moviebook.movies.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.frank.moviebook.MovieBookApp;
import com.frank.moviebook.R;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.databinding.ActivityMainBinding;
import com.frank.moviebook.moviedatail.ui.MovieDetailActivity;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.adapters.CategoryAdapter;
import com.frank.moviebook.movies.di.MainComponent;
import com.frank.moviebook.moviedatail.ui.SerieDetailActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityView, ItemClickListener {

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

        setupToolbar();
        setupRecyclerView();

        mainViewModel.getMovies();
        mainViewModel.getSeries();
    }


    private void setupInjection() {
        MovieBookApp app = (MovieBookApp) getApplication();
        MainComponent mainComponent = app.getMainComponent(this, this);
        mainComponent.inject(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.toolBar;
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView(){
        RecyclerView categoryRecyclerView = binding.categoryReciclerView;
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mainViewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToMovieDetail(int idMovie) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("idMovie",idMovie);
        startActivity(intent);
    }

    @Override
    public void navigateToSerieDetail(int idSerie) {
        Intent intent = new Intent(this,SerieDetailActivity.class);
        intent.putExtra("idSerie",idSerie);
        startActivity(intent);
    }

    @Override
    public void showMovies(String title, List<Movie> movies) {
        categoryAdapter.addMovies(title,movies);
    }

    @Override
    public void showSeries(String title,List<Serie> series) {
        categoryAdapter.addSeries(title, series);
    }

    @Override
    public void showInitialMovie(Movie movie) {
        categoryAdapter.showInitialMovie(movie);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(binding.mainLinearLayout, message, Snackbar.LENGTH_LONG).show();
    }

}
