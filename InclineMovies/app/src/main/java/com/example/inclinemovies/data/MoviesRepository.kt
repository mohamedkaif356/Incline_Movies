package com.example.inclinemovies.data

import com.example.inclinemovies.api.RetrofitBuilder

class MoviesRepository {

    private val rb = RetrofitBuilder().api

    suspend fun getPopularMovies() = rb.getPopularMovies(Constants.API_KEY, Constants.RESULT_PAGE)


    suspend fun getTopRatedMovies() = rb.getTopRatedMovies(Constants.API_KEY, Constants.RESULT_PAGE)

    suspend fun getUpcomingMovies() = rb.getUpcomingMovies(Constants.API_KEY, Constants.RESULT_PAGE)

    suspend fun getMovieDetails(id: Int) = rb.getMovieDetails(id, Constants.API_KEY)

    suspend fun getMovieVideo(id: Int) = rb.getMovieVideo(id, Constants.API_KEY)

    suspend fun searchMovies(movieName: String) = rb.getSearchedMovie(Constants.API_KEY, movieName)

    suspend fun getSimilarMovies(id: Int) = rb.getSimilarMovie(id, Constants.API_KEY)
}