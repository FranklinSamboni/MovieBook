package com.frank.moviebook.movies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.databinding.TemplateHorizontalMoviesListBinding;

import java.util.List;

/**
 * Created by FRANK on 21/11/2017.
 */

public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListAdapter.HorizontalListViewHolder>{

    List movieOrSerie;

    public HorizontalListAdapter(List movieOrSerie) {
        this.movieOrSerie = movieOrSerie;
    }

    @Override
    public HorizontalListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TemplateHorizontalMoviesListBinding moviesListBinding = TemplateHorizontalMoviesListBinding.inflate(layoutInflater,parent,false);
        return new HorizontalListViewHolder(moviesListBinding);
    }

    @Override
    public void onBindViewHolder(HorizontalListViewHolder holder, int position) {
        if(movieOrSerie.get(position).getClass().equals(Movie.class)){
            holder.bind((Movie) movieOrSerie.get(position), null);
        }
        else{
            holder.bind(null, (Serie) movieOrSerie.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return movieOrSerie.size();
    }

    public static class HorizontalListViewHolder extends RecyclerView.ViewHolder{

        final TemplateHorizontalMoviesListBinding binding;

        public HorizontalListViewHolder(TemplateHorizontalMoviesListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie movie, Serie serie){
            binding.setMovie(movie);
            binding.setSerie(serie);
            binding.executePendingBindings();
        }
    }

}
