package com.example.inclinemovies.data

import com.example.inclinemovies.api.RetrofitBuilder

class MoviesRepository {

    private val rb = RetrofitBuilder().api

    suspend fun getPopularMovies() = rb.getPopularMovies(Constants.API_KEY, Constants.RESULT_PAGE)


    suspend fun getTopRatedMovies() = rb.getTopRatedMovies(Constants.API_KEY, Constants.RESULT_PAGE)

    suspend fun getUpcomingMovies() = rb.getUpcomingMovies(Constants.API_KEY, Constants.RESULT_PAGE)

    suspend fun getMovieDetails(id: Int) = rb.getMovieDetails(id, Constants.API_KEY, "videos")

    suspend fun searchMovies(movieName: String) = rb.getSearchedMovie(Constants.API_KEY, movieName)
}