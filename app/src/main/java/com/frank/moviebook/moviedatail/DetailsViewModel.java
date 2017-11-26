package com.frank.moviebook.moviedatail;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.data.source.MovieRepository;

/**
 * Created by FRANK on 25/11/2017.
 */

public class DetailsViewModel {

    private MovieRepository movieRepository;

    public DetailsViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(int idMovie){
        Movie movie = movieRepository.getMovieById(idMovie);
        return movie;
    }

    public Serie getSerieById(int Serie){
        return null;
    }
}
