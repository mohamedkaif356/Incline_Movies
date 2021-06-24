package com.example.inclinemovies.api

import com.example.inclinemovies.data.moviedetails.MovieDetails
import com.example.inclinemovies.data.moviesresponse.MoviesResponse
import com.example.inclinemovies.data.movievideo.MovieVideo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key:String) : Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key:String) : Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String) : Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int,
                                @Query("api_key") api_key: String)
    : Response<MovieDetails>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(@Path("movie_id") id: Int,
                                @Query("api_key") api_key: String)
    : Response<MovieVideo>
}