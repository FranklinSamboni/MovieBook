package com.frank.moviebook.movies.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frank.moviebook.R;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.databinding.TemplateInitialMovieBinding;
import com.frank.moviebook.movies.MainViewModel;
import com.frank.moviebook.movies.ui.ItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FRANK on 21/11/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static int INITIAL_MOVIE = 0;

    Context context;
    ItemClickListener itemClickListener;

    Movie movie;
    List<Map<String,List>> categories;

    public CategoryAdapter(Context context, List<Map<String,List>> categories, ItemClickListener itemClickListener) {
        this.context = context;
        this.categories = categories;
        this.itemClickListener = itemClickListener;
        movie = new Movie();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == INITIAL_MOVIE) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            TemplateInitialMovieBinding initialMovieBinding = TemplateInitialMovieBinding.inflate(layoutInflater, parent, false);
            return new InitialMovieViewHolder(initialMovieBinding);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_category, parent, false);
            return new CategoryViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CategoryViewHolder) {
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
            Map<String, List> map = categories.get(position);
            for (Map.Entry entry : map.entrySet()) {

                categoryViewHolder.categoryTitle.setText((String) entry.getKey());

                HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter((List) entry.getValue(), itemClickListener);
                categoryViewHolder.listRecyclerView.setAdapter(horizontalListAdapter);
                categoryViewHolder.listRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.HORIZONTAL, false));
            }
        }
        else if(holder instanceof InitialMovieViewHolder){
            InitialMovieViewHolder initialMovieViewHolder = (InitialMovieViewHolder) holder;
            initialMovieViewHolder.bind(movie);
            initialMovieViewHolder.binding.initialMovieLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.navigateToMovieDetail(movie.getId());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return INITIAL_MOVIE;
        }
        return position;
    }

    public void addMovies(String categoryTitle, List<Movie> movies) {
        Map<String, List> data = new LinkedHashMap<>(1);
        data.put(categoryTitle, movies);
        categories.add(data);
        notifyDataSetChanged();
    }

    public void addSeries(String categoryTitle, List<Serie> series) {
        Map<String, List> data = new LinkedHashMap<>(1);
        data.put(categoryTitle, series);
        categories.add(data);
        notifyDataSetChanged();
    }

    public void showInitialMovie(Movie movie) {
        if(categories.size() < 6){
            categories.add(0,new HashMap<String, List>());
        }
        this.movie = movie;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        RecyclerView listRecyclerView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title_text);
            listRecyclerView = itemView.findViewById(R.id.list_reclycler_view);
        }
    }

    public static class InitialMovieViewHolder extends RecyclerView.ViewHolder {

        final TemplateInitialMovieBinding binding;

        public InitialMovieViewHolder(TemplateInitialMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie movie) {
            binding.setInitialmovie(movie);
            binding.executePendingBindings();
        }
    }
}
