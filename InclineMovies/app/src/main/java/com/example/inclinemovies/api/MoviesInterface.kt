package com.example.inclinemovies.api

import com.example.inclinemovies.data.moviedetails.MovieDetails
import com.example.inclinemovies.data.moviesearch.MovieSearch
import com.example.inclinemovies.data.moviesresponse.MoviesResponse
import com.example.inclinemovies.data.movievideo.MovieVideo
import com.example.inclinemovies.ui.details.MoviesDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key:String, @Query("page") page: Int) : Response<MoviesResponse>

    @GET("trending/movie/week")
    suspend fun getTopRatedMovies(@Query("api_key") api_key:String, @Query("page") page: Int) : Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String, @Query("page") page: Int) : Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int,
                                @Query("api_key") api_key: String)
    : Response<MovieDetails>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideo(@Path("movie_id") id: Int,
                                @Query("api_key") api_key: String)
            : Response<MovieVideo>

    @GET("search/movie")
    suspend fun getSearchedMovie(@Query("api_key") api_key: String,
                                 @Query("query") queryName: String,
                                 @Query("page") page: Int)
    : MovieSearch

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(@Path("movie_id") id: Int,
                              @Query("api_key") api_key: String)
            : Response<MoviesResponse>

}