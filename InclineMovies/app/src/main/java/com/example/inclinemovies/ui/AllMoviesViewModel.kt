package com.example.inclinemovies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inclinemovies.data.MoviesRepository
import com.example.inclinemovies.data.moviesresponse.MoviesResponse

class AllMoviesViewModel : ViewModel() {

    private val _popularMovies = MutableLiveData<List<MoviesResponse>>()
    val popularMovies: LiveData<List<MoviesResponse>> = _popularMovies

    fun updatePopularMovies(){

    }

}