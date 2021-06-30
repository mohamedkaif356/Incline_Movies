package com.example.inclinemovies.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.inclinemovies.data.MoviesRepository

class SearchViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val movies = currentQuery.switchMap {
        repository.searchMovies(it).cachedIn(viewModelScope)
    }

    fun searchMovies(query: String){
        currentQuery.value = query
    }

    companion object{
        private const val DEFAULT_QUERY = "Iron man"
    }
}