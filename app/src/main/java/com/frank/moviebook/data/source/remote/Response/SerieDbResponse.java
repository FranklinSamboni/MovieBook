package com.frank.moviebook.data.source.remote.Response;

import com.frank.moviebook.data.Serie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FRANK on 19/11/2017.
 */

public class SerieDbResponse {
    @SerializedName("page")
    private String page;

    @SerializedName("total_results")
    private String totalResults;

    @SerializedName("total_pages")
    private String totalPages;

    @SerializedName("results")
    private List<Serie> results;

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

    public List<Serie> getResults() {
        return results;
    }

    public void setResults(List<Serie> results) {
        this.results = results;
    }
}
