package com.example.inclinemovies.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.inclinemovies.R
import com.example.inclinemovies.adapter.PopularMoviesAdapter
import com.example.inclinemovies.adapter.TopRatedMoviesAdapter
import com.example.inclinemovies.adapter.UpcomingMoviesAdapter
import com.example.inclinemovies.data.Constants
import com.example.inclinemovies.ui.search.SearchMovies

class MainActivity : AppCompatActivity() {

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private lateinit var upcomingMoviesAdapter : UpcomingMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchMovies = findViewById<ImageView>(R.id.iv_search_movies)
        val popularMoviesRV = findViewById<RecyclerView>(R.id.rv_popular_movies)
        val topRatedMoviesRV = findViewById<RecyclerView>(R.id.rv_topRated_movies)
        val upcomingMoviesRV = findViewById<RecyclerView>(R.id.rv_upcoming_movies)

        popularMoviesAdapter = PopularMoviesAdapter(this)
        topRatedMoviesAdapter = TopRatedMoviesAdapter(this)
        upcomingMoviesAdapter = UpcomingMoviesAdapter(this)

        popularMoviesRV.adapter = popularMoviesAdapter
        topRatedMoviesRV.adapter = topRatedMoviesAdapter
        upcomingMoviesRV.adapter = upcomingMoviesAdapter

        searchMovies.setOnClickListener {
            startActivity(Intent(this, SearchMovies::class.java))
        }

        val viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(AllMoviesViewModel::class.java)

        Constants.RESULT_PAGE = 10

        viewModel.fetchPopularMovies()
        viewModel.fetchTopRatedMovies()
        viewModel.fetchUpcomingMovies()


        viewModel.popularMovies.observe({lifecycle}){
            popularMoviesAdapter.differ.submitList(it)
        }

        viewModel.topRatedMovies.observe({lifecycle}){
            topRatedMoviesAdapter.differ.submitList(it)
        }

        viewModel.upcomingMovies.observe({lifecycle}){
            upcomingMoviesAdapter.differ.submitList(it)
        }

    }
}