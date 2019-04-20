package com.andrew.samir.manastmovies.Data.PeopleResponseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseData {

    @Expose
    @SerializedName("results")
    private List<PersonDetailsData> results;
    @Expose
    @SerializedName("total_pages")
    private int totalPages;
    @Expose
    @SerializedName("total_results")
    private int totalResults;
    @Expose
    @SerializedName("page")
    private int page;

    public List<PersonDetailsData> getResults() {
        return results;
    }

    public void setResults(List<PersonDetailsData> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
