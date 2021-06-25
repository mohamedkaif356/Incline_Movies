package com.example.inclinemovies.api

import com.example.inclinemovies.data.moviedetails.MovieDetails
import com.example.inclinemovies.data.moviesearch.MovieSearch
import com.example.inclinemovies.data.moviesresponse.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface MoviesInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key:String) : Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key:String) : Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key:String) : Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int,
                                @Query("api_key") api_key: String,
                                @Query("append_to_response") append_to_response: String)
    : Response<MovieDetails>

    @GET("search/movie")
    suspend fun getSearchedMovie(@Query("api_key") api_key: String,
                                 @Query("query") queryName: String)
    : Response<MovieSearch>

}