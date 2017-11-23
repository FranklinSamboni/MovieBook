package com.frank.moviebook.movies.adapters;

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
import com.frank.moviebook.movies.MainViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FRANK on 21/11/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context context;

    List<Map<String,List>> categories;

    public CategoryAdapter(Context context, List<Map<String,List>> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        Map<String,List> map = categories.get(position);
        for(Map.Entry entry : map.entrySet()){

            holder.categoryTitle.setText((String)entry.getKey());

            HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter((List)entry.getValue());
            holder.listRecyclerView.setAdapter(horizontalListAdapter);
            holder.listRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL,false));
        }

    }

    public void addMovies(String category, List<Movie> movies){
        Map<String, List> data = new LinkedHashMap<>(1);
        data.put(category,movies);
        if(categories.size() < movies.get(0).getCategory()){ // LAS PELICULAS VAN DE LA POSICION 0 - 2
            categories.add( data);
        }else{
            categories.add( movies.get(0).getCategory()+ 0,data);
        }
        notifyDataSetChanged();
    }

    public void addSeries(String category, List<Serie> series){
        Map<String, List> data = new LinkedHashMap<>(1);
        data.put(category,series);
        if(categories.size() < (series.get(0).getCategory() + 3)){ // SE INGRESAN LAS SERIES DESDE LA POSICION 3
            categories.add( data);
        }else{
            categories.add( series.get(0).getCategory()+ 3,data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
        RecyclerView listRecyclerView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title_text);
            listRecyclerView = itemView.findViewById(R.id.list_reclycler_view);
        }
    }
}
