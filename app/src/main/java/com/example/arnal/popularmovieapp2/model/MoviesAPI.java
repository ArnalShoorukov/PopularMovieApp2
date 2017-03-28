package com.example.arnal.popularmovieapp2.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arnal on 3/28/17.
 */

public interface MoviesAPI {

    @GET("movie/top_rated")
    Call<MovieResult> getTopRatedMovie(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResult> getPopularMovie(@Query("api_key") String apiKey);

}
