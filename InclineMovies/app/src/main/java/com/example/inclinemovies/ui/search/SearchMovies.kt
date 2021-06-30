package com.example.inclinemovies.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.inclinemovies.R
import com.example.inclinemovies.adapter.SearchMoviesAdapter

class SearchMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        val searchMoviesRV = findViewById<RecyclerView>(R.id.rv_search_movie)

        val adapter = SearchMoviesAdapter(this)

        searchMoviesRV.adapter = adapter

        val viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(SearchViewModel::class.java)

        viewModel.movies.observe({lifecycle}){
            adapter.submitData(lifecycle, it)
        }

    }
}