package com.example.inclinemovies.ui.home

import androidx.lifecycle.*
import com.example.inclinemovies.data.MoviesRepository
import com.example.inclinemovies.data.moviesresponse.Result
import kotlinx.coroutines.launch

class AllMoviesViewModel : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Result>>()
    val popularMovies : LiveData<List<Result>> = this._popularMovies

    private val _topRatedMovies = MutableLiveData<List<Result>>()
    val topRatedMovies : LiveData<List<Result>> = this._topRatedMovies

    private val _upcomingMovies = MutableLiveData<List<Result>>()
    val upcomingMovies : LiveData<List<Result>> = this._upcomingMovies


    private val mr = MoviesRepository()

    fun fetchPopularMovies() = viewModelScope.launch {
        mr.getPopularMovies().body()?.let {
            this@AllMoviesViewModel._popularMovies.postValue(it.results)
        }
    }

    fun fetchTopRatedMovies() = viewModelScope.launch {
        mr.getTopRatedMovies().body()?.let {
            this@AllMoviesViewModel._topRatedMovies.postValue(it.results)
        }
    }

    fun fetchUpcomingMovies() =viewModelScope.launch {
        mr.getUpcomingMovies().body()?.let {
            this@AllMoviesViewModel._upcomingMovies.postValue(it.results)
        }
    }

}