package com.frank.moviebook.moviedatail.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.frank.moviebook.MovieBookApp;
import com.frank.moviebook.R;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.databinding.ActivityMovieDetailBinding;
import com.frank.moviebook.moviedatail.DetailsViewModel;
import com.frank.moviebook.moviedatail.di.DetailComponent;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity {

    ActivityMovieDetailBinding binding;

    @Inject
    DetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_movie_detail);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail);
        setupInjection();
        setupToolbar();
        int idMovie = getIntent().getIntExtra("idMovie",0);
        int idSerie = getIntent().getIntExtra("idSerie",0);

        if(idMovie !=  0){
            Movie movie = viewModel.getMovieById(idMovie);
            binding.setMovie(movie);
        }
        else if(idSerie != 0){
            Serie serie = viewModel.getSerieById(idSerie);
            binding.setSerie(serie);
        }

    }

    private void setupToolbar() {
        Toolbar toolbar = binding.toolBar;
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupInjection() {
        MovieBookApp app = (MovieBookApp) getApplication();
        DetailComponent detailComponent = app.getDetailComponent();
        detailComponent.inject(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
