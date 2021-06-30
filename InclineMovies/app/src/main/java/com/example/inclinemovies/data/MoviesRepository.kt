package com.example.inclinemovies.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.inclinemovies.api.RetrofitBuilder

class MoviesRepository {

    private val rb = RetrofitBuilder().api

    suspend fun getPopularMovies() = rb.getPopularMovies(Constants.API_KEY, Constants.RESULT_PAGE)


    suspend fun getTopRatedMovies() = rb.getTopRatedMovies(Constants.API_KEY, Constants.RESULT_PAGE)

    suspend fun getUpcomingMovies() = rb.getUpcomingMovies(Constants.API_KEY, Constants.RESULT_PAGE)

    suspend fun getMovieDetails(id: Int) = rb.getMovieDetails(id, Constants.API_KEY)

    suspend fun getMovieVideo(id: Int) = rb.getMovieVideo(id, Constants.API_KEY)

    fun searchMovies(movieName: String) =
        Pager(
            config = PagingConfig(
                pageSize = 100,
                maxSize = 1000,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(rb, movieName)}
        ).liveData

    suspend fun getSimilarMovies(id: Int) = rb.getSimilarMovie(id, Constants.API_KEY)
}