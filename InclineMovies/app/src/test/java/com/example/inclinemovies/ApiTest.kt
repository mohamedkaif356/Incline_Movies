package com.example.inclinemovies

import com.example.inclinemovies.api.RetrofitBuilder
import com.example.inclinemovies.data.Constants
import com.example.inclinemovies.data.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class ApiTest {

    private val rb = RetrofitBuilder()
    private val mr = MoviesRepository()

    @Test
    fun api_test() {

        runBlocking {

            val popular = rb.api.getPopularMovies(Constants.API_KEY, Constants.RESULT_PAGE)
            val topRated = rb.api.getTopRatedMovies(Constants.API_KEY, Constants.RESULT_PAGE)
            val upcoming = rb.api.getUpcomingMovies(Constants.API_KEY, Constants.RESULT_PAGE)
            val movieDetails = rb.api.getMovieDetails(508943, Constants.API_KEY)
            val searchMovies = rb.api.getSearchedMovie(Constants.API_KEY, "Jack sparrow")
            assertNotNull(popular.body()?.results)
            assertNotNull(topRated.body()?.results)
            assertNotNull(upcoming.body()?.results)
            assertNotNull(movieDetails.body()?.originalTitle)
            assertNotNull(searchMovies.body()?.results)

        }


    }

    @Test
    fun repository_test() {

        runBlocking {
            val popular = mr.getPopularMovies()
            val topRated = mr.getTopRatedMovies()
            val upcoming = mr.getUpcomingMovies()
            val movieDetails = mr.getMovieDetails(15478)
            val movieVideo = mr.getMovieVideo(15478)
            val searchMovies = mr.searchMovies("inception")
            assertNotNull(popular.body()?.results)
            assertNotNull(topRated.body()?.results)
            assertNotNull(upcoming.body()?.results)
            assertNotNull(movieDetails.body()?.originalTitle)
            assertNotNull(movieVideo.body()?.results)
            assertNotNull(searchMovies.body()?.results)
        }
    }
}