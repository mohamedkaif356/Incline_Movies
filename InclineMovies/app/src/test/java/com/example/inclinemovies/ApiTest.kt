package com.example.inclinemovies

import com.example.inclinemovies.api.RetrofitBuilder
import com.example.inclinemovies.data.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class ApiTest {

    private val rb = RetrofitBuilder()

    @Test
    fun api_test(){

        runBlocking {
            val popular = rb.api.getPopularMovies(Constants.API_KEY)
            val topRated = rb.api.getTopRatedMovies(Constants.API_KEY)
            val upcoming = rb.api.getUpcomingMovies(Constants.API_KEY)
            val movieDetails = rb.api.getMovieDetails(508943, Constants.API_KEY, "videos")
            assertNotNull(popular.body()?.results)
            assertNotNull(topRated.body()?.results)
            assertNotNull(upcoming.body()?.results)
            assertNotNull(movieDetails.body()?.originalTitle)
        }
    }
}