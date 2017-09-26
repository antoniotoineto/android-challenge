
package com.mc.mimo.moviechallenge.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlaying {

    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("dates")
    @Expose
    public Dates dates;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;

}
