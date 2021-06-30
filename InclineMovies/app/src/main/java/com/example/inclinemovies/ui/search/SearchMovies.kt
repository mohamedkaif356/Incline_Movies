package com.example.inclinemovies.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inclinemovies.R
import com.example.inclinemovies.adapter.LoadStateAdapter
import com.example.inclinemovies.adapter.SearchMoviesAdapter

class SearchMovies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        val searchMoviesRV = findViewById<RecyclerView>(R.id.rv_search_movie)
        val adapter = SearchMoviesAdapter(this)

        searchMoviesRV.layoutManager = GridLayoutManager(this, 3)
        searchMoviesRV.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { adapter.retry() },
            footer = LoadStateAdapter { adapter.retry() }
        )

        val viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(SearchViewModel::class.java)

        viewModel.searchMovies(QUERY)

        viewModel.movies.observe({lifecycle}){
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(SearchViewModel::class.java)

        val searchMoviesRV = findViewById<RecyclerView>(R.id.rv_search_movie)

        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu!!.findItem(R.id.search_movies)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMoviesRV.scrollToPosition(0)
                viewModel.searchMovies(query!!)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchMovies(newText!!)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    companion object{
        private const val QUERY = "Jack"
    }
}