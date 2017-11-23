package com.frank.moviebook.data.source.remote.Response;

import com.frank.moviebook.data.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FRANK on 19/11/2017.
 */

public class MovieDbResponse {

    @SerializedName("page")
    private String page;

    @SerializedName("total_results")
    private String totalResults;

    @SerializedName("total_pages")
    private String totalPages;

    @SerializedName("results")
    private List<Movie> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
