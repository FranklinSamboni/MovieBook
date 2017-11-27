package com.frank.moviebook.search.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.frank.moviebook.MovieBookApp;
import com.frank.moviebook.R;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.databinding.ActivitySearchBinding;
import com.frank.moviebook.moviedatail.ui.MovieDetailActivity;
import com.frank.moviebook.movies.adapters.CategoryAdapter;
import com.frank.moviebook.movies.ui.ItemClickListener;
import com.frank.moviebook.search.SearchViewModel;
import com.frank.moviebook.search.adapters.SearchCategoryAdapter;
import com.frank.moviebook.search.di.SearchComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchActivity extends AppCompatActivity implements SearchView, ItemClickListener {

    ActivitySearchBinding binding;

    @Inject
    SearchCategoryAdapter categoryAdapter;

    @Inject
    SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search);
        setupInjection();
        setupToolbar();
        setupRecyclerView();

        EditText editText = binding.searchText;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                clear();
                searchViewModel.getMovieByName(charSequence.toString());
                searchViewModel.getSerieByName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        searchViewModel.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        MovieBookApp app = (MovieBookApp) getApplication();
        SearchComponent searchComponent = app.getSearchComponent(this, this);
        searchComponent.inject(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.toolBar;
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupRecyclerView(){
        RecyclerView categoryRecyclerView = binding.categoryReciclerView;
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void clear() {
        categoryAdapter.clear();
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
    public void showError(String message) {
        Snackbar.make(binding.searchLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void navigateToMovieDetail(int idMovie) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("idMovie",idMovie);
        startActivity(intent);
    }

    @Override
    public void navigateToSerieDetail(int idSerie) {
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("idSerie",idSerie);
        startActivity(intent);
    }
}
