package com.mc.mimo.moviechallenge.api;

import com.mc.mimo.moviechallenge.pojo.moviedetails.Movie;
import com.mc.mimo.moviechallenge.pojo.movielist.MovieList;
import com.mc.mimo.moviechallenge.pojo.search.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("movie/now_playing?")
    Call<MovieList> doGetNowPlaingList(@Query("page") String page, @Query("language") String lang);


    @GET("movie/popular?")
    Call<MovieList> doGetPopularList(@Query("page") String page, @Query("language") String lang);

    @GET("movie/top_rated?")
    Call<MovieList> doGetTopRatedList(@Query("page") String page, @Query("language") String lang);

    @GET("movie/{movie_id}?")
    Call<Movie> doGetMovieDetails(@Path("movie_id") int movie_id, @Query("language") String lang);

    @GET("search/movie?")
    Call<Search> doSearch(@Query("query") String query);

}
