package com.frank.moviebook.movies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.databinding.TemplateHorizontalMoviesListBinding;
import com.frank.moviebook.movies.ui.ItemClickListener;

import java.util.List;

/**
 * Created by FRANK on 21/11/2017.
 */

public class HorizontalListAdapter extends RecyclerView.Adapter<HorizontalListAdapter.HorizontalListViewHolder>{

    List movieOrSerie;
    ItemClickListener itemClickListener;

    public HorizontalListAdapter(List movieOrSerie, ItemClickListener itemClickListener) {
        this.movieOrSerie = movieOrSerie;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public HorizontalListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TemplateHorizontalMoviesListBinding moviesListBinding = TemplateHorizontalMoviesListBinding.inflate(layoutInflater,parent,false);

        return new HorizontalListViewHolder(moviesListBinding);
    }

    @Override
    public void onBindViewHolder(HorizontalListViewHolder holder, final int position) {
        if(movieOrSerie.get(position).getClass().equals(Movie.class)){
            holder.bind((Movie) movieOrSerie.get(position), null);
            holder.binding.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.navigateToMovieDetail(((Movie) movieOrSerie.get(position)).getId());
                }
            });
        }
        else{
            holder.bind(null, (Serie) movieOrSerie.get(position));
            holder.binding.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.navigateToSerieDetail(((Serie) movieOrSerie.get(position)).getId());
                }
            });
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
