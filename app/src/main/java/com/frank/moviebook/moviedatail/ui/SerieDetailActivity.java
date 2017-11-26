package com.frank.moviebook.moviedatail.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.frank.moviebook.MovieBookApp;
import com.frank.moviebook.R;
import com.frank.moviebook.databinding.ActivitySerieDetailBinding;
import com.frank.moviebook.moviedatail.DetailsViewModel;
import com.frank.moviebook.moviedatail.di.DetailComponent;

import javax.inject.Inject;

public class SerieDetailActivity extends AppCompatActivity {

    ActivitySerieDetailBinding binding;

    @Inject
    DetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_serie_detail);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_serie_detail);

        setupInjection();

        int idSerie = getIntent().getIntExtra("idSerie",0);

        viewModel.getSerieById(idSerie);

    }

    private void setupInjection() {
        MovieBookApp app = (MovieBookApp) getApplication();
        DetailComponent detailComponent = app.getDetailComponent();
        detailComponent.inject(this);
    }
}
