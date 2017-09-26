package com.mc.mimo.moviechallenge;

import com.mc.mimo.moviechallenge.pojo.NowPlaying;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface APIInterface {

    @GET("movie/now_playing?")
    Call<NowPlaying> doGetNowPlaingList(@Query("page") String page, @Query("language") String lang);

}
