package com.example.inclinemovies.ui

import androidx.lifecycle.*
import com.example.inclinemovies.data.MoviesRepository
import com.example.inclinemovies.data.moviesresponse.Result
import kotlinx.coroutines.launch

class AllMoviesViewModel : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Result>>()
    val popularMovies : LiveData<List<Result>> = _popularMovies

    private val mr = MoviesRepository()

    fun fetchPopularMovies() = viewModelScope.launch {
        mr.getPopularMovies().body()?.let {
            _popularMovies.postValue(it.results)
        }
    }

}