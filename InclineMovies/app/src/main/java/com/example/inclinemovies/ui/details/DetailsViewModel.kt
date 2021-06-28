package com.example.inclinemovies.ui.details


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inclinemovies.data.MoviesRepository
import com.example.inclinemovies.data.moviedetails.MovieDetails
import com.example.inclinemovies.data.movievideo.Result
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val _movieVideo = MutableLiveData<List<Result>>()
    val movieVideo : LiveData<List<Result>> = _movieVideo

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails : LiveData<MovieDetails> = _movieDetails

    private val _similarMovies = MutableLiveData<List<com.example.inclinemovies.data.moviesresponse.Result>>()
    val similarMovies : LiveData<List<com.example.inclinemovies.data.moviesresponse.Result>> = this._similarMovies

    private val mr = MoviesRepository()

    fun fetchMovieDetails(id: Int) = viewModelScope.launch {
        mr.getMovieDetails(id).body()?.let {
            _movieDetails.postValue(it)
        }
    }

    fun fetchMovieVideo(id: Int) = viewModelScope.launch {
        mr.getMovieVideo(id).body()?.let {
            _movieVideo.postValue(it.results)
        }
    }

    fun fetchSimilarMovies(id: Int) = viewModelScope.launch {
        mr.getSimilarMovies(id).body()?.let {
            _similarMovies.postValue(it.results)
        }
    }

}
