package com.example.inclinemovies.api

import com.example.inclinemovies.data.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesInterface {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") api_key:String) : Call<PopularMovies>

}