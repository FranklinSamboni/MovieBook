package com.frank.moviebook.search.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frank.moviebook.R;
import com.frank.moviebook.data.Movie;
import com.frank.moviebook.data.Serie;
import com.frank.moviebook.movies.adapters.HorizontalListAdapter;
import com.frank.moviebook.movies.ui.ItemClickListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FRANK on 26/11/2017.
 */

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.SearchCategoryViewHolder> {

    Context context;
    ItemClickListener itemClickListener;

    List<Map<String, List>> categories;

    public SearchCategoryAdapter(Context context, List<Map<String, List>> categories, ItemClickListener itemClickListener) {
        this.context = context;
        this.categories = categories;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SearchCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_category, parent, false);
        return new SearchCategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SearchCategoryViewHolder holder, int position) {
        Map<String, List> map = categories.get(position);
        for (Map.Entry entry : map.entrySet()) {

            holder.categoryTitle.setText((String) entry.getKey());

            HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter((List) entry.getValue(), itemClickListener);
            holder.listRecyclerView.setAdapter(horizontalListAdapter);
            holder.listRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false));
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
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

    public void clear(){
        categories.clear();
        notifyDataSetChanged();
    }

    public static class SearchCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        RecyclerView listRecyclerView;

        public SearchCategoryViewHolder(View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title_text);
            listRecyclerView = itemView.findViewById(R.id.list_reclycler_view);
        }
    }


}
