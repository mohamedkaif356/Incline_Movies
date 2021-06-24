package com.example.inclinemovies

import com.example.inclinemovies.api.RetrofitBuilder
import com.example.inclinemovies.data.Constants
import org.junit.Test

import org.junit.Assert.*

class ApiTest {

    private val rb = RetrofitBuilder()

    @Test
    fun api_test(){
        val movies = rb.api.getPopularMovies(Constants.API_KEY).execute()
        assertNotNull(movies.body()?.results)
    }
}