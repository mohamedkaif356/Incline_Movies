package com.example.inclinemovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.inclinemovies.R
import com.example.inclinemovies.adapter.PopularMoviesAdapter
import com.example.inclinemovies.adapter.TopRatedMoviesAdapter
import com.example.inclinemovies.adapter.UpcomingMoviesAdapter
import com.example.inclinemovies.data.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private lateinit var upcomingMoviesAdapter : UpcomingMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val popularMoviesRV = findViewById<RecyclerView>(R.id.rv_popular_movies)
        val topRatedMoviesRV = findViewById<RecyclerView>(R.id.rv_topRated_movies)
        val upcomingMoviesRV = findViewById<RecyclerView>(R.id.rv_upcoming_movies)

        popularMoviesAdapter = PopularMoviesAdapter()
        topRatedMoviesAdapter = TopRatedMoviesAdapter()
        upcomingMoviesAdapter = UpcomingMoviesAdapter()

        popularMoviesRV.adapter = popularMoviesAdapter
        topRatedMoviesRV.adapter = topRatedMoviesAdapter
        upcomingMoviesRV.adapter = upcomingMoviesAdapter

        val viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(AllMoviesViewModel::class.java)

        Constants.RESULT_PAGE = 1

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